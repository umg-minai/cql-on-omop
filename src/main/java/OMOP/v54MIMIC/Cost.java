// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
package OMOP.v54MIMIC;

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
@Table(name = "cost")
public class Cost {

    @Column(name = "amount_allowed", updatable = false, nullable = true)
    private BigDecimal amountAllowed;
    
    public Optional<BigDecimal> getAmountAllowed() {
        if (this.amountAllowed != null) {
            return Optional.of(this.amountAllowed);
        } else {
            return Optional.empty();
        }
    }

    public void setAmountAllowed(final BigDecimal newValue) {
        this.amountAllowed = newValue;
    }

    @Column(name = "cost_domain_id", updatable = false, nullable = false)
    private String costDomainId;
    
    public String getCostDomainId() {
        return this.costDomainId;
    }

    @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_domain_id", insertable = false, updatable = false)
    private Domain costDomain;
    
    public Domain getCostDomain() {
        return this.costDomain;
    }

    public void setCostDomain(final Domain newValue) {
        this.costDomain = newValue;
        this.costDomainId = newValue.getDomainId();
    }

    @Column(name = "cost_event_id", updatable = false, nullable = false)
    private Long costEventId;
    
    public Long getCostEventId() {
        return this.costEventId;
    }

    public void setCostEventId(final Long newValue) {
        this.costEventId = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_id", updatable = false, nullable = false)
    private Long costId;
    
    public Long getCostId() {
        return this.costId;
    }

    @Column(name = "cost_type_concept_id", updatable = false, nullable = false)
    private Integer costTypeConceptId;
    
    public Integer getCostTypeConceptId() {
        return this.costTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_type_concept_id", insertable = false,
                updatable = false)
    private Concept costTypeConcept;
    
    public Concept getCostTypeConcept() {
        return this.costTypeConcept;
    }

    public void setCostTypeConcept(final Concept newValue) {
        this.costTypeConcept = newValue;
        this.costTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "currency_concept_id", updatable = false, nullable = true)
    private Integer currencyConceptId;
    
    public Optional<Integer> getCurrencyConceptId() {
        if (this.currencyConceptId != null) {
            return Optional.of(this.currencyConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_concept_id", insertable = false,
                updatable = false)
    private Concept currencyConcept;
    
    public Optional<Concept> getCurrencyConcept() {
        return Optional.ofNullable(this.currencyConcept);
    }

    public void setCurrencyConcept(final Concept newValue) {
        if (newValue == null) {
            this.currencyConcept = null;
            this.currencyConceptId = null;
        } else {
            this.currencyConcept = newValue;
            this.currencyConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "drg_concept_id", updatable = false, nullable = true)
    private Integer drgConceptId;
    
    public Optional<Integer> getDrgConceptId() {
        if (this.drgConceptId != null) {
            return Optional.of(this.drgConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drg_concept_id", insertable = false, updatable = false)
    private Concept drgConcept;
    
    public Optional<Concept> getDrgConcept() {
        return Optional.ofNullable(this.drgConcept);
    }

    public void setDrgConcept(final Concept newValue) {
        if (newValue == null) {
            this.drgConcept = null;
            this.drgConceptId = null;
        } else {
            this.drgConcept = newValue;
            this.drgConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "drg_source_value", updatable = false, nullable = true)
    private String drgSourceValue;
    
    public Optional<String> getDrgSourceValue() {
        if (this.drgSourceValue != null) {
            return Optional.of(this.drgSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDrgSourceValue(final String newValue) {
        this.drgSourceValue = newValue;
    }

    @Column(name = "paid_by_patient", updatable = false, nullable = true)
    private BigDecimal paidByPatient;
    
    public Optional<BigDecimal> getPaidByPatient() {
        if (this.paidByPatient != null) {
            return Optional.of(this.paidByPatient);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidByPatient(final BigDecimal newValue) {
        this.paidByPatient = newValue;
    }

    @Column(name = "paid_by_payer", updatable = false, nullable = true)
    private BigDecimal paidByPayer;
    
    public Optional<BigDecimal> getPaidByPayer() {
        if (this.paidByPayer != null) {
            return Optional.of(this.paidByPayer);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidByPayer(final BigDecimal newValue) {
        this.paidByPayer = newValue;
    }

    @Column(name = "paid_by_primary", updatable = false, nullable = true)
    private BigDecimal paidByPrimary;
    
    public Optional<BigDecimal> getPaidByPrimary() {
        if (this.paidByPrimary != null) {
            return Optional.of(this.paidByPrimary);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidByPrimary(final BigDecimal newValue) {
        this.paidByPrimary = newValue;
    }

    @Column(name = "paid_dispensing_fee", updatable = false, nullable = true)
    private BigDecimal paidDispensingFee;
    
    public Optional<BigDecimal> getPaidDispensingFee() {
        if (this.paidDispensingFee != null) {
            return Optional.of(this.paidDispensingFee);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidDispensingFee(final BigDecimal newValue) {
        this.paidDispensingFee = newValue;
    }

    @Column(name = "paid_ingredient_cost", updatable = false, nullable = true)
    private BigDecimal paidIngredientCost;
    
    public Optional<BigDecimal> getPaidIngredientCost() {
        if (this.paidIngredientCost != null) {
            return Optional.of(this.paidIngredientCost);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidIngredientCost(final BigDecimal newValue) {
        this.paidIngredientCost = newValue;
    }

    @Column(name = "paid_patient_coinsurance", updatable = false,
            nullable = true)
    private BigDecimal paidPatientCoinsurance;
    
    public Optional<BigDecimal> getPaidPatientCoinsurance() {
        if (this.paidPatientCoinsurance != null) {
            return Optional.of(this.paidPatientCoinsurance);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidPatientCoinsurance(final BigDecimal newValue) {
        this.paidPatientCoinsurance = newValue;
    }

    @Column(name = "paid_patient_copay", updatable = false, nullable = true)
    private BigDecimal paidPatientCopay;
    
    public Optional<BigDecimal> getPaidPatientCopay() {
        if (this.paidPatientCopay != null) {
            return Optional.of(this.paidPatientCopay);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidPatientCopay(final BigDecimal newValue) {
        this.paidPatientCopay = newValue;
    }

    @Column(name = "paid_patient_deductible", updatable = false,
            nullable = true)
    private BigDecimal paidPatientDeductible;
    
    public Optional<BigDecimal> getPaidPatientDeductible() {
        if (this.paidPatientDeductible != null) {
            return Optional.of(this.paidPatientDeductible);
        } else {
            return Optional.empty();
        }
    }

    public void setPaidPatientDeductible(final BigDecimal newValue) {
        this.paidPatientDeductible = newValue;
    }

    @Column(name = "payer_plan_period_id", updatable = false, nullable = true)
    private Long payerPlanPeriodId;
    
    public Optional<Long> getPayerPlanPeriodId() {
        if (this.payerPlanPeriodId != null) {
            return Optional.of(this.payerPlanPeriodId);
        } else {
            return Optional.empty();
        }
    }

    public void setPayerPlanPeriodId(final Long newValue) {
        this.payerPlanPeriodId = newValue;
    }

    @Column(name = "revenue_code_concept_id", updatable = false,
            nullable = true)
    private Integer revenueCodeConceptId;
    
    public Optional<Integer> getRevenueCodeConceptId() {
        if (this.revenueCodeConceptId != null) {
            return Optional.of(this.revenueCodeConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "revenue_code_concept_id", insertable = false,
                updatable = false)
    private Concept revenueCodeConcept;
    
    public Optional<Concept> getRevenueCodeConcept() {
        return Optional.ofNullable(this.revenueCodeConcept);
    }

    public void setRevenueCodeConcept(final Concept newValue) {
        if (newValue == null) {
            this.revenueCodeConcept = null;
            this.revenueCodeConceptId = null;
        } else {
            this.revenueCodeConcept = newValue;
            this.revenueCodeConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "revenue_code_source_value", updatable = false,
            nullable = true)
    private String revenueCodeSourceValue;
    
    public Optional<String> getRevenueCodeSourceValue() {
        if (this.revenueCodeSourceValue != null) {
            return Optional.of(this.revenueCodeSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setRevenueCodeSourceValue(final String newValue) {
        this.revenueCodeSourceValue = newValue;
    }

    @Column(name = "total_charge", updatable = false, nullable = true)
    private BigDecimal totalCharge;
    
    public Optional<BigDecimal> getTotalCharge() {
        if (this.totalCharge != null) {
            return Optional.of(this.totalCharge);
        } else {
            return Optional.empty();
        }
    }

    public void setTotalCharge(final BigDecimal newValue) {
        this.totalCharge = newValue;
    }

    @Column(name = "total_cost", updatable = false, nullable = true)
    private BigDecimal totalCost;
    
    public Optional<BigDecimal> getTotalCost() {
        if (this.totalCost != null) {
            return Optional.of(this.totalCost);
        } else {
            return Optional.empty();
        }
    }

    public void setTotalCost(final BigDecimal newValue) {
        this.totalCost = newValue;
    }

    @Column(name = "total_paid", updatable = false, nullable = true)
    private BigDecimal totalPaid;
    
    public Optional<BigDecimal> getTotalPaid() {
        if (this.totalPaid != null) {
            return Optional.of(this.totalPaid);
        } else {
            return Optional.empty();
        }
    }

    public void setTotalPaid(final BigDecimal newValue) {
        this.totalPaid = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Cost{");
        result.append("id=");
        result.append(this.costId);
        result.append("}");
        return result.toString();
    }


}
