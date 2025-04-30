package OMOP.v54;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "concept_relationship", schema = "cds_cdm")
public class ConceptRelationship {

    @Embeddable
    private static class CompoundId {

        @Column(name = "concept_id_1", insertable = false, updatable = false,
                nullable = true)
        private Integer conceptId1;
        @Column(name = "concept_id_2", insertable = false, updatable = false,
                nullable = true)
        private Integer conceptId2;
        @Column(name = "relationship_id", insertable = false,
                updatable = false, nullable = true)
        private String relationshipId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.relationshipId, otherInstance.relationshipId)
                            && Objects.equals(this.conceptId2, otherInstance.conceptId2)
                            && Objects.equals(this.conceptId1, otherInstance.conceptId1));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.relationshipId, this.conceptId2, this.conceptId1);
        }

    }

    @EmbeddedId
    private CompoundId compoundId;

    public Optional<Integer> getConceptId1() {
        if (this.compoundId.conceptId1 != null) {
            return Optional.of(this.compoundId.conceptId1);
        } else {
            return Optional.empty();
        }

    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id_1")
    @MapsId("conceptId1")
    private Concept concept1;

    public Concept getConcept1() {
        return this.concept1;
    }

    public Integer getConceptId2() {
        return this.compoundId.conceptId2;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id_2")
    @MapsId("conceptId2")
    private Concept concept2;

    public Concept getConcept2() {
        return this.concept2;
    }

    @Column(name = "invalid_reason", insertable = false, updatable = false,
            nullable = true)
    private String invalidReason;

    public Optional<String> getInvalidReason() {
        if (this.invalidReason != null) {
            return Optional.of(this.invalidReason);
        } else {
            return Optional.empty();
        }

    }

    public Optional<String> getRelationshipId() {
        if (this.compoundId.relationshipId != null) {
            return Optional.of(this.compoundId.relationshipId);
        } else {
            return Optional.empty();
        }

    }

    @ManyToOne(targetEntity = Relationship.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id")
    @MapsId("relationshipId")
    private Relationship relationship;

    public Optional<Relationship> getRelationship() {
        return Optional.ofNullable(this.relationship);
    }

    @Column(name = "valid_end_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime validEndDate;

    public Optional<Date> getValidEndDate() {
        if (this.validEndDate != null) {
            return Optional.of(new Date(this.validEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }

    }

    @Column(name = "valid_start_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime validStartDate;

    public Optional<Date> getValidStartDate() {
        if (this.validStartDate != null) {
            return Optional.of(new Date(this.validStartDate.toLocalDate()));
        } else {
            return Optional.empty();
        }

    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptRelationship{id=").append(this.relationship);
        result.append(", concept1=").append(this.getConcept1());
        result.append(", concept2=").append(this.getConcept2());
        result.append("}");
        return result.toString();
    }

}
