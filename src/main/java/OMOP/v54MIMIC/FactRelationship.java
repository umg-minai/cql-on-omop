package OMOP.v54MIMIC;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "fact_relationship", schema = "cds_cdm")
public class FactRelationship {

    @Embeddable
    private static class CompoundId {

        @Column(name = "domain_concept_id_1", insertable = false,
                updatable = false, nullable = false)
        private Long domainConceptId1;

        @Column(name = "domain_concept_id_2", insertable = false,
                updatable = false, nullable = false)
        private Long domainConceptId2;

        @Column(name = "relationship_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Long relationshipConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.domainConceptId1, otherInstance.domainConceptId1)
                            && Objects.equals(this.domainConceptId2, otherInstance.domainConceptId2)
                            && Objects.equals(this.relationshipConceptId, otherInstance.relationshipConceptId));
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
    private CompoundId compoundId;

    public Long getDomainConceptId1() {
        return this.compoundId.domainConceptId1;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id_1")
    @MapsId("domainConceptId1")
    private Concept domainConcept1;
    
    public Concept getDomainConcept1() {
        return this.domainConcept1;
    }

    public Long getDomainConceptId2() {
        return this.compoundId.domainConceptId2;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id_2")
    @MapsId("domainConceptId2")
    private Concept domainConcept2;
    
    public Concept getDomainConcept2() {
        return this.domainConcept2;
    }

    @Column(name = "fact_id_1", insertable = false, updatable = false,
            nullable = false)
    private Long factId1;
    
    public Long getFactId1() {
        return this.factId1;
    }

    @Column(name = "fact_id_2", insertable = false, updatable = false,
            nullable = false)
    private Long factId2;
    
    public Long getFactId2() {
        return this.factId2;
    }

    public Long getRelationshipConceptId() {
        return this.compoundId.relationshipConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_concept_id")
    @MapsId("relationshipConceptId")
    private Concept relationshipConcept;
    
    public Concept getRelationshipConcept() {
        return this.relationshipConcept;
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
