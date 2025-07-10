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
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

    @Embeddable
    private static class CompoundId {

        @Column(name = "drug_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Integer drugConceptId;

        @Column(name = "ingredient_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Integer ingredientConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.drugConceptId, otherInstance.drugConceptId)
                            && Objects.equals(this.ingredientConceptId, otherInstance.ingredientConceptId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.drugConceptId, this.ingredientConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("drugConceptId=");
            result.append(this.drugConceptId);
            result.append(", ");
            result.append("ingredientConceptId=");
            result.append(this.ingredientConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId;

    @Column(name = "amount_unit_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer amountUnitConceptId;

    public Optional<Integer> getAmountUnitConceptId() {
        if (this.amountUnitConceptId != null) {
            return Optional.of(this.amountUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "amount_unit_concept_id")
    private Concept amountUnitConcept;
    
    public Optional<Concept> getAmountUnitConcept() {
        return Optional.ofNullable(this.amountUnitConcept);
    }

    @Column(name = "amount_value", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal amountValue;
    
    public Optional<BigDecimal> getAmountValue() {
        if (this.amountValue != null) {
            return Optional.of(this.amountValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "box_size", insertable = false, updatable = false,
            nullable = true)
    private Integer boxSize;

    public Optional<Integer> getBoxSize() {
        if (this.boxSize != null) {
            return Optional.of(this.boxSize);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "denominator_unit_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer denominatorUnitConceptId;

    public Optional<Integer> getDenominatorUnitConceptId() {
        if (this.denominatorUnitConceptId != null) {
            return Optional.of(this.denominatorUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "denominator_unit_concept_id")
    private Concept denominatorUnitConcept;
    
    public Optional<Concept> getDenominatorUnitConcept() {
        return Optional.ofNullable(this.denominatorUnitConcept);
    }

    @Column(name = "denominator_value", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal denominatorValue;
    
    public Optional<BigDecimal> getDenominatorValue() {
        if (this.denominatorValue != null) {
            return Optional.of(this.denominatorValue);
        } else {
            return Optional.empty();
        }
    }

    public Integer getDrugConceptId() {
        return this.compoundId.drugConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id")
    @MapsId("drugConceptId")
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
    }

    public Integer getIngredientConceptId() {
        return this.compoundId.ingredientConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_concept_id")
    @MapsId("ingredientConceptId")
    private Concept ingredientConcept;
    
    public Concept getIngredientConcept() {
        return this.ingredientConcept;
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

    @Column(name = "numerator_unit_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer numeratorUnitConceptId;

    public Optional<Integer> getNumeratorUnitConceptId() {
        if (this.numeratorUnitConceptId != null) {
            return Optional.of(this.numeratorUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "numerator_unit_concept_id")
    private Concept numeratorUnitConcept;
    
    public Optional<Concept> getNumeratorUnitConcept() {
        return Optional.ofNullable(this.numeratorUnitConcept);
    }

    @Column(name = "numerator_value", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal numeratorValue;
    
    public Optional<BigDecimal> getNumeratorValue() {
        if (this.numeratorValue != null) {
            return Optional.of(this.numeratorValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "valid_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime validEndDate;
    
    public Date getValidEndDate() {
        return new Date(this.validEndDate.toLocalDate());
    }

    @Column(name = "valid_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime validStartDate;
    
    public Date getValidStartDate() {
        return new Date(this.validStartDate.toLocalDate());
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("DrugStrength{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
