package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Entity
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

  @Embeddable
  private static class CompoundId {

    @Column(name = "ingredient_concept_id")
    public Integer ingredientConceptId;

    @Column(name = "drug_concept_id")
    public Integer drugConceptId;

  }

  @EmbeddedId
  private CompoundId compoundId;

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    if (this.invalidReason != null) {
      return Optional.of(this.invalidReason);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_end_date", insertable = false, updatable = false)
  private ZonedDateTime validEndDate;
  
  public Optional<Date> getValidEndDate() {
    if (this.validEndDate != null) {
      return Optional.of(new Date(this.validEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_start_date", insertable = false, updatable = false)
  private ZonedDateTime validStartDate;
  
  public Optional<Date> getValidStartDate() {
    if (this.validStartDate != null) {
      return Optional.of(new Date(this.validStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "box_size", insertable = false, updatable = false)
  private Integer boxSize;
  
  public Optional<Integer> getBoxSize() {
    if (this.boxSize != null) {
      return Optional.of(this.boxSize);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "denominator_unit_concept_id", insertable = false, updatable = false)
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
  @Column(name = "denominator_value", insertable = false, updatable = false)
  private BigDecimal denominatorValue;
  
  public Optional<BigDecimal> getDenominatorValue() {
    if (this.denominatorValue != null) {
      return Optional.of(this.denominatorValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "numerator_unit_concept_id", insertable = false, updatable = false)
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
  @Column(name = "numerator_value", insertable = false, updatable = false)
  private BigDecimal numeratorValue;
  
  public Optional<BigDecimal> getNumeratorValue() {
    if (this.numeratorValue != null) {
      return Optional.of(this.numeratorValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "amount_unit_concept_id", insertable = false, updatable = false)
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
  @Column(name = "amount_value", insertable = false, updatable = false)
  private BigDecimal amountValue;
  
  public Optional<BigDecimal> getAmountValue() {
    if (this.amountValue != null) {
      return Optional.of(this.amountValue);
    } else {
      return Optional.empty();
    }
  }

  public Optional<Integer> getIngredientConceptId() {
    /*if (this.ingredientConceptId != null) {
      return Optional.of(this.ingredientConceptId);
    } else {
      return Optional.empty();
    }*/
    return Optional.of(this.compoundId.ingredientConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @MapsId("ingredientConceptId")
  @JoinColumn(name = "ingredient_concept_id")
  private Concept ingredientConcept;
  
  public Optional<Concept> getIngredientConcept() {
    return Optional.ofNullable(this.ingredientConcept);
  }
  
  public Optional<Integer> getDrugConceptId() {
    /*if (this.drugConceptId != null) {
      return Optional.of(this.drugConceptId);
    } else {
      return Optional.empty();
    }*/
    return Optional.of(this.compoundId.drugConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @MapsId("drugConceptId")
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.ofNullable(this.drugConcept);
  }
  
  
}
