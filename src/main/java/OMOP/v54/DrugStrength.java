package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

    @Embeddable
    private static class CompoundId {

        @Column(name = "drug_concept_id", insertable = false,
                updatable = false, nullable = true)
        private Integer drugConceptId;
        @Column(name = "ingredient_concept_id", insertable = false,
                updatable = false, nullable = true)
        private Integer ingredientConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.ingredientConceptId, otherInstance.ingredientConceptId)
                            && Objects.equals(this.drugConceptId, otherInstance.drugConceptId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.ingredientConceptId, this.drugConceptId);
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

    public Optional<Integer> getDrugConceptId() {
        if (this.compoundId.drugConceptId != null) {
            return Optional.of(this.compoundId.drugConceptId);
        } else {
            return Optional.empty();
        }

    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id")
    @MapsId("drugConceptId")
    private Concept drugConcept;

    public Optional<Concept> getDrugConcept() {
        return Optional.ofNullable(this.drugConcept);
    }

    public Optional<Integer> getIngredientConceptId() {
        if (this.compoundId.ingredientConceptId != null) {
            return Optional.of(this.compoundId.ingredientConceptId);
        } else {
            return Optional.empty();
        }

    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_concept_id")
    @MapsId("ingredientConceptId")
    private Concept ingredientConcept;

    public Optional<Concept> getIngredientConcept() {
        return Optional.ofNullable(this.ingredientConcept);
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


}
