// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
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
@Table(name = "payer_plan_period")
public class PayerPlanPeriod {

    @Column(name = "family_source_value", updatable = false, nullable = true)
    private String familySourceValue;
    
    public Optional<String> getFamilySourceValue() {
        if (this.familySourceValue != null) {
            return Optional.of(this.familySourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setFamilySourceValue(final String newValue) {
        this.familySourceValue = newValue;
    }

    @Column(name = "payer_concept_id", updatable = false, nullable = true)
    private Integer payerConceptId;
    
    public Optional<Integer> getPayerConceptId() {
        if (this.payerConceptId != null) {
            return Optional.of(this.payerConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_concept_id", insertable = false,
                updatable = false)
    private Concept payerConcept;
    
    public Optional<Concept> getPayerConcept() {
        return Optional.ofNullable(this.payerConcept);
    }

    public void setPayerConcept(final Concept newValue) {
        if (newValue == null) {
            this.payerConcept = null;
            this.payerConceptId = null;
        } else {
            this.payerConcept = newValue;
            this.payerConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "payer_plan_period_end_date", updatable = false,
            nullable = false)
    private ZonedDateTime payerPlanPeriodEndDate;
    
    public Date getPayerPlanPeriodEndDate() {
        return new Date(this.payerPlanPeriodEndDate.toLocalDate());
    }

    public void setPayerPlanPeriodEndDate(final Date newValue) {
        this.payerPlanPeriodEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payer_plan_period_id", updatable = false, nullable = false)
    private Integer payerPlanPeriodId;
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
    }

    @Column(name = "payer_plan_period_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime payerPlanPeriodStartDate;
    
    public Date getPayerPlanPeriodStartDate() {
        return new Date(this.payerPlanPeriodStartDate.toLocalDate());
    }

    public void setPayerPlanPeriodStartDate(final Date newValue) {
        this.payerPlanPeriodStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "payer_source_concept_id", updatable = false,
            nullable = true)
    private Integer payerSourceConceptId;
    
    public Optional<Integer> getPayerSourceConceptId() {
        if (this.payerSourceConceptId != null) {
            return Optional.of(this.payerSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_source_concept_id", insertable = false,
                updatable = false)
    private Concept payerSourceConcept;
    
    public Optional<Concept> getPayerSourceConcept() {
        return Optional.ofNullable(this.payerSourceConcept);
    }

    public void setPayerSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.payerSourceConcept = null;
            this.payerSourceConceptId = null;
        } else {
            this.payerSourceConcept = newValue;
            this.payerSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "payer_source_value", updatable = false, nullable = true)
    private String payerSourceValue;
    
    public Optional<String> getPayerSourceValue() {
        if (this.payerSourceValue != null) {
            return Optional.of(this.payerSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setPayerSourceValue(final String newValue) {
        this.payerSourceValue = newValue;
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.personId = (newValue != null) ? newValue.getPersonId() : 0;
    }

    @Column(name = "plan_concept_id", updatable = false, nullable = true)
    private Integer planConceptId;
    
    public Optional<Integer> getPlanConceptId() {
        if (this.planConceptId != null) {
            return Optional.of(this.planConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_concept_id", insertable = false, updatable = false)
    private Concept planConcept;
    
    public Optional<Concept> getPlanConcept() {
        return Optional.ofNullable(this.planConcept);
    }

    public void setPlanConcept(final Concept newValue) {
        if (newValue == null) {
            this.planConcept = null;
            this.planConceptId = null;
        } else {
            this.planConcept = newValue;
            this.planConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "plan_source_concept_id", updatable = false, nullable = true)
    private Integer planSourceConceptId;
    
    public Optional<Integer> getPlanSourceConceptId() {
        if (this.planSourceConceptId != null) {
            return Optional.of(this.planSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_source_concept_id", insertable = false,
                updatable = false)
    private Concept planSourceConcept;
    
    public Optional<Concept> getPlanSourceConcept() {
        return Optional.ofNullable(this.planSourceConcept);
    }

    public void setPlanSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.planSourceConcept = null;
            this.planSourceConceptId = null;
        } else {
            this.planSourceConcept = newValue;
            this.planSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "plan_source_value", updatable = false, nullable = true)
    private String planSourceValue;
    
    public Optional<String> getPlanSourceValue() {
        if (this.planSourceValue != null) {
            return Optional.of(this.planSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setPlanSourceValue(final String newValue) {
        this.planSourceValue = newValue;
    }

    @Column(name = "sponsor_concept_id", updatable = false, nullable = true)
    private Integer sponsorConceptId;
    
    public Optional<Integer> getSponsorConceptId() {
        if (this.sponsorConceptId != null) {
            return Optional.of(this.sponsorConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_concept_id", insertable = false,
                updatable = false)
    private Concept sponsorConcept;
    
    public Optional<Concept> getSponsorConcept() {
        return Optional.ofNullable(this.sponsorConcept);
    }

    public void setSponsorConcept(final Concept newValue) {
        if (newValue == null) {
            this.sponsorConcept = null;
            this.sponsorConceptId = null;
        } else {
            this.sponsorConcept = newValue;
            this.sponsorConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "sponsor_source_concept_id", updatable = false,
            nullable = true)
    private Integer sponsorSourceConceptId;
    
    public Optional<Integer> getSponsorSourceConceptId() {
        if (this.sponsorSourceConceptId != null) {
            return Optional.of(this.sponsorSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_source_concept_id", insertable = false,
                updatable = false)
    private Concept sponsorSourceConcept;
    
    public Optional<Concept> getSponsorSourceConcept() {
        return Optional.ofNullable(this.sponsorSourceConcept);
    }

    public void setSponsorSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.sponsorSourceConcept = null;
            this.sponsorSourceConceptId = null;
        } else {
            this.sponsorSourceConcept = newValue;
            this.sponsorSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "sponsor_source_value", updatable = false, nullable = true)
    private String sponsorSourceValue;
    
    public Optional<String> getSponsorSourceValue() {
        if (this.sponsorSourceValue != null) {
            return Optional.of(this.sponsorSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setSponsorSourceValue(final String newValue) {
        this.sponsorSourceValue = newValue;
    }

    @Column(name = "stop_reason_concept_id", updatable = false, nullable = true)
    private Integer stopReasonConceptId;
    
    public Optional<Integer> getStopReasonConceptId() {
        if (this.stopReasonConceptId != null) {
            return Optional.of(this.stopReasonConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_reason_concept_id", insertable = false,
                updatable = false)
    private Concept stopReasonConcept;
    
    public Optional<Concept> getStopReasonConcept() {
        return Optional.ofNullable(this.stopReasonConcept);
    }

    public void setStopReasonConcept(final Concept newValue) {
        if (newValue == null) {
            this.stopReasonConcept = null;
            this.stopReasonConceptId = null;
        } else {
            this.stopReasonConcept = newValue;
            this.stopReasonConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "stop_reason_source_concept_id", updatable = false,
            nullable = true)
    private Integer stopReasonSourceConceptId;
    
    public Optional<Integer> getStopReasonSourceConceptId() {
        if (this.stopReasonSourceConceptId != null) {
            return Optional.of(this.stopReasonSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_reason_source_concept_id", insertable = false,
                updatable = false)
    private Concept stopReasonSourceConcept;
    
    public Optional<Concept> getStopReasonSourceConcept() {
        return Optional.ofNullable(this.stopReasonSourceConcept);
    }

    public void setStopReasonSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.stopReasonSourceConcept = null;
            this.stopReasonSourceConceptId = null;
        } else {
            this.stopReasonSourceConcept = newValue;
            this.stopReasonSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "stop_reason_source_value", updatable = false,
            nullable = true)
    private String stopReasonSourceValue;
    
    public Optional<String> getStopReasonSourceValue() {
        if (this.stopReasonSourceValue != null) {
            return Optional.of(this.stopReasonSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setStopReasonSourceValue(final String newValue) {
        this.stopReasonSourceValue = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("PayerPlanPeriod{");
        result.append("id=");
        result.append(this.payerPlanPeriodId);
        result.append("}");
        return result.toString();
    }


}
