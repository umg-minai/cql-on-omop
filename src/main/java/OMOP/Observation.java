package OMOP;

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

@Entity
@Table(name = "observation", schema = "cds_cdm")
public class Observation {

  @Column(name = "obs_event_field_concept_id", insertable = false, updatable = false)
  private Integer obsEventFieldConceptId;

  public Optional<Integer> getObsEventFieldConceptId() {
    return Optional.of(this.obsEventFieldConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "obs_event_field_concept_id")
  private Concept obsEventFieldConcept;

  public Optional<Concept> getObsEventFieldConcept() {
    return Optional.of(this.obsEventFieldConcept);
  }
  @Column(name = "observation_event_id", insertable = false, updatable = false)
  private Integer observationEventId;

  public Optional<Integer> getObservationEventId() {
    return Optional.of(this.observationEventId);
  }

  @Column(name = "value_source_value", insertable = false, updatable = false)
  private String valueSourceValue;

  public Optional<String> getValueSourceValue() {
    return Optional.of(this.valueSourceValue);
  }

  @Column(name = "qualifier_source_value", insertable = false, updatable = false)
  private String qualifierSourceValue;

  public Optional<String> getQualifierSourceValue() {
    return Optional.of(this.qualifierSourceValue);
  }

  @Column(name = "unit_source_value", insertable = false, updatable = false)
  private String unitSourceValue;

  public Optional<String> getUnitSourceValue() {
    return Optional.of(this.unitSourceValue);
  }

  @Column(name = "observation_source_concept_id", insertable = false, updatable = false)
  private Integer observationSourceConceptId;

  public Optional<Integer> getObservationSourceConceptId() {
    return Optional.of(this.observationSourceConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_source_concept_id")
  private Concept observationSourceConcept;

  public Optional<Concept> getObservationSourceConcept() {
    return Optional.of(this.observationSourceConcept);
  }
  @Column(name = "observation_source_value", insertable = false, updatable = false)
  private String observationSourceValue;

  public Optional<String> getObservationSourceValue() {
    return Optional.of(this.observationSourceValue);
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

  public DateTime getObservationDateTime() { return new DateTime(observationDateTime.toOffsetDateTime()); }
  @Column(name = "observation_datetime")
  private ZonedDateTime observationDateTime;

  public Optional<Provider> getProvider() {
    return Optional.of(this.provider);
  }
  @Column(name = "unit_concept_id", insertable = false, updatable = false)
  private Integer unitConceptId;

  public Optional<Integer> getUnitConceptId() {
    return Optional.of(this.unitConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "unit_concept_id")
  private Concept unitConcept;

  public Optional<Concept> getUnitConcept() {
    return Optional.of(this.unitConcept);
  }
  @Column(name = "qualifier_concept_id", insertable = false, updatable = false)
  private Integer qualifierConceptId;

  public Optional<Integer> getQualifierConceptId() {
    return Optional.of(this.qualifierConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "qualifier_concept_id")
  private Concept qualifierConcept;

  public Optional<Concept> getQualifierConcept() {
    return Optional.of(this.qualifierConcept);
  }
  @Column(name = "value_as_concept_id", insertable = false, updatable = false)
  private Integer valueAsConceptId;

  public Optional<Integer> getValueAsConceptId() {
    return Optional.of(this.valueAsConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "value_as_concept_id")
  private Concept valueAsConcept;

  public Optional<Concept> getValueAsConcept() {
    return Optional.of(this.valueAsConcept);
  }
  @Column(name = "value_as_string", insertable = false, updatable = false)
  private String valueAsString;

  public Optional<String> getValueAsString() {
    return Optional.of(this.valueAsString);
  }

  @Column(name = "value_as_number", insertable = false, updatable = false)
  private Float valueAsNumber;

  public Optional<Float> getValueAsNumber() {
    return Optional.of(this.valueAsNumber);
  }

  @Column(name = "observation_type_concept_id", insertable = false, updatable = false)
  private Integer observationTypeConceptId;

  public Optional<Integer> getObservationTypeConceptId() {
    return Optional.of(this.observationTypeConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_type_concept_id")
  private Concept observationTypeConcept;

  public Optional<Concept> getObservationTypeConcept() {
    return Optional.of(this.observationTypeConcept);
  }
  @Column(name = "observation_concept_id", insertable = false, updatable = false)
  private Integer observationConceptId;

  public Optional<Integer> getObservationConceptId() {
    return Optional.of(this.observationConceptId);
  }

  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "observation_concept_id")
  private Concept observationConcept;

  public Optional<Concept> getObservationConcept() {
    return Optional.of(this.observationConcept);
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
  @Column(name = "observation_id", insertable = false, updatable = false)
  private Integer observationId;

  public Optional<Integer> getObservationId() {
    return Optional.of(this.observationId);
  }

  @Override
  public String toString() {
    return "Observation{id=" + this.observationId + "}";
  }


}
