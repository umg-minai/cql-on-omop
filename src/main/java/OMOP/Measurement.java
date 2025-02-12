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
@Table(name = "measurement", schema = "cds_cdm")
public class Measurement {

  @Column(name = "meas_event_field_concept_id", insertable = false, updatable = false)
  private Integer measEventFieldConceptId;
  
  public Optional<Integer> getMeasEventFieldConceptId() {
    return Optional.of(this.measEventFieldConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "meas_event_field_concept_id")
  private Concept measEventFieldConcept;
  
  public Optional<Concept> getMeasEventFieldConcept() {
    return Optional.of(this.measEventFieldConcept);
  }
  @Column(name = "measurement_event_id", insertable = false, updatable = false)
  private Integer measurementEventId;
  
  public Optional<Integer> getMeasurementEventId() {
    return Optional.of(this.measurementEventId);
  }
  
  @Column(name = "value_source_value", insertable = false, updatable = false)
  private String valueSourceValue;
  
  public Optional<String> getValueSourceValue() {
    return Optional.of(this.valueSourceValue);
  }
  
  @Column(name = "unit_source_concept_id", insertable = false, updatable = false)
  private Integer unitSourceConceptId;
  
  public Optional<Integer> getUnitSourceConceptId() {
    return Optional.of(this.unitSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "unit_source_concept_id")
  private Concept unitSourceConcept;
  
  public Optional<Concept> getUnitSourceConcept() {
    return Optional.of(this.unitSourceConcept);
  }
  @Column(name = "unit_source_value", insertable = false, updatable = false)
  private String unitSourceValue;
  
  public Optional<String> getUnitSourceValue() {
    return Optional.of(this.unitSourceValue);
  }
  
  @Column(name = "measurement_source_concept_id", insertable = false, updatable = false)
  private Integer measurementSourceConceptId;
  
  public Optional<Integer> getMeasurementSourceConceptId() {
    return Optional.of(this.measurementSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "measurement_source_concept_id")
  private Concept measurementSourceConcept;
  
  public Optional<Concept> getMeasurementSourceConcept() {
    return Optional.of(this.measurementSourceConcept);
  }
  @Column(name = "measurement_source_value", insertable = false, updatable = false)
  private String measurementSourceValue;
  
  public Optional<String> getMeasurementSourceValue() {
    return Optional.of(this.measurementSourceValue);
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
  @Column(name = "range_high", insertable = false, updatable = false)
  private Float rangeHigh;
  
  public Optional<Float> getRangeHigh() {
    return Optional.of(this.rangeHigh);
  }
  
  @Column(name = "range_low", insertable = false, updatable = false)
  private Float rangeLow;
  
  public Optional<Float> getRangeLow() {
    return Optional.of(this.rangeLow);
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
  @Column(name = "value_as_number", insertable = false, updatable = false)
  private Float valueAsNumber;
  
  public Optional<Float> getValueAsNumber() {
    return Optional.of(this.valueAsNumber);
  }
  
  @Column(name = "operator_concept_id", insertable = false, updatable = false)
  private Integer operatorConceptId;
  
  public Optional<Integer> getOperatorConceptId() {
    return Optional.of(this.operatorConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "operator_concept_id")
  private Concept operatorConcept;
  
  public Optional<Concept> getOperatorConcept() {
    return Optional.of(this.operatorConcept);
  }
  @Column(name = "measurement_type_concept_id", insertable = false, updatable = false)
  private Integer measurementTypeConceptId;
  
  public Optional<Integer> getMeasurementTypeConceptId() {
    return Optional.of(this.measurementTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "measurement_type_concept_id")
  private Concept measurementTypeConcept;
  
  public Optional<Concept> getMeasurementTypeConcept() {
    return Optional.of(this.measurementTypeConcept);
  }
  @Column(name = "measurement_time", insertable = false, updatable = false)
  private String measurementTime;
  
  public Optional<String> getMeasurementTime() {
    return Optional.of(this.measurementTime);
  }
  
  @Column(name = "measurement_concept_id", insertable = false, updatable = false)
  private Integer measurementConceptId;
  
  public Optional<Integer> getMeasurementConceptId() {
    return Optional.of(this.measurementConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "measurement_concept_id")
  private Concept measurementConcept;
  
  public Optional<Concept> getMeasurementConcept() {
    return Optional.of(this.measurementConcept);
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
  @Column(name = "measurement_id", insertable = false, updatable = false)
  private Integer measurementId;
  
  public Optional<Integer> getMeasurementId() {
    return Optional.of(this.measurementId);
  }
  
  @Override
  public String toString() {
    return "Measurement{id=" + this.measurementId + "}";
  }
  
  
}
