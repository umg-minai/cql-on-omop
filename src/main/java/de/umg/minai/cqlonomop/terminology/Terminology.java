package de.umg.minai.cqlonomop.terminology;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.umg.minai.cqlonomop.commandline.CqlOptions;
import de.umg.minai.cqlonomop.commandline.DatabaseOptions;
import de.umg.minai.cqlonomop.commandline.DefaultValueProvider;
import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.Constants;
import de.umg.minai.cqlonomop.engine.OMOPDataProvider;
import de.umg.minai.cqlonomop.terminal.DefaultTheme;
import de.umg.minai.cqlonomop.terminal.ErrorPresenter;
import de.umg.minai.cqlonomop.terminal.SourcePresenter;
import de.umg.minai.cqlonomop.terminal.Theme;
import org.hl7.elm.r1.IncludeDef;
import org.hl7.elm.r1.Library;
import org.hl7.elm.r1.VersionedIdentifier;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

@Command(
        name = "terminology",
        description = """
                      Extract terminology information from the specified CQL libraries and output a JSON representation.
                      If connection parameters for an OMOP database are specified, additional information about the \
                      OMOP concepts used in the CQL libraries will be retrieved from the database.
                      """,
        defaultValueProvider = DefaultValueProvider.class,
        usageHelpAutoWidth = true
)
public class Terminology implements Callable<Integer> {

    private static final Logger log = LoggerFactory.getLogger(Terminology.class);
    @ArgGroup(validate = false, heading = "Database Options%n")
    private DatabaseOptions databaseOptions = new DatabaseOptions();

    @ArgGroup(validate = false, heading = "CQL Options%n")
    private CqlOptions cqlOptions;

    @CommandLine.Parameters(
            arity = "1..*",
            description = """
                          The id or ids (without version) of CQL libraries from which terminology should be extracted.
                          For a library named NAME, a file named NAME.cql has to present in one of the directories \
                          specified via the -I option. If multiple libraries are specified, the extracted terminology \
                          information is merged.
                          """
    )
    private List<String> libraries;

    private record CodeSystemKey(String libraryName, String name) {}

    // TODO(moringenj): store name and source location?
    private record Code(String code, String system) {}

    @JsonAutoDetect
    private record ConceptInfo(String id, String name, String domain, Set<String> usingLibraries) {}

    @Override
    public Integer call() {
        // Prepare a configuration and instantiate the engine.
        var configuration = new Configuration();
        if (databaseOptions != null) {
            configuration = databaseOptions.applyToConfiguration(configuration);
        }
        if (cqlOptions != null) {
            configuration = cqlOptions.applyToConfiguration(configuration);
        }
        // Instantiate CQL engine.
        final var engine = new CQLonOMOPEngine(configuration);

        // Initialize the terminal and result, error,
        // etc. presentation components.
        final Terminal terminal;
        final Theme theme;
        try {
            terminal = TerminalBuilder.builder().build();
            theme = new DefaultTheme();
        } catch (final IOException e) {
            throw new RuntimeException(String.format("Internal error initializing terminal: %s%n", e));
        }
        final var sourcePresenter = new SourcePresenter(terminal, theme, engine.getLibraryManager());
        final var valuePresenter = new de.umg.minai.cqlonomop.terminal.ValuePresenter(terminal, theme);
        final var errorPresenter = new ErrorPresenter(terminal, theme, sourcePresenter, valuePresenter);

        // Load libraries, collect terminology and output results.
        var isSuccess = true;
        try {
            // Load the libraries specified on the commandline with dependencies and collect the dependency-closure
            // for processing.
            final var allLibraries = new HashSet<Library>();
            for (final var library : libraries) {
                final var newLibraries = engine.prepareLibrary(new VersionedIdentifier().withId(library));
                allLibraries.addAll(newLibraries);
            }
            Collection<ConceptInfo> conceptInfos =
                    engine.getSessionFactory() != null
                            ? engine.withEngineSession(session -> collectTerminology(session, allLibraries))
                            : collectTerminology(null, allLibraries);
            // Output collected concept info as JSON.
            var objectMapper = new ObjectMapper();
            objectMapper.writeValue(System.out, conceptInfos);
        } catch (final Exception exception) {
            errorPresenter.presentError(exception);
            isSuccess = false;
        }
        terminal.flush();
        return isSuccess ? CommandLine.ExitCode.OK : CommandLine.ExitCode.SOFTWARE;
    }

