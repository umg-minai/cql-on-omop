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
@Table(name = "device_exposure", schema = "cds_cdm")
public class DeviceExposure {

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
  @Column(name = "device_source_concept_id", insertable = false, updatable = false)
  private Integer deviceSourceConceptId;
  
  public Optional<Integer> getDeviceSourceConceptId() {
    return Optional.of(this.deviceSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_source_concept_id")
  private Concept deviceSourceConcept;
  
  public Optional<Concept> getDeviceSourceConcept() {
    return Optional.of(this.deviceSourceConcept);
  }
  @Column(name = "device_source_value", insertable = false, updatable = false)
  private String deviceSourceValue;
  
  public Optional<String> getDeviceSourceValue() {
    return Optional.of(this.deviceSourceValue);
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
  @Column(name = "quantity", insertable = false, updatable = false)
  private Integer quantity;
  
  public Optional<Integer> getQuantity() {
    return Optional.of(this.quantity);
  }
  
  @Column(name = "production_id", insertable = false, updatable = false)
  private String productionId;
  
  public Optional<String> getProductionId() {
    return Optional.of(this.productionId);
  }
  
  @Column(name = "unique_device_id", insertable = false, updatable = false)
  private String uniqueDeviceId;
  
  public Optional<String> getUniqueDeviceId() {
    return Optional.of(this.uniqueDeviceId);
  }
  
  @Column(name = "device_type_concept_id", insertable = false, updatable = false)
  private Integer deviceTypeConceptId;
  
  public Optional<Integer> getDeviceTypeConceptId() {
    return Optional.of(this.deviceTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_type_concept_id")
  private Concept deviceTypeConcept;
  
  public Optional<Concept> getDeviceTypeConcept() {
    return Optional.of(this.deviceTypeConcept);
  }
  @Column(name = "device_concept_id", insertable = false, updatable = false)
  private Integer deviceConceptId;
  
  public Optional<Integer> getDeviceConceptId() {
    return Optional.of(this.deviceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_concept_id")
  private Concept deviceConcept;
  
  public Optional<Concept> getDeviceConcept() {
    return Optional.of(this.deviceConcept);
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
  @Column(name = "device_exposure_id", insertable = false, updatable = false)
  private Integer deviceExposureId;
  
  public Optional<Integer> getDeviceExposureId() {
    return Optional.of(this.deviceExposureId);
  }
  
  @Override
  public String toString() {
    return "DeviceExposure{id=" + this.deviceExposureId + "}";
  }
  
  
}
