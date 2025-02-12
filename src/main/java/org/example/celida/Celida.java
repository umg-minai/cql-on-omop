package org.example.celida;

import OMOP.Person;
import jakarta.persistence.EntityManager;
import org.example.engine.CQLonOMOPEngine;
import org.example.engine.ConnectionFactory;
import org.example.engine.InMemoryLibrarySourceProvider;
import org.hl7.elm.r1.VersionedIdentifier;

import java.util.stream.Stream;

public class Celida {

    final EntityManager entityManager;

    final InMemoryLibrarySourceProvider librarySourceProvider;

    final CQLonOMOPEngine engine;

    private Celida() {
        this.entityManager = ConnectionFactory.createEntityManager();
        this.librarySourceProvider = new InMemoryLibrarySourceProvider();
        this.engine = new CQLonOMOPEngine(entityManager, librarySourceProvider);
    }

    private void run() {
        getPersonStream().forEach(this::evaluatePerson);
    }

    private Stream<Person> getPersonStream() {
        final var clazz = OMOP.Person.class;
        final var criteria = entityManager.getCriteriaBuilder()
                .createQuery(clazz);
        final var query = criteria.select(criteria.from(clazz));
        return entityManager.createQuery(query).getResultStream();
    }

    private void evaluatePerson(final OMOP.Person person) {
        final var id = person.getPersonId().orElseThrow();
        final var libraryName = "Test2"; // String.format("Test%s", id);
        final var identifier = new VersionedIdentifier().withId(libraryName);
        librarySourceProvider.registerLibrary(identifier, String.format("""
                        library %s version '0.1.0'
                        using OMOP version 'v5.4'
                        context Patient
                        define E: [ConditionOccurrence]
                        define F: [Observation] o return o.observationDateTime""", libraryName)
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
            for (var element : iterable) {
                System.out.printf("%s, ", element);
            }
        } else {
            System.out.print(value);
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        final var celida = new Celida();
        celida.run();
    }

}