    private Collection<ConceptInfo> collectTerminology(final CQLonOMOPEngine.EngineSession session,
                                                       final Collection<Library> libraries) {
        // Step 1: collect the code systems used in all libraries. This information is required to resolve code system
        // references within code definition
        final var codeSystems = new HashMap<CodeSystemKey, String>();
        for (final var library : libraries) {
            final var codeSystemDefs = library.getCodeSystems();
            if (codeSystemDefs != null) {
                for (final var codeSystem : codeSystemDefs.getDef()) {
                    final var key = new CodeSystemKey(library.getIdentifier().getId(), codeSystem.getName());
                    codeSystems.put(key, codeSystem.getId());
                }
            }
        }
        // Step 2: Look for code definitions in the collected libraries. This analysis considers only the form
        //   code NAME: CODE from [LIBRARY.]CODESYSTEM
        // of code definitions. Code literals of the form
        //   Code{code: CODE, system: SYSTEM}
        // are not considered.
        final var codes = new HashMap<Code, HashSet<String>>();
        for (final var library : libraries) {
            final var includes = library.getIncludes();
            final var codeDefs = library.getCodes();
            if (codeDefs != null) {
                for (final var codeDef : codeDefs.getDef()) {
                    // Resolve the codesystem reference of the code definition into a CodeSystemId instance with a
                    // library name and a codesystem name.
                    final var codeSystemReference = codeDef.getCodeSystem();
                    final var codeSystemLibrary = codeSystemReference.getLibraryName();
                    final var key = new CodeSystemKey(
                            codeSystemLibrary != null
                                    ? includes.getDef().stream()
                                    .filter(includeDef -> includeDef.getLocalIdentifier().equals(codeSystemLibrary))
                                    .findFirst()
                                    .map(IncludeDef::getPath)
                                    .orElse(null)
                                    : library.getIdentifier().getId(),
                            codeSystemReference.getName());
                    // Look up the codesystem id based on the key and determine whether the codesystem is the OMOP
                    // standard vocabulary. In that case, the "code" part of defined codes is the OMOP concept id.
                    // Collect all OMOP concept ids and remember for each which library (or libraries) defined it.
                    final var codeSystemId = codeSystems.get(key);
                    if (codeSystemId.equals(Constants.OMOP_CODESYSTEM_URI)) {
                        final var code = new Code(codeDef.getId(), codeSystemId);
                        codes.compute(code, (key1, existing) ->  {
                            var list = existing;
                            if (list == null) {
                                list = new HashSet<>();
                            }
                            list.add(library.getIdentifier().getId());
                            return list;
                        });
                    }
                }
            }
        }
        // Step 3: If a database connection is available, augment the collected OMOP concept ids with the concept
        // name, domain name and possibly other information. Otherwise, those fields will be empty.
        final var conceptInfos = new ArrayList<ConceptInfo>();
        for (final var entry : codes.entrySet()) {
            final var code = entry.getKey();
            final var id = code.code();
            String name = null;
            String domainName = null;
            Set<String> usingLibraries = entry.getValue();
            if (session != null) {
                final var dataProvider = session.cqlEngine().getEnvironment().getDataProviders().values().stream()
                        .filter(provider -> provider instanceof OMOPDataProvider)
                        .findFirst().orElseThrow();
                final var queryCode = new org.opencds.cqf.cql.engine.runtime.Code().withCode(code.code()).withSystem(code.system());
                final var result = dataProvider.retrieve(null,
                        null,
                        null,
                        "Concept",
                        null,
                        "concept",
                        List.of(queryCode),
                        null,
                        null,
                        null,
                        null,
                        null);
                final var iterator = result.iterator();
                if (iterator.hasNext()) {
                    final var concept = iterator.next();
                    final var domain = dataProvider.resolvePath(concept, "domain");
                    name = (String) dataProvider.resolvePath(concept, "conceptName");
                    domainName = (String) dataProvider.resolvePath(domain, "domainName");
                }
            }
            final var conceptInfo = new ConceptInfo(id, name, domainName, usingLibraries);
            conceptInfos.add(conceptInfo);
        }
        return conceptInfos;
    }

}
