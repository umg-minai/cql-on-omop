package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "device_exposure", schema = "cds_cdm")
public class DeviceExposure {

  @Column(name = "unit_source_concept_id", insertable = false, updatable = false)
  private Integer unitSourceConceptId;
  
  public Optional<Integer> getUnitSourceConceptId() {
    if (this.unitSourceConceptId != null) {
      return Optional.of(this.unitSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "unit_source_concept_id")
  private Concept unitSourceConcept;
  
  public Optional<Concept> getUnitSourceConcept() {
    return Optional.ofNullable(this.unitSourceConcept);
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
  @Column(name = "device_source_concept_id", insertable = false, updatable = false)
  private Integer deviceSourceConceptId;
  
  public Optional<Integer> getDeviceSourceConceptId() {
    if (this.deviceSourceConceptId != null) {
      return Optional.of(this.deviceSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_source_concept_id")
  private Concept deviceSourceConcept;
  
  public Optional<Concept> getDeviceSourceConcept() {
    return Optional.ofNullable(this.deviceSourceConcept);
  }
  @Column(name = "device_source_value", insertable = false, updatable = false)
  private String deviceSourceValue;
  
  public Optional<String> getDeviceSourceValue() {
    if (this.deviceSourceValue != null) {
      return Optional.of(this.deviceSourceValue);
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
  @Column(name = "quantity", insertable = false, updatable = false)
  private Integer quantity;
  
  public Optional<Integer> getQuantity() {
    if (this.quantity != null) {
      return Optional.of(this.quantity);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "production_id", insertable = false, updatable = false)
  private String productionId;
  
  public Optional<String> getProductionId() {
    if (this.productionId != null) {
      return Optional.of(this.productionId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "unique_device_id", insertable = false, updatable = false)
  private String uniqueDeviceId;
  
  public Optional<String> getUniqueDeviceId() {
    if (this.uniqueDeviceId != null) {
      return Optional.of(this.uniqueDeviceId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "device_type_concept_id", insertable = false, updatable = false)
  private Integer deviceTypeConceptId;
  
  public Optional<Integer> getDeviceTypeConceptId() {
    if (this.deviceTypeConceptId != null) {
      return Optional.of(this.deviceTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_type_concept_id")
  private Concept deviceTypeConcept;
  
  public Optional<Concept> getDeviceTypeConcept() {
    return Optional.ofNullable(this.deviceTypeConcept);
  }
  @Column(name = "device_exposure_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime deviceExposureEndDatetime;
  
  public Optional<DateTime> getDeviceExposureEndDatetime() {
    if (this.deviceExposureEndDatetime != null) {
      return Optional.of(new DateTime(this.deviceExposureEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "device_exposure_end_date", insertable = false, updatable = false)
  private ZonedDateTime deviceExposureEndDate;
  
  public Optional<Date> getDeviceExposureEndDate() {
    if (this.deviceExposureEndDate != null) {
      return Optional.of(new Date(this.deviceExposureEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "device_exposure_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime deviceExposureStartDatetime;
  
  public Optional<DateTime> getDeviceExposureStartDatetime() {
    if (this.deviceExposureStartDatetime != null) {
      return Optional.of(new DateTime(this.deviceExposureStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "device_exposure_start_date", insertable = false, updatable = false)
  private ZonedDateTime deviceExposureStartDate;
  
  public Optional<Date> getDeviceExposureStartDate() {
    if (this.deviceExposureStartDate != null) {
      return Optional.of(new Date(this.deviceExposureStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "device_concept_id", insertable = false, updatable = false)
  private Integer deviceConceptId;
  
  public Optional<Integer> getDeviceConceptId() {
    if (this.deviceConceptId != null) {
      return Optional.of(this.deviceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "device_concept_id")
  private Concept deviceConcept;
  
  public Optional<Concept> getDeviceConcept() {
    return Optional.ofNullable(this.deviceConcept);
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
  @Column(name = "device_exposure_id", insertable = false, updatable = false)
  private Integer deviceExposureId;
  
  public Optional<Integer> getDeviceExposureId() {
    if (this.deviceExposureId != null) {
      return Optional.of(this.deviceExposureId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("DeviceExposure{id=").append(this.deviceExposureId);
      result.append("}");
      return result.toString();
  }
  
}
