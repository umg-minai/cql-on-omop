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
@Table(name = "observation", schema = "cds_cdm")
public class Observation {

  @Column(name = "obs_event_field_concept_id", insertable = false, updatable = false)
  private Integer obsEventFieldConceptId;
  
  public Optional<Integer> getObsEventFieldConceptId() {
    if (this.obsEventFieldConceptId != null) {
      return Optional.of(this.obsEventFieldConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "obs_event_field_concept_id")
  private Concept obsEventFieldConcept;
  
  public Optional<Concept> getObsEventFieldConcept() {
    return Optional.ofNullable(this.obsEventFieldConcept);
  }
  @Column(name = "observation_event_id", insertable = false, updatable = false)
  private Integer observationEventId;
  
  public Optional<Integer> getObservationEventId() {
    if (this.observationEventId != null) {
      return Optional.of(this.observationEventId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "value_source_value", insertable = false, updatable = false)
  private String valueSourceValue;
  
  public Optional<String> getValueSourceValue() {
    if (this.valueSourceValue != null) {
      return Optional.of(this.valueSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "qualifier_source_value", insertable = false, updatable = false)
  private String qualifierSourceValue;
  
  public Optional<String> getQualifierSourceValue() {
    if (this.qualifierSourceValue != null) {
      return Optional.of(this.qualifierSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "unit_source_value", insertable = false, updatable = false)
  private String unitSourceValue;
  
  public Optional<String> getUnitSourceValue() {
    if (this.unitSourceValue != null) {
      return Optional.of(this.unitSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "observation_source_concept_id", insertable = false, updatable = false)
  private Integer observationSourceConceptId;
  
  public Optional<Integer> getObservationSourceConceptId() {
    if (this.observationSourceConceptId != null) {
      return Optional.of(this.observationSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_source_concept_id")
  private Concept observationSourceConcept;
  
  public Optional<Concept> getObservationSourceConcept() {
    return Optional.ofNullable(this.observationSourceConcept);
  }
  @Column(name = "observation_source_value", insertable = false, updatable = false)
  private String observationSourceValue;
  
  public Optional<String> getObservationSourceValue() {
    if (this.observationSourceValue != null) {
      return Optional.of(this.observationSourceValue);
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
    return Optional.ofNullable(this.visitDetail);
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
    return Optional.ofNullable(this.visitOccurrence);
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
    return Optional.ofNullable(this.provider);
  }
  @Column(name = "unit_concept_id", insertable = false, updatable = false)
  private Integer unitConceptId;
  
  public Optional<Integer> getUnitConceptId() {
    if (this.unitConceptId != null) {
      return Optional.of(this.unitConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "unit_concept_id")
  private Concept unitConcept;
  
  public Optional<Concept> getUnitConcept() {
    return Optional.ofNullable(this.unitConcept);
  }
  @Column(name = "qualifier_concept_id", insertable = false, updatable = false)
  private Integer qualifierConceptId;
  
  public Optional<Integer> getQualifierConceptId() {
    if (this.qualifierConceptId != null) {
      return Optional.of(this.qualifierConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "qualifier_concept_id")
  private Concept qualifierConcept;
  
  public Optional<Concept> getQualifierConcept() {
    return Optional.ofNullable(this.qualifierConcept);
  }
  @Column(name = "value_as_concept_id", insertable = false, updatable = false)
  private Integer valueAsConceptId;
  
  public Optional<Integer> getValueAsConceptId() {
    if (this.valueAsConceptId != null) {
      return Optional.of(this.valueAsConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "value_as_concept_id")
  private Concept valueAsConcept;
  
  public Optional<Concept> getValueAsConcept() {
    return Optional.ofNullable(this.valueAsConcept);
  }
  @Column(name = "value_as_string", insertable = false, updatable = false)
  private String valueAsString;
  
  public Optional<String> getValueAsString() {
    if (this.valueAsString != null) {
      return Optional.of(this.valueAsString);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "value_as_number", insertable = false, updatable = false)
  private Float valueAsNumber;
  
  public Optional<Float> getValueAsNumber() {
    if (this.valueAsNumber != null) {
      return Optional.of(this.valueAsNumber);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "observation_type_concept_id", insertable = false, updatable = false)
  private Integer observationTypeConceptId;
  
  public Optional<Integer> getObservationTypeConceptId() {
    if (this.observationTypeConceptId != null) {
      return Optional.of(this.observationTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_type_concept_id")
  private Concept observationTypeConcept;
  
  public Optional<Concept> getObservationTypeConcept() {
    return Optional.ofNullable(this.observationTypeConcept);
  }
  @Column(name = "observation_datetime", insertable = false, updatable = false)
  private ZonedDateTime observationDatetime;
  
  public Optional<DateTime> getObservationDatetime() {
    if (this.observationDatetime != null) {
      return Optional.of(new DateTime(this.observationDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "observation_date", insertable = false, updatable = false)
  private ZonedDateTime observationDate;
  
  public Optional<Date> getObservationDate() {
    if (this.observationDate != null) {
      return Optional.of(new Date(this.observationDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "observation_concept_id", insertable = false, updatable = false)
  private Integer observationConceptId;
  
  public Optional<Integer> getObservationConceptId() {
    if (this.observationConceptId != null) {
      return Optional.of(this.observationConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_concept_id")
  private Concept observationConcept;
  
  public Optional<Concept> getObservationConcept() {
    return Optional.ofNullable(this.observationConcept);
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
  @Column(name = "observation_id", insertable = false, updatable = false)
  private Integer observationId;
  
  public Optional<Integer> getObservationId() {
    if (this.observationId != null) {
      return Optional.of(this.observationId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Observation{id=").append(this.observationId);
    this.getObservationConcept().ifPresent(concept -> {
      result.append(", concept='")
      .append(concept.getConceptName().get())
      .append("'");
    });
    result.append("}");
    return result.toString();
}
  
}
