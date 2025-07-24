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
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

    @Embeddable
    private static class CompoundId {

        @Column(name = "drug_concept_id", updatable = false, nullable = false)
        private Integer drugConceptId;

        @Column(name = "ingredient_concept_id", updatable = false,
                nullable = false)
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
    private CompoundId compoundId = new CompoundId();

    @Column(name = "amount_unit_concept_id", updatable = false, nullable = true)
    private Integer amountUnitConceptId;
    
    public Optional<Integer> getAmountUnitConceptId() {
        if (this.amountUnitConceptId != null) {
            return Optional.of(this.amountUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "amount_unit_concept_id", insertable = false,
                updatable = false)
    private Concept amountUnitConcept;
    
    public Optional<Concept> getAmountUnitConcept() {
        return Optional.ofNullable(this.amountUnitConcept);
    }

    public void setAmountUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.amountUnitConcept = null;
            this.amountUnitConceptId = null;
        } else {
            this.amountUnitConcept = newValue;
            this.amountUnitConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "amount_value", updatable = false, nullable = true)
    private BigDecimal amountValue;
    
    public Optional<BigDecimal> getAmountValue() {
        if (this.amountValue != null) {
            return Optional.of(this.amountValue);
        } else {
            return Optional.empty();
        }
    }

    public void setAmountValue(final BigDecimal newValue) {
        this.amountValue = newValue;
    }

    @Column(name = "box_size", updatable = false, nullable = true)
    private Integer boxSize;
    
    public Optional<Integer> getBoxSize() {
        if (this.boxSize != null) {
            return Optional.of(this.boxSize);
        } else {
            return Optional.empty();
        }
    }

    public void setBoxSize(final Integer newValue) {
        this.boxSize = newValue;
    }

    @Column(name = "denominator_unit_concept_id", updatable = false,
            nullable = true)
    private Integer denominatorUnitConceptId;
    
    public Optional<Integer> getDenominatorUnitConceptId() {
        if (this.denominatorUnitConceptId != null) {
            return Optional.of(this.denominatorUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "denominator_unit_concept_id", insertable = false,
                updatable = false)
    private Concept denominatorUnitConcept;
    
    public Optional<Concept> getDenominatorUnitConcept() {
        return Optional.ofNullable(this.denominatorUnitConcept);
    }

    public void setDenominatorUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.denominatorUnitConcept = null;
            this.denominatorUnitConceptId = null;
        } else {
            this.denominatorUnitConcept = newValue;
            this.denominatorUnitConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "denominator_value", updatable = false, nullable = true)
    private BigDecimal denominatorValue;
    
    public Optional<BigDecimal> getDenominatorValue() {
        if (this.denominatorValue != null) {
            return Optional.of(this.denominatorValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDenominatorValue(final BigDecimal newValue) {
        this.denominatorValue = newValue;
    }

    public Integer getDrugConceptId() {
        return this.compoundId.drugConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id", insertable = false, updatable = false)
    @MapsId("drugConceptId")
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
    }

    public void setDrugConcept(final Concept newValue) {
        this.drugConcept = newValue;
        this.compoundId.drugConceptId = newValue.getConceptId();
    }

    public Integer getIngredientConceptId() {
        return this.compoundId.ingredientConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_concept_id", insertable = false,
                updatable = false)
    @MapsId("ingredientConceptId")
    private Concept ingredientConcept;
    
    public Concept getIngredientConcept() {
        return this.ingredientConcept;
    }

    public void setIngredientConcept(final Concept newValue) {
        this.ingredientConcept = newValue;
        this.compoundId.ingredientConceptId = newValue.getConceptId();
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

    @Column(name = "numerator_unit_concept_id", updatable = false,
            nullable = true)
    private Integer numeratorUnitConceptId;
    
    public Optional<Integer> getNumeratorUnitConceptId() {
        if (this.numeratorUnitConceptId != null) {
            return Optional.of(this.numeratorUnitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "numerator_unit_concept_id", insertable = false,
                updatable = false)
    private Concept numeratorUnitConcept;
    
    public Optional<Concept> getNumeratorUnitConcept() {
        return Optional.ofNullable(this.numeratorUnitConcept);
    }

    public void setNumeratorUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.numeratorUnitConcept = null;
            this.numeratorUnitConceptId = null;
        } else {
            this.numeratorUnitConcept = newValue;
            this.numeratorUnitConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "numerator_value", updatable = false, nullable = true)
    private BigDecimal numeratorValue;
    
    public Optional<BigDecimal> getNumeratorValue() {
        if (this.numeratorValue != null) {
            return Optional.of(this.numeratorValue);
        } else {
            return Optional.empty();
        }
    }

    public void setNumeratorValue(final BigDecimal newValue) {
        this.numeratorValue = newValue;
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
        result.append("DrugStrength{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
