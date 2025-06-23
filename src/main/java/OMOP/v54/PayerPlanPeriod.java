package OMOP.v54;

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
@Table(name = "payer_plan_period", schema = "cds_cdm")
public class PayerPlanPeriod {

    @Column(name = "family_source_value", insertable = false,
            updatable = false, nullable = true)
    private String familySourceValue;
    
    public Optional<String> getFamilySourceValue() {
        if (this.familySourceValue != null) {
            return Optional.of(this.familySourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "payer_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Integer payerConceptId;
    
    public Optional<Integer> getPayerConceptId() {
        if (this.payerConceptId != null) {
            return Optional.of(this.payerConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_concept_id")
    private Concept payerConcept;
    
    public Optional<Concept> getPayerConcept() {
        return Optional.ofNullable(this.payerConcept);
    }

    @Column(name = "payer_plan_period_end_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime payerPlanPeriodEndDate;
    
    public Date getPayerPlanPeriodEndDate() {
        return new Date(this.payerPlanPeriodEndDate.toLocalDate());
    }

    @Id
    @Column(name = "payer_plan_period_id", insertable = false,
            updatable = false, nullable = false)
    private Integer payerPlanPeriodId;
    
    public Integer getPayerPlanPeriodId() {
        return this.payerPlanPeriodId;
    }

    @Column(name = "payer_plan_period_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime payerPlanPeriodStartDate;
    
    public Date getPayerPlanPeriodStartDate() {
        return new Date(this.payerPlanPeriodStartDate.toLocalDate());
    }

    @Column(name = "payer_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer payerSourceConceptId;
    
    public Optional<Integer> getPayerSourceConceptId() {
        if (this.payerSourceConceptId != null) {
            return Optional.of(this.payerSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_source_concept_id")
    private Concept payerSourceConcept;
    
    public Optional<Concept> getPayerSourceConcept() {
        return Optional.ofNullable(this.payerSourceConcept);
    }

    @Column(name = "payer_source_value", insertable = false, updatable = false,
            nullable = true)
    private String payerSourceValue;
    
    public Optional<String> getPayerSourceValue() {
        if (this.payerSourceValue != null) {
            return Optional.of(this.payerSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    @Column(name = "plan_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Integer planConceptId;
    
    public Optional<Integer> getPlanConceptId() {
        if (this.planConceptId != null) {
            return Optional.of(this.planConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_concept_id")
    private Concept planConcept;
    
    public Optional<Concept> getPlanConcept() {
        return Optional.ofNullable(this.planConcept);
    }

    @Column(name = "plan_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer planSourceConceptId;
    
    public Optional<Integer> getPlanSourceConceptId() {
        if (this.planSourceConceptId != null) {
            return Optional.of(this.planSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_source_concept_id")
    private Concept planSourceConcept;
    
    public Optional<Concept> getPlanSourceConcept() {
        return Optional.ofNullable(this.planSourceConcept);
    }

    @Column(name = "plan_source_value", insertable = false, updatable = false,
            nullable = true)
    private String planSourceValue;
    
    public Optional<String> getPlanSourceValue() {
        if (this.planSourceValue != null) {
            return Optional.of(this.planSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "sponsor_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Integer sponsorConceptId;
    
    public Optional<Integer> getSponsorConceptId() {
        if (this.sponsorConceptId != null) {
            return Optional.of(this.sponsorConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_concept_id")
    private Concept sponsorConcept;
    
    public Optional<Concept> getSponsorConcept() {
        return Optional.ofNullable(this.sponsorConcept);
    }

    @Column(name = "sponsor_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer sponsorSourceConceptId;
    
    public Optional<Integer> getSponsorSourceConceptId() {
        if (this.sponsorSourceConceptId != null) {
            return Optional.of(this.sponsorSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_source_concept_id")
    private Concept sponsorSourceConcept;
    
    public Optional<Concept> getSponsorSourceConcept() {
        return Optional.ofNullable(this.sponsorSourceConcept);
    }

    @Column(name = "sponsor_source_value", insertable = false,
            updatable = false, nullable = true)
    private String sponsorSourceValue;
    
    public Optional<String> getSponsorSourceValue() {
        if (this.sponsorSourceValue != null) {
            return Optional.of(this.sponsorSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "stop_reason_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer stopReasonConceptId;
    
    public Optional<Integer> getStopReasonConceptId() {
        if (this.stopReasonConceptId != null) {
            return Optional.of(this.stopReasonConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_reason_concept_id")
    private Concept stopReasonConcept;
    
    public Optional<Concept> getStopReasonConcept() {
        return Optional.ofNullable(this.stopReasonConcept);
    }

    @Column(name = "stop_reason_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer stopReasonSourceConceptId;
    
    public Optional<Integer> getStopReasonSourceConceptId() {
        if (this.stopReasonSourceConceptId != null) {
            return Optional.of(this.stopReasonSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "stop_reason_source_concept_id")
    private Concept stopReasonSourceConcept;
    
    public Optional<Concept> getStopReasonSourceConcept() {
        return Optional.ofNullable(this.stopReasonSourceConcept);
    }

    @Column(name = "stop_reason_source_value", insertable = false,
            updatable = false, nullable = true)
    private String stopReasonSourceValue;
    
    public Optional<String> getStopReasonSourceValue() {
        if (this.stopReasonSourceValue != null) {
            return Optional.of(this.stopReasonSourceValue);
        } else {
            return Optional.empty();
        }
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
