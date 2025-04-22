package OMOP.v54;

import jakarta.persistence.*;

@Entity
@Table(name = "concept_relationship", schema = "cds_cdm")
public class ConceptRelationship {

    @Embeddable
    private static class CompoundId {

        @Column(name = "concept_id_1")
        public Integer concept1Id;

        @Column(name = "concept_id_2")
        public Integer concept2Id;

        @Column(name = "relationship_id")
        public String relationshipId;

    }

    @EmbeddedId
    CompoundId embeddedId;

    public String getRelationshipId() {
        return this.embeddedId.relationshipId;
    }

    @ManyToOne(targetEntity = Relationship.class, fetch = FetchType.LAZY)
    @MapsId("relationshipId")
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
    private Concept concept2;

    public Concept getConcept2() {
        return this.concept2;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptRelationshiop{id=").append(this.relationship);
        result.append(", concept1=")
                .append(this.getConcept1())
                .append("'");
        result.append(", concept2=")
                .append(this.getConcept2())
                .append("'");
        result.append("}");
        return result.toString();
    }

}
