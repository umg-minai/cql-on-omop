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
@Table(name = "condition_occurrence", schema = "cds_cdm")
public class ConditionOccurrence {

  @Column(name = "condition_status_source_value", insertable = false, updatable = false)
  private String conditionStatusSourceValue;
  
  public Optional<String> getConditionStatusSourceValue() {
    return Optional.of(this.conditionStatusSourceValue);
  }
  
  @Column(name = "condition_source_concept_id", insertable = false, updatable = false)
  private Integer conditionSourceConceptId;
  
  public Optional<Integer> getConditionSourceConceptId() {
    return Optional.of(this.conditionSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_source_concept_id")
  private Concept conditionSourceConcept;
  
  public Optional<Concept> getConditionSourceConcept() {
    return Optional.of(this.conditionSourceConcept);
  }
  @Column(name = "condition_source_value", insertable = false, updatable = false)
  private String conditionSourceValue;
  
  public Optional<String> getConditionSourceValue() {
    return Optional.of(this.conditionSourceValue);
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    return Optional.of(this.visitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.of(this.visitDetail);
  }
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    return Optional.of(this.visitOccurrenceId);
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.of(this.visitOccurrence);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    return Optional.of(this.providerId);
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.of(this.provider);
  }
  @Column(name = "stop_reason", insertable = false, updatable = false)
  private String stopReason;
  
  public Optional<String> getStopReason() {
    return Optional.of(this.stopReason);
  }
  
  @Column(name = "condition_status_concept_id", insertable = false, updatable = false)
  private Integer conditionStatusConceptId;
  
  public Optional<Integer> getConditionStatusConceptId() {
    return Optional.of(this.conditionStatusConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_status_concept_id")
  private Concept conditionStatusConcept;
  
  public Optional<Concept> getConditionStatusConcept() {
    return Optional.of(this.conditionStatusConcept);
  }
  @Column(name = "condition_type_concept_id", insertable = false, updatable = false)
  private Integer conditionTypeConceptId;
  
  public Optional<Integer> getConditionTypeConceptId() {
    return Optional.of(this.conditionTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_type_concept_id")
  private Concept conditionTypeConcept;
  
  public Optional<Concept> getConditionTypeConcept() {
    return Optional.of(this.conditionTypeConcept);
  }
  @Column(name = "condition_concept_id", insertable = false, updatable = false)
  private Integer conditionConceptId;
  
  public Optional<Integer> getConditionConceptId() {
    return Optional.of(this.conditionConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_concept_id")
  private Concept conditionConcept;
  
  public Optional<Concept> getConditionConcept() {
    return Optional.of(this.conditionConcept);
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
  @Column(name = "condition_occurrence_id", insertable = false, updatable = false)
  private Integer conditionOccurrenceId;
  
  public Optional<Integer> getConditionOccurrenceId() {
    return Optional.of(this.conditionOccurrenceId);
  }
  
  @Override
  public String toString() {
    return "ConditionOccurrence{id=" + this.conditionOccurrenceId + "}";
  }
  
  
}
