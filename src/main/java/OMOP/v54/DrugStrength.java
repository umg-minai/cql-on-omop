package OMOP.v54;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

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
    return Optional.of(this.denominatorUnitConcept);
  }
  @Column(name = "denominator_value", insertable = false, updatable = false)
  private Float denominatorValue;
  
  public Optional<Float> getDenominatorValue() {
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
    return Optional.of(this.numeratorUnitConcept);
  }
  @Column(name = "numerator_value", insertable = false, updatable = false)
  private Float numeratorValue;
  
  public Optional<Float> getNumeratorValue() {
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
    return Optional.of(this.amountUnitConcept);
  }
  @Column(name = "amount_value", insertable = false, updatable = false)
  private Float amountValue;
  
  public Optional<Float> getAmountValue() {
    if (this.amountValue != null) {
      return Optional.of(this.amountValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "ingredient_concept_id", insertable = false, updatable = false)
  private Integer ingredientConceptId;
  
  public Optional<Integer> getIngredientConceptId() {
    if (this.ingredientConceptId != null) {
      return Optional.of(this.ingredientConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ingredient_concept_id")
  private Concept ingredientConcept;
  
  public Optional<Concept> getIngredientConcept() {
    return Optional.of(this.ingredientConcept);
  }
  @Column(name = "drug_concept_id", insertable = false, updatable = false)
  private Integer drugConceptId;
  
  public Optional<Integer> getDrugConceptId() {
    if (this.drugConceptId != null) {
      return Optional.of(this.drugConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.of(this.drugConcept);
  }
  
}
