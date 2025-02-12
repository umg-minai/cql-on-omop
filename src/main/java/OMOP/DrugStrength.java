package OMOP;

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

@Entity
@Table(name = "drug_strength", schema = "cds_cdm")
public class DrugStrength {

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    return Optional.of(this.invalidReason);
  }
  
  @Column(name = "box_size", insertable = false, updatable = false)
  private Integer boxSize;
  
  public Optional<Integer> getBoxSize() {
    return Optional.of(this.boxSize);
  }
  
  @Column(name = "denominator_unit_concept_id", insertable = false, updatable = false)
  private Integer denominatorUnitConceptId;
  
  public Optional<Integer> getDenominatorUnitConceptId() {
    return Optional.of(this.denominatorUnitConceptId);
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
    return Optional.of(this.denominatorValue);
  }
  
  @Column(name = "numerator_unit_concept_id", insertable = false, updatable = false)
  private Integer numeratorUnitConceptId;
  
  public Optional<Integer> getNumeratorUnitConceptId() {
    return Optional.of(this.numeratorUnitConceptId);
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
    return Optional.of(this.numeratorValue);
  }
  
  @Column(name = "amount_unit_concept_id", insertable = false, updatable = false)
  private Integer amountUnitConceptId;
  
  public Optional<Integer> getAmountUnitConceptId() {
    return Optional.of(this.amountUnitConceptId);
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
    return Optional.of(this.amountValue);
  }
  
  @Column(name = "ingredient_concept_id", insertable = false, updatable = false)
  private Integer ingredientConceptId;
  
  public Optional<Integer> getIngredientConceptId() {
    return Optional.of(this.ingredientConceptId);
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
    return Optional.of(this.drugConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.of(this.drugConcept);
  }
  
}
