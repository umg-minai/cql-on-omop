package de.umg.minai.cqlonomop.engine;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class CodeSystems {

    public record ResolutionResult(String id, boolean hierarchical) {}

    /**
     * The URI that identifies the OMOP Standard Vocabulary code system. We treat this as a special case because we map
     * CQL codes directly to OMOP concept IDs without looking at the concept code or vocabulary.
     */
    public final static String OMOP_CODESYSTEM_URI = "https://fhir-terminology.ohdsi.org";

    /**
     * Mapping from built-in Codesystem URIs to OMOP vocabulary ids. We use these when we map a code with id i and
     * Codesystem URI u to an OMOP concept: assume that this map maps URI u to vocabulary id v, then the row in the
     * concept table has concept_code = i and vocabulary_id = v.
     */
    public final static Map<String, String> OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID = Map.of(
            "http://loinc.org",                                             "LOINC",
            "http://snomed.info/sct",                                       "SNOMED",
            "https://www.who.int/tools/atc-ddd-toolkit/atc-classification", "ATC",
            "https://www.cdc.gov/nchs/icd/icd-10-cm.htm",                   "ICD10CM",
            "http://www.cms.gov/Medicare/Coding/ICD10/index.html",          "ICM10PCS"
    );

    /**
     * Check whether codeSystem indicates hierarchical processing via the URL query part being
     * "?hierarchical".
     *
     * @param codeSystem A URL that designates a code system. May include the ?hierarchical query.
     * @return Two values: 1) A base URL computed from codeSystem that does not include a query part 2) a Boolean which
     *         indicates whether codeSystem indicates hierarchical processing
     * @throws RuntimeException If codeSystem is not a well-formed URL or if a query part other than "?hierarchical"
     *                          is present.
     */
    public static ResolutionResult analyzeCodeSystem(final String codeSystem) {
        // Parse the code system designator as a URL.
        final URL codeSystemURL;
        try {
            codeSystemURL= new URL(codeSystem);
        } catch (MalformedURLException exception) {
            throw new RuntimeException(String.format("Code system URL '%s' is not valid: %s",
                    codeSystem,
                    exception));
        }
        // If the query part of the URL is "hierarchical", return true and a URL with the query part stripped,
        // otherwise return false the unmodified URL.
        final var query = codeSystemURL.getQuery();
        if (query == null) {
            return new ResolutionResult(codeSystem, false);
        }
        if (query.equals("hierarchical")) {
            try {
                final var baseURL = new URL(codeSystemURL.getProtocol(),
                        codeSystemURL.getHost(),
                        codeSystemURL.getPort(),
                        codeSystemURL.getPath());
                return new ResolutionResult(baseURL.toString(), true);
            } catch (MalformedURLException exception) {
                throw new RuntimeException("should not happen");
            }
        } else {
            throw new RuntimeException(String.format("Code system URL '%s' contains invalid query part: %s",
                    codeSystemURL,
                    codeSystemURL.getQuery()));
        }
    }

    /**
     * Identify the code system designated by codeSystem and return both its id and a Boolean which indicates whether
     * codeSystem implies hierarchical interpretation.
     *
     * @param codeSystem A URL that designates a code system. May include the ?hierarchical query.
     * @return Two values: 1) A code system id string or null if the code system is the OMOP standard vocabulary 2) a
     *         Boolean which indicates whether codeSystem implies hierarchical interpretation.
     * @throws RuntimeException If codeSystem is not a well-formed URL or if a query part other than "?hierarchical"
     *                          is present.
     */
    public static ResolutionResult resolveCodeSystem(final String codeSystem) {
        final var URLandHierarchical = analyzeCodeSystem(codeSystem);
        if (URLandHierarchical.id().equals(OMOP_CODESYSTEM_URI)) {
            return new ResolutionResult(null, URLandHierarchical.hierarchical);
        } else {
            final var id = OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID.get(URLandHierarchical.id());
            if (id == null) {
                throw new RuntimeException(String.format("Could not resolve codesystem URL '%s' (normalize from '%s'",
                        URLandHierarchical.id(),
                        codeSystem));
            }
            return new ResolutionResult(id, URLandHierarchical.hierarchical);
        }
    }

}
