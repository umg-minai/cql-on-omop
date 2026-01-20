// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
package OMOP.v54;

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
@Table(name = "fact_relationship")
public class FactRelationship {

    @Embeddable
    private static class CompoundId {

        @Column(name = "domain_concept_id_1", updatable = false,
                nullable = false)
        private Integer domainConceptId1;

        @Column(name = "domain_concept_id_2", updatable = false,
                nullable = false)
        private Integer domainConceptId2;

        @Column(name = "relationship_concept_id", updatable = false,
                nullable = false)
        private Integer relationshipConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return ((other.getClass() == this.getClass())
                            && (Objects.equals(this.domainConceptId1, otherInstance.domainConceptId1))
                            && (Objects.equals(this.domainConceptId2, otherInstance.domainConceptId2))
                            && (Objects.equals(this.relationshipConceptId, otherInstance.relationshipConceptId)));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.domainConceptId1, this.domainConceptId2, this.relationshipConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("domainConceptId1=");
            result.append(this.domainConceptId1);
            result.append(", ");
            result.append("domainConceptId2=");
            result.append(this.domainConceptId2);
            result.append(", ");
            result.append("relationshipConceptId=");
            result.append(this.relationshipConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getDomainConceptId1() {
        return this.compoundId.domainConceptId1;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id_1", insertable = false,
                updatable = false)
    @MapsId("domainConceptId1")
    private Concept domainConcept1;
    
    public Concept getDomainConcept1() {
        return this.domainConcept1;
    }

    public void setDomainConcept1(final Concept newValue) {
        this.domainConcept1 = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.domainConceptId1 = (newValue != null) ? newValue.getConceptId() : 0;
    }

    public Integer getDomainConceptId2() {
        return this.compoundId.domainConceptId2;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id_2", insertable = false,
                updatable = false)
    @MapsId("domainConceptId2")
    private Concept domainConcept2;
    
    public Concept getDomainConcept2() {
        return this.domainConcept2;
    }

    public void setDomainConcept2(final Concept newValue) {
        this.domainConcept2 = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.domainConceptId2 = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "fact_id_1", updatable = false, nullable = false)
    private Integer factId1;
    
    public Integer getFactId1() {
        return this.factId1;
    }

    public void setFactId1(final Integer newValue) {
        this.factId1 = newValue;
    }

    @Column(name = "fact_id_2", updatable = false, nullable = false)
    private Integer factId2;
    
    public Integer getFactId2() {
        return this.factId2;
    }

    public void setFactId2(final Integer newValue) {
        this.factId2 = newValue;
    }

    public Integer getRelationshipConceptId() {
        return this.compoundId.relationshipConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_concept_id", insertable = false,
                updatable = false)
    @MapsId("relationshipConceptId")
    private Concept relationshipConcept;
    
    public Concept getRelationshipConcept() {
        return this.relationshipConcept;
    }

    public void setRelationshipConcept(final Concept newValue) {
        this.relationshipConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.relationshipConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("FactRelationship{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
