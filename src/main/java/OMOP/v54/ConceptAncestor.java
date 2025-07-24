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
@Table(name = "concept_ancestor", schema = "cds_cdm")
public class ConceptAncestor {

    @Embeddable
    private static class CompoundId {

        @Column(name = "ancestor_concept_id", updatable = false,
                nullable = false)
        private Integer ancestorConceptId;

        @Column(name = "descendant_concept_id", updatable = false,
                nullable = false)
        private Integer descendantConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.ancestorConceptId, otherInstance.ancestorConceptId)
                            && Objects.equals(this.descendantConceptId, otherInstance.descendantConceptId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.ancestorConceptId, this.descendantConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("ancestorConceptId=");
            result.append(this.ancestorConceptId);
            result.append(", ");
            result.append("descendantConceptId=");
            result.append(this.descendantConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getAncestorConceptId() {
        return this.compoundId.ancestorConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ancestor_concept_id", insertable = false,
                updatable = false)
    @MapsId("ancestorConceptId")
    private Concept ancestorConcept;
    
    public Concept getAncestorConcept() {
        return this.ancestorConcept;
    }

    public void setAncestorConcept(final Concept newValue) {
        this.ancestorConcept = newValue;
        this.compoundId.ancestorConceptId = newValue.getConceptId();
    }

    public Integer getDescendantConceptId() {
        return this.compoundId.descendantConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "descendant_concept_id", insertable = false,
                updatable = false)
    @MapsId("descendantConceptId")
    private Concept descendantConcept;
    
    public Concept getDescendantConcept() {
        return this.descendantConcept;
    }

    public void setDescendantConcept(final Concept newValue) {
        this.descendantConcept = newValue;
        this.compoundId.descendantConceptId = newValue.getConceptId();
    }

    @Column(name = "max_levels_of_separation", updatable = false,
            nullable = false)
    private Integer maxLevelsOfSeparation;
    
    public Integer getMaxLevelsOfSeparation() {
        return this.maxLevelsOfSeparation;
    }

    public void setMaxLevelsOfSeparation(final Integer newValue) {
        this.maxLevelsOfSeparation = newValue;
    }

    @Column(name = "min_levels_of_separation", updatable = false,
            nullable = false)
    private Integer minLevelsOfSeparation;
    
    public Integer getMinLevelsOfSeparation() {
        return this.minLevelsOfSeparation;
    }

    public void setMinLevelsOfSeparation(final Integer newValue) {
        this.minLevelsOfSeparation = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptAncestor{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
