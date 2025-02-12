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
@Table(name = "cost", schema = "cds_cdm")
public class Cost {

  @Column(name = "drg_source_value", insertable = false, updatable = false)
  private String drgSourceValue;
  
  public Optional<String> getDrgSourceValue() {
    return Optional.of(this.drgSourceValue);
  }
  
  @Column(name = "drg_concept_id", insertable = false, updatable = false)
  private Integer drgConceptId;
  
  public Optional<Integer> getDrgConceptId() {
    return Optional.of(this.drgConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drg_concept_id")
  private Concept drgConcept;
  
  public Optional<Concept> getDrgConcept() {
    return Optional.of(this.drgConcept);
  }
  @Column(name = "revenue_code_source_value", insertable = false, updatable = false)
  private String revenueCodeSourceValue;
  
  public Optional<String> getRevenueCodeSourceValue() {
    return Optional.of(this.revenueCodeSourceValue);
  }
  
  @Column(name = "revenue_code_concept_id", insertable = false, updatable = false)
  private Integer revenueCodeConceptId;
  
  public Optional<Integer> getRevenueCodeConceptId() {
    return Optional.of(this.revenueCodeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "revenue_code_concept_id")
  private Concept revenueCodeConcept;
  
  public Optional<Concept> getRevenueCodeConcept() {
    return Optional.of(this.revenueCodeConcept);
  }
  @Column(name = "amount_allowed", insertable = false, updatable = false)
  private Float amountAllowed;
  
  public Optional<Float> getAmountAllowed() {
    return Optional.of(this.amountAllowed);
  }
  
  @Column(name = "payer_plan_period_id", insertable = false, updatable = false)
  private Integer payerPlanPeriodId;
  
  public Optional<Integer> getPayerPlanPeriodId() {
    return Optional.of(this.payerPlanPeriodId);
  }
  
  @Column(name = "paid_dispensing_fee", insertable = false, updatable = false)
  private Float paidDispensingFee;
  
  public Optional<Float> getPaidDispensingFee() {
    return Optional.of(this.paidDispensingFee);
  }
  
  @Column(name = "paid_ingredient_cost", insertable = false, updatable = false)
  private Float paidIngredientCost;
  
  public Optional<Float> getPaidIngredientCost() {
    return Optional.of(this.paidIngredientCost);
  }
  
  @Column(name = "paid_by_primary", insertable = false, updatable = false)
  private Float paidByPrimary;
  
  public Optional<Float> getPaidByPrimary() {
    return Optional.of(this.paidByPrimary);
  }
  
  @Column(name = "paid_patient_deductible", insertable = false, updatable = false)
  private Float paidPatientDeductible;
  
  public Optional<Float> getPaidPatientDeductible() {
    return Optional.of(this.paidPatientDeductible);
  }
  
  @Column(name = "paid_patient_coinsurance", insertable = false, updatable = false)
  private Float paidPatientCoinsurance;
  
  public Optional<Float> getPaidPatientCoinsurance() {
    return Optional.of(this.paidPatientCoinsurance);
  }
  
  @Column(name = "paid_patient_copay", insertable = false, updatable = false)
  private Float paidPatientCopay;
  
  public Optional<Float> getPaidPatientCopay() {
    return Optional.of(this.paidPatientCopay);
  }
  
  @Column(name = "paid_by_patient", insertable = false, updatable = false)
  private Float paidByPatient;
  
  public Optional<Float> getPaidByPatient() {
    return Optional.of(this.paidByPatient);
  }
  
  @Column(name = "paid_by_payer", insertable = false, updatable = false)
  private Float paidByPayer;
  
  public Optional<Float> getPaidByPayer() {
    return Optional.of(this.paidByPayer);
  }
  
  @Column(name = "total_paid", insertable = false, updatable = false)
  private Float totalPaid;
  
  public Optional<Float> getTotalPaid() {
    return Optional.of(this.totalPaid);
  }
  
  @Column(name = "total_cost", insertable = false, updatable = false)
  private Float totalCost;
  
  public Optional<Float> getTotalCost() {
    return Optional.of(this.totalCost);
  }
  
  @Column(name = "total_charge", insertable = false, updatable = false)
  private Float totalCharge;
  
  public Optional<Float> getTotalCharge() {
    return Optional.of(this.totalCharge);
  }
  
  @Column(name = "currency_concept_id", insertable = false, updatable = false)
  private Integer currencyConceptId;
  
  public Optional<Integer> getCurrencyConceptId() {
    return Optional.of(this.currencyConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_concept_id")
  private Concept currencyConcept;
  
  public Optional<Concept> getCurrencyConcept() {
    return Optional.of(this.currencyConcept);
  }
  @Column(name = "cost_type_concept_id", insertable = false, updatable = false)
  private Integer costTypeConceptId;
  
  public Optional<Integer> getCostTypeConceptId() {
    return Optional.of(this.costTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cost_type_concept_id")
  private Concept costTypeConcept;
  
  public Optional<Concept> getCostTypeConcept() {
    return Optional.of(this.costTypeConcept);
  }
  @Column(name = "cost_domain_id", insertable = false, updatable = false)
  private String costDomainId;
  
  public Optional<String> getCostDomainId() {
    return Optional.of(this.costDomainId);
  }
  
  @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cost_domain_id")
  private Domain costDomain;
  
  public Optional<Domain> getCostDomain() {
    return Optional.of(this.costDomain);
  }
  @Column(name = "cost_event_id", insertable = false, updatable = false)
  private Integer costEventId;
  
  public Optional<Integer> getCostEventId() {
    return Optional.of(this.costEventId);
  }
  
  @Id
  @Column(name = "cost_id", insertable = false, updatable = false)
  private Integer costId;
  
  public Optional<Integer> getCostId() {
    return Optional.of(this.costId);
  }
  
  @Override
  public String toString() {
    return "Cost{id=" + this.costId + "}";
  }
  
  
}
