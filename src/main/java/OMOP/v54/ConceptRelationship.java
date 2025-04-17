package OMOP.v54;

import jakarta.persistence.*;

@Entity
@Table(name = "concept_relationship", schema = "cds_cdm")
public class ConceptRelationship {

    @Embeddable
    private static class Concept1IdConcept2Id {

        @Column(name = "concept_id_1")
        public Integer concept1Id;

        @Column(name = "concept_id_2")
        public Integer concept2Id;

        // FIXME(jmoringe): needs relationship_id for uniqueness as well

    }

    @EmbeddedId
    Concept1IdConcept2Id concept1IdConcept2Id;

    @ManyToOne(targetEntity = Relationship.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id")
    private Relationship relationship;

    public Relationship getRelationship() {
        return this.relationship;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @MapsId("concept2Id")
    @JoinColumn(name = "concept_id_1")
    private Concept concept1;

    public Concept getConcept1() {
        return this.concept1;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @MapsId("concept2Id")
    @JoinColumn(name = "concept_id_2")
    Concept concept2;

    public Concept getConcept2() {
        return this.concept2;
    }

}
