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
@Table(name = "payer_plan_period", schema = "cds_cdm")
public class PayerPlanPeriod {

  @Column(name = "stop_reason_source_concept_id", insertable = false, updatable = false)
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
  @Column(name = "stop_reason_source_value", insertable = false, updatable = false)
  private String stopReasonSourceValue;
  
  public Optional<String> getStopReasonSourceValue() {
    if (this.stopReasonSourceValue != null) {
      return Optional.of(this.stopReasonSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "stop_reason_concept_id", insertable = false, updatable = false)
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
  @Column(name = "family_source_value", insertable = false, updatable = false)
  private String familySourceValue;
  
  public Optional<String> getFamilySourceValue() {
    if (this.familySourceValue != null) {
      return Optional.of(this.familySourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "sponsor_source_concept_id", insertable = false, updatable = false)
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
  @Column(name = "sponsor_source_value", insertable = false, updatable = false)
  private String sponsorSourceValue;
  
  public Optional<String> getSponsorSourceValue() {
    if (this.sponsorSourceValue != null) {
      return Optional.of(this.sponsorSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "sponsor_concept_id", insertable = false, updatable = false)
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
  @Column(name = "plan_source_concept_id", insertable = false, updatable = false)
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
  @Column(name = "plan_source_value", insertable = false, updatable = false)
  private String planSourceValue;
  
  public Optional<String> getPlanSourceValue() {
    if (this.planSourceValue != null) {
      return Optional.of(this.planSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "plan_concept_id", insertable = false, updatable = false)
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
  @Column(name = "payer_source_concept_id", insertable = false, updatable = false)
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
  @Column(name = "payer_source_value", insertable = false, updatable = false)
  private String payerSourceValue;
  
  public Optional<String> getPayerSourceValue() {
    if (this.payerSourceValue != null) {
      return Optional.of(this.payerSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "payer_concept_id", insertable = false, updatable = false)
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
  @Column(name = "payer_plan_period_end_date", insertable = false, updatable = false)
  private ZonedDateTime payerPlanPeriodEndDate;
  
  public Optional<Date> getPayerPlanPeriodEndDate() {
    if (this.payerPlanPeriodEndDate != null) {
      return Optional.of(new Date(this.payerPlanPeriodEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "payer_plan_period_start_date", insertable = false, updatable = false)
  private ZonedDateTime payerPlanPeriodStartDate;
  
  public Optional<Date> getPayerPlanPeriodStartDate() {
    if (this.payerPlanPeriodStartDate != null) {
      return Optional.of(new Date(this.payerPlanPeriodStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.ofNullable(this.person);
  }
  @Id
  @Column(name = "payer_plan_period_id", insertable = false, updatable = false)
  private Integer payerPlanPeriodId;
  
  public Optional<Integer> getPayerPlanPeriodId() {
    if (this.payerPlanPeriodId != null) {
      return Optional.of(this.payerPlanPeriodId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("PayerPlanPeriod{id=").append(this.payerPlanPeriodId);
    result.append("}");
    return result.toString();
}
  
}
