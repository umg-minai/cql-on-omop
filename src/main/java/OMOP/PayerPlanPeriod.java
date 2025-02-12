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
@Table(name = "payer_plan_period", schema = "cds_cdm")
public class PayerPlanPeriod {

  @Column(name = "stop_reason_source_concept_id", insertable = false, updatable = false)
  private Integer stopReasonSourceConceptId;
  
  public Optional<Integer> getStopReasonSourceConceptId() {
    return Optional.of(this.stopReasonSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "stop_reason_source_concept_id")
  private Concept stopReasonSourceConcept;
  
  public Optional<Concept> getStopReasonSourceConcept() {
    return Optional.of(this.stopReasonSourceConcept);
  }
  @Column(name = "stop_reason_source_value", insertable = false, updatable = false)
  private String stopReasonSourceValue;
  
  public Optional<String> getStopReasonSourceValue() {
    return Optional.of(this.stopReasonSourceValue);
  }
  
  @Column(name = "stop_reason_concept_id", insertable = false, updatable = false)
  private Integer stopReasonConceptId;
  
  public Optional<Integer> getStopReasonConceptId() {
    return Optional.of(this.stopReasonConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "stop_reason_concept_id")
  private Concept stopReasonConcept;
  
  public Optional<Concept> getStopReasonConcept() {
    return Optional.of(this.stopReasonConcept);
  }
  @Column(name = "family_source_value", insertable = false, updatable = false)
  private String familySourceValue;
  
  public Optional<String> getFamilySourceValue() {
    return Optional.of(this.familySourceValue);
  }
  
  @Column(name = "sponsor_source_concept_id", insertable = false, updatable = false)
  private Integer sponsorSourceConceptId;
  
  public Optional<Integer> getSponsorSourceConceptId() {
    return Optional.of(this.sponsorSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "sponsor_source_concept_id")
  private Concept sponsorSourceConcept;
  
  public Optional<Concept> getSponsorSourceConcept() {
    return Optional.of(this.sponsorSourceConcept);
  }
  @Column(name = "sponsor_source_value", insertable = false, updatable = false)
  private String sponsorSourceValue;
  
  public Optional<String> getSponsorSourceValue() {
    return Optional.of(this.sponsorSourceValue);
  }
  
  @Column(name = "sponsor_concept_id", insertable = false, updatable = false)
  private Integer sponsorConceptId;
  
  public Optional<Integer> getSponsorConceptId() {
    return Optional.of(this.sponsorConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "sponsor_concept_id")
  private Concept sponsorConcept;
  
  public Optional<Concept> getSponsorConcept() {
    return Optional.of(this.sponsorConcept);
  }
  @Column(name = "plan_source_concept_id", insertable = false, updatable = false)
  private Integer planSourceConceptId;
  
  public Optional<Integer> getPlanSourceConceptId() {
    return Optional.of(this.planSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_source_concept_id")
  private Concept planSourceConcept;
  
  public Optional<Concept> getPlanSourceConcept() {
    return Optional.of(this.planSourceConcept);
  }
  @Column(name = "plan_source_value", insertable = false, updatable = false)
  private String planSourceValue;
  
  public Optional<String> getPlanSourceValue() {
    return Optional.of(this.planSourceValue);
  }
  
  @Column(name = "plan_concept_id", insertable = false, updatable = false)
  private Integer planConceptId;
  
  public Optional<Integer> getPlanConceptId() {
    return Optional.of(this.planConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_concept_id")
  private Concept planConcept;
  
  public Optional<Concept> getPlanConcept() {
    return Optional.of(this.planConcept);
  }
  @Column(name = "payer_source_concept_id", insertable = false, updatable = false)
  private Integer payerSourceConceptId;
  
  public Optional<Integer> getPayerSourceConceptId() {
    return Optional.of(this.payerSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "payer_source_concept_id")
  private Concept payerSourceConcept;
  
  public Optional<Concept> getPayerSourceConcept() {
    return Optional.of(this.payerSourceConcept);
  }
  @Column(name = "payer_source_value", insertable = false, updatable = false)
  private String payerSourceValue;
  
  public Optional<String> getPayerSourceValue() {
    return Optional.of(this.payerSourceValue);
  }
  
  @Column(name = "payer_concept_id", insertable = false, updatable = false)
  private Integer payerConceptId;
  
  public Optional<Integer> getPayerConceptId() {
    return Optional.of(this.payerConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "payer_concept_id")
  private Concept payerConcept;
  
  public Optional<Concept> getPayerConcept() {
    return Optional.of(this.payerConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    return Optional.of(this.personId);
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "payer_plan_period_id", insertable = false, updatable = false)
  private Integer payerPlanPeriodId;
  
  public Optional<Integer> getPayerPlanPeriodId() {
    return Optional.of(this.payerPlanPeriodId);
  }
  
  @Override
  public String toString() {
    return "PayerPlanPeriod{id=" + this.payerPlanPeriodId + "}";
  }
  
  
}
