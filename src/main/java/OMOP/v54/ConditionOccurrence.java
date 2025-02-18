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
@Table(name = "condition_occurrence", schema = "cds_cdm")
public class ConditionOccurrence {

  @Column(name = "condition_status_source_value", insertable = false, updatable = false)
  private String conditionStatusSourceValue;
  
  public Optional<String> getConditionStatusSourceValue() {
    if (this.conditionStatusSourceValue != null) {
      return Optional.of(this.conditionStatusSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_source_concept_id", insertable = false, updatable = false)
  private Integer conditionSourceConceptId;
  
  public Optional<Integer> getConditionSourceConceptId() {
    if (this.conditionSourceConceptId != null) {
      return Optional.of(this.conditionSourceConceptId);
    } else {
      return Optional.empty();
    }
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
    if (this.conditionSourceValue != null) {
      return Optional.of(this.conditionSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    if (this.visitDetailId != null) {
      return Optional.of(this.visitDetailId);
    } else {
      return Optional.empty();
    }
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
    if (this.visitOccurrenceId != null) {
      return Optional.of(this.visitOccurrenceId);
    } else {
      return Optional.empty();
    }
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
    if (this.providerId != null) {
      return Optional.of(this.providerId);
    } else {
      return Optional.empty();
    }
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
    if (this.stopReason != null) {
      return Optional.of(this.stopReason);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_status_concept_id", insertable = false, updatable = false)
  private Integer conditionStatusConceptId;
  
  public Optional<Integer> getConditionStatusConceptId() {
    if (this.conditionStatusConceptId != null) {
      return Optional.of(this.conditionStatusConceptId);
    } else {
      return Optional.empty();
    }
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
    if (this.conditionTypeConceptId != null) {
      return Optional.of(this.conditionTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_type_concept_id")
  private Concept conditionTypeConcept;
  
  public Optional<Concept> getConditionTypeConcept() {
    return Optional.of(this.conditionTypeConcept);
  }
  @Column(name = "condition_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime conditionEndDatetime;
  
  public Optional<DateTime> getConditionEndDatetime() {
    if (this.conditionEndDatetime != null) {
      return Optional.of(new DateTime(this.conditionEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_end_date", insertable = false, updatable = false)
  private ZonedDateTime conditionEndDate;
  
  public Optional<Date> getConditionEndDate() {
    if (this.conditionEndDate != null) {
      return Optional.of(new Date(this.conditionEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime conditionStartDatetime;
  
  public Optional<DateTime> getConditionStartDatetime() {
    if (this.conditionStartDatetime != null) {
      return Optional.of(new DateTime(this.conditionStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_start_date", insertable = false, updatable = false)
  private ZonedDateTime conditionStartDate;
  
  public Optional<Date> getConditionStartDate() {
    if (this.conditionStartDate != null) {
      return Optional.of(new Date(this.conditionStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_concept_id", insertable = false, updatable = false)
  private Integer conditionConceptId;
  
  public Optional<Integer> getConditionConceptId() {
    if (this.conditionConceptId != null) {
      return Optional.of(this.conditionConceptId);
    } else {
      return Optional.empty();
    }
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
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "condition_occurrence_id", insertable = false, updatable = false)
  private Integer conditionOccurrenceId;
  
  public Optional<Integer> getConditionOccurrenceId() {
    if (this.conditionOccurrenceId != null) {
      return Optional.of(this.conditionOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "ConditionOccurrence{id=" + this.conditionOccurrenceId + "}";
  }
  
  
}
