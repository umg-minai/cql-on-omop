package OMOP.v53;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "concept_relationship", schema = "cds_cdm")
public class ConceptRelationship {

    @Embeddable
    private static class CompoundId {

        @Column(name = "concept_id_1", updatable = false, nullable = false)
        private Integer conceptId1;

        @Column(name = "concept_id_2", updatable = false, nullable = false)
        private Integer conceptId2;

        @Column(name = "relationship_id", updatable = false, nullable = false)
        private String relationshipId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.conceptId1, otherInstance.conceptId1)
                            && Objects.equals(this.conceptId2, otherInstance.conceptId2)
                            && Objects.equals(this.relationshipId, otherInstance.relationshipId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.conceptId1, this.conceptId2, this.relationshipId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("conceptId1=");
            result.append(this.conceptId1);
            result.append(", ");
            result.append("conceptId2=");
            result.append(this.conceptId2);
            result.append(", ");
            result.append("relationshipId=");
            result.append(this.relationshipId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getConceptId1() {
        return this.compoundId.conceptId1;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id_1", insertable = false, updatable = false)
    @MapsId("conceptId1")
    private Concept concept1;
    
    public Concept getConcept1() {
        return this.concept1;
    }

    public void setConcept1(final Concept newValue) {
        this.concept1 = newValue;
        this.compoundId.conceptId1 = newValue.getConceptId();
    }

    public Integer getConceptId2() {
        return this.compoundId.conceptId2;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id_2", insertable = false, updatable = false)
    @MapsId("conceptId2")
    private Concept concept2;
    
    public Concept getConcept2() {
        return this.concept2;
    }

    public void setConcept2(final Concept newValue) {
        this.concept2 = newValue;
        this.compoundId.conceptId2 = newValue.getConceptId();
    }

    @Column(name = "invalid_reason", updatable = false, nullable = true)
    private String invalidReason;
    
    public Optional<String> getInvalidReason() {
        if (this.invalidReason != null) {
            return Optional.of(this.invalidReason);
        } else {
            return Optional.empty();
        }
    }

    public void setInvalidReason(final String newValue) {
        this.invalidReason = newValue;
    }

    public String getRelationshipId() {
        return this.compoundId.relationshipId;
    }

    @ManyToOne(targetEntity = Relationship.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id", insertable = false, updatable = false)
    @MapsId("relationshipId")
    private Relationship relationship;
    
    public Relationship getRelationship() {
        return this.relationship;
    }

    public void setRelationship(final Relationship newValue) {
        this.relationship = newValue;
        this.compoundId.relationshipId = newValue.getRelationshipId();
    }

    @Column(name = "valid_end_date", updatable = false, nullable = false)
    private ZonedDateTime validEndDate;
    
    public Date getValidEndDate() {
        return new Date(this.validEndDate.toLocalDate());
    }

    public void setValidEndDate(final Date newValue) {
        this.validEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "valid_start_date", updatable = false, nullable = false)
    private ZonedDateTime validStartDate;
    
    public Date getValidStartDate() {
        return new Date(this.validStartDate.toLocalDate());
    }

    public void setValidStartDate(final Date newValue) {
        this.validStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptRelationship{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
