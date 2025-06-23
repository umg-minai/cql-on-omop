package de.umg.minai.cqlonomop.batch;

import OMOP.MappingInfo;
import OMOP.v54.Person;
import jakarta.persistence.EntityManager;
import de.umg.minai.cqlonomop.engine.CQLonOMOPEngine;
import de.umg.minai.cqlonomop.engine.Configuration;
import de.umg.minai.cqlonomop.engine.ConnectionFactory;
import de.umg.minai.cqlonomop.engine.InMemoryLibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.util.Set;
import java.util.stream.Collectors;

public class Batch {

    final EntityManager entityManager;

    final InMemoryLibrarySourceProvider librarySourceProvider;

    final CQLonOMOPEngine engine;

    private Batch() {
        final var modelInfo = MappingInfo.ensureVersion("v5.4");
        this.entityManager = ConnectionFactory.createEntityManager(new Configuration(), modelInfo);
        this.librarySourceProvider = new InMemoryLibrarySourceProvider();
        this.engine = null; // new CQLonOMOPEngine(entityManager, modelInfo, librarySourceProvider);
    }

    private void run() {
        getPersons().forEach(this::evaluatePerson);
    }

    private Set<Person> getPersons() {
        final var clazz = Person.class;
        final var criteria = entityManager.getCriteriaBuilder()
                .createQuery(clazz);
        final var query = criteria.select(criteria.from(clazz));
        return entityManager.createQuery(query).getResultStream().collect(Collectors.toSet());
    }

    private void evaluatePerson(final Person person) {
        final var id = person.getPersonId();
        final var libraryName = "Test2"; // String.format("Test%s", id);
        final var identifier = new VersionedIdentifier().withId(libraryName);
        librarySourceProvider.registerLibrary(identifier, String.format("""
                        library %s version '0.1.0'
                        using OMOP version 'v5.4'
                        include "OMOPHelpers"

                        codesystem OMOPCode: 'https://fhir-terminology.ohdsi.org'
                        code "Condition COVID19 via OMOP": '37311061' from OMOPCode display 'Disease caused by Severe acute respiratory syndrome coronavirus 2 (disorder)'

                        codesystem SNOMED: 'http://snomed.info/sct'
                        code "Condition COVID19 via SNOMED": '840539006' from SNOMED display 'Disease caused by Severe acute respiratory syndrome coronavirus 2 (disorder)'

                        concept "Condition COVID19": {
                           "Condition COVID19 via OMOP",
                           "Condition COVID19 via SNOMED"
                        }

                        context Patient
                        //define Covid: First([Concept: Code '111293003' from SNOMED])
                        //define Birth: Patient.birthDatetime
                        //define AgeInYearsToday: AgeInYearsAt(@2025-02-13)
                        //define Conditions: [ConditionOccurrence] c return {name: c.conditionConcept.conceptName, code: c.conditionConcept.conceptId}
                        //define F: [Observation] o return o.observationDatetime
                        //define G: [Observation] o return o.observationDate
                        define function PatientAndCondition(condition OMOP.ConditionOccurrence):
                          condition c
                          let con: c.conditionConcept
                          return {
                            person: c.person,
                            age: AgeInYearsAt(@2025-02-13),
                            conceptName: con.conceptName,
                            conceptId: con.conceptId,
                            conceptCode: con.conceptCode + ' (from ' + con.vocabularyId + ')'
                          }
                        define ViaSNOMED: [ConditionOccurrence: "Condition COVID19 via SNOMED"] c return all PatientAndCondition(c)
                        define ViaOMOP: [ConditionOccurrence: conditionConcept ~ "Condition COVID19 via OMOP"] c return all PatientAndCondition(c)
                        //define COVID: [ConditionOccurrence] c where c.conditionConcept subsumes ToConcept("Condition COVID19")
                        """, libraryName)
                );
        final var result = this.engine.evaluateLibrary(libraryName, person);
        System.out.printf("%s%n", person);
        result.expressionResults.forEach((name, oneResult) -> {
            System.out.printf("  %s => ", name);
            presentResultValue(oneResult.value());
        });
    }

    private void presentResultValue(final Object value) {
        if (value instanceof Iterable<?> iterable) {
            System.out.print("{");
            for (var element : iterable) {
                System.out.printf("%s, ", element);
            }
            System.out.print("}");
        } else {
            System.out.print(value);
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        final var celida = new Batch();
        celida.run();
    }

}
