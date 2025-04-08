package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "cost", schema = "cds_cdm")
public class Cost {

  @Column(name = "drg_source_value", insertable = false, updatable = false)
  private String drgSourceValue;
  
  public Optional<String> getDrgSourceValue() {
    if (this.drgSourceValue != null) {
      return Optional.of(this.drgSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drg_concept_id", insertable = false, updatable = false)
  private Integer drgConceptId;
  
  public Optional<Integer> getDrgConceptId() {
    if (this.drgConceptId != null) {
      return Optional.of(this.drgConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drg_concept_id")
  private Concept drgConcept;
  
  public Optional<Concept> getDrgConcept() {
    return Optional.ofNullable(this.drgConcept);
  }
  @Column(name = "revenue_code_source_value", insertable = false, updatable = false)
  private String revenueCodeSourceValue;
  
  public Optional<String> getRevenueCodeSourceValue() {
    if (this.revenueCodeSourceValue != null) {
      return Optional.of(this.revenueCodeSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "revenue_code_concept_id", insertable = false, updatable = false)
  private Integer revenueCodeConceptId;
  
  public Optional<Integer> getRevenueCodeConceptId() {
    if (this.revenueCodeConceptId != null) {
      return Optional.of(this.revenueCodeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "revenue_code_concept_id")
  private Concept revenueCodeConcept;
  
  public Optional<Concept> getRevenueCodeConcept() {
    return Optional.ofNullable(this.revenueCodeConcept);
  }
  @Column(name = "amount_allowed", insertable = false, updatable = false)
  private BigDecimal amountAllowed;
  
  public Optional<BigDecimal> getAmountAllowed() {
    if (this.amountAllowed != null) {
      return Optional.of(this.amountAllowed);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "payer_plan_period_id", insertable = false, updatable = false)
  private Integer payerPlanPeriodId;
  
  public Optional<Integer> getPayerPlanPeriodId() {
    if (this.payerPlanPeriodId != null) {
      return Optional.of(this.payerPlanPeriodId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_dispensing_fee", insertable = false, updatable = false)
  private BigDecimal paidDispensingFee;
  
  public Optional<BigDecimal> getPaidDispensingFee() {
    if (this.paidDispensingFee != null) {
      return Optional.of(this.paidDispensingFee);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_ingredient_cost", insertable = false, updatable = false)
  private BigDecimal paidIngredientCost;
  
  public Optional<BigDecimal> getPaidIngredientCost() {
    if (this.paidIngredientCost != null) {
      return Optional.of(this.paidIngredientCost);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_by_primary", insertable = false, updatable = false)
  private BigDecimal paidByPrimary;
  
  public Optional<BigDecimal> getPaidByPrimary() {
    if (this.paidByPrimary != null) {
      return Optional.of(this.paidByPrimary);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_patient_deductible", insertable = false, updatable = false)
  private BigDecimal paidPatientDeductible;
  
  public Optional<BigDecimal> getPaidPatientDeductible() {
    if (this.paidPatientDeductible != null) {
      return Optional.of(this.paidPatientDeductible);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_patient_coinsurance", insertable = false, updatable = false)
  private BigDecimal paidPatientCoinsurance;
  
  public Optional<BigDecimal> getPaidPatientCoinsurance() {
    if (this.paidPatientCoinsurance != null) {
      return Optional.of(this.paidPatientCoinsurance);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_patient_copay", insertable = false, updatable = false)
  private BigDecimal paidPatientCopay;
  
  public Optional<BigDecimal> getPaidPatientCopay() {
    if (this.paidPatientCopay != null) {
      return Optional.of(this.paidPatientCopay);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_by_patient", insertable = false, updatable = false)
  private BigDecimal paidByPatient;
  
  public Optional<BigDecimal> getPaidByPatient() {
    if (this.paidByPatient != null) {
      return Optional.of(this.paidByPatient);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "paid_by_payer", insertable = false, updatable = false)
  private BigDecimal paidByPayer;
  
  public Optional<BigDecimal> getPaidByPayer() {
    if (this.paidByPayer != null) {
      return Optional.of(this.paidByPayer);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "total_paid", insertable = false, updatable = false)
  private BigDecimal totalPaid;
  
  public Optional<BigDecimal> getTotalPaid() {
    if (this.totalPaid != null) {
      return Optional.of(this.totalPaid);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "total_cost", insertable = false, updatable = false)
  private BigDecimal totalCost;
  
  public Optional<BigDecimal> getTotalCost() {
    if (this.totalCost != null) {
      return Optional.of(this.totalCost);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "total_charge", insertable = false, updatable = false)
  private BigDecimal totalCharge;
  
  public Optional<BigDecimal> getTotalCharge() {
    if (this.totalCharge != null) {
      return Optional.of(this.totalCharge);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "currency_concept_id", insertable = false, updatable = false)
  private Integer currencyConceptId;
  
  public Optional<Integer> getCurrencyConceptId() {
    if (this.currencyConceptId != null) {
      return Optional.of(this.currencyConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_concept_id")
  private Concept currencyConcept;
  
  public Optional<Concept> getCurrencyConcept() {
    return Optional.ofNullable(this.currencyConcept);
  }
  @Column(name = "cost_type_concept_id", insertable = false, updatable = false)
  private Integer costTypeConceptId;
  
  public Optional<Integer> getCostTypeConceptId() {
    if (this.costTypeConceptId != null) {
      return Optional.of(this.costTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cost_type_concept_id")
  private Concept costTypeConcept;
  
  public Optional<Concept> getCostTypeConcept() {
    return Optional.ofNullable(this.costTypeConcept);
  }
  @Column(name = "cost_domain_id", insertable = false, updatable = false)
  private String costDomainId;
  
  public Optional<String> getCostDomainId() {
    if (this.costDomainId != null) {
      return Optional.of(this.costDomainId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cost_domain_id")
  private Domain costDomain;
  
  public Optional<Domain> getCostDomain() {
    return Optional.ofNullable(this.costDomain);
  }
  @Column(name = "cost_event_id", insertable = false, updatable = false)
  private Integer costEventId;
  
  public Optional<Integer> getCostEventId() {
    if (this.costEventId != null) {
      return Optional.of(this.costEventId);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "cost_id", insertable = false, updatable = false)
  private Integer costId;
  
  public Optional<Integer> getCostId() {
    if (this.costId != null) {
      return Optional.of(this.costId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("Cost{id=").append(this.costId);
      result.append("}");
      return result.toString();
  }
  
}
