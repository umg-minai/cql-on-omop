// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
package OMOP.v54MIMIC;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "device_exposure")
public class DeviceExposure {

    @Column(name = "device_concept_id", updatable = false, nullable = false)
    private Integer deviceConceptId;
    
    public Integer getDeviceConceptId() {
        return this.deviceConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDeviceConceptId(final Integer newValue) {
        this.deviceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_concept_id", insertable = false,
                updatable = false)
    private Concept deviceConcept;
    
    public Concept getDeviceConcept() {
        return this.deviceConcept;
    }

    public void setDeviceConcept(final Concept newValue) {
        this.deviceConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.deviceConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "device_exposure_end_date", updatable = false,
            nullable = true)
    private ZonedDateTime deviceExposureEndDate;
    
    public Optional<Date> getDeviceExposureEndDate() {
        if (this.deviceExposureEndDate != null) {
            return Optional.of(new Date(this.deviceExposureEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setDeviceExposureEndDate(final Date newValue) {
        if (newValue == null) {
            this.deviceExposureEndDate = null;
        } else {
            this.deviceExposureEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    @Column(name = "device_exposure_end_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime deviceExposureEndDatetime;
    
    public Optional<DateTime> getDeviceExposureEndDatetime() {
        if (this.deviceExposureEndDatetime != null) {
            return Optional.of(new DateTime(this.deviceExposureEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setDeviceExposureEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.deviceExposureEndDatetime = null;
        } else {
            this.deviceExposureEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_exposure_id", updatable = false, nullable = false)
    private Long deviceExposureId;
    
    public Long getDeviceExposureId() {
        return this.deviceExposureId;
    }

    @Column(name = "device_exposure_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime deviceExposureStartDate;
    
    public Date getDeviceExposureStartDate() {
        return new Date(this.deviceExposureStartDate.toLocalDate());
    }

    public void setDeviceExposureStartDate(final Date newValue) {
        this.deviceExposureStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "device_exposure_start_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime deviceExposureStartDatetime;
    
    public Optional<DateTime> getDeviceExposureStartDatetime() {
        if (this.deviceExposureStartDatetime != null) {
            return Optional.of(new DateTime(this.deviceExposureStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setDeviceExposureStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.deviceExposureStartDatetime = null;
        } else {
            this.deviceExposureStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "device_source_concept_id", updatable = false,
            nullable = true)
    private Integer deviceSourceConceptId;
    
    public Optional<Integer> getDeviceSourceConceptId() {
        if (this.deviceSourceConceptId != null) {
            return Optional.of(this.deviceSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDeviceSourceConceptId(final Integer newValue) {
        this.deviceSourceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_source_concept_id", insertable = false,
                updatable = false)
    private Concept deviceSourceConcept;
    
    public Optional<Concept> getDeviceSourceConcept() {
        return Optional.ofNullable(this.deviceSourceConcept);
    }

    public void setDeviceSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.deviceSourceConcept = null;
            this.deviceSourceConceptId = null;
        } else {
            this.deviceSourceConcept = newValue;
            this.deviceSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "device_source_value", updatable = false, nullable = true)
    private String deviceSourceValue;
    
    public Optional<String> getDeviceSourceValue() {
        if (this.deviceSourceValue != null) {
            return Optional.of(this.deviceSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDeviceSourceValue(final String newValue) {
        this.deviceSourceValue = newValue;
    }

    @Column(name = "device_type_concept_id", updatable = false,
            nullable = false)
    private Integer deviceTypeConceptId;
    
    public Integer getDeviceTypeConceptId() {
        return this.deviceTypeConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDeviceTypeConceptId(final Integer newValue) {
        this.deviceTypeConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_type_concept_id", insertable = false,
                updatable = false)
    private Concept deviceTypeConcept;
    
    public Concept getDeviceTypeConcept() {
        return this.deviceTypeConcept;
    }

    public void setDeviceTypeConcept(final Concept newValue) {
        this.deviceTypeConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.deviceTypeConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Long personId;
    
    public Long getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.personId = (newValue != null) ? newValue.getPersonId() : 0;
    }

    @Column(name = "production_id", updatable = false, nullable = true)
    private String productionId;
    
    public Optional<String> getProductionId() {
        if (this.productionId != null) {
            return Optional.of(this.productionId);
        } else {
            return Optional.empty();
        }
    }

    public void setProductionId(final String newValue) {
        this.productionId = newValue;
    }

    @Column(name = "provider_id", updatable = false, nullable = true)
    private Long providerId;
    
    public Optional<Long> getProviderId() {
        if (this.providerId != null) {
            return Optional.of(this.providerId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", insertable = false, updatable = false)
    private Provider provider;
    
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(this.provider);
    }

    public void setProvider(final Provider newValue) {
        if (newValue == null) {
            this.provider = null;
            this.providerId = null;
        } else {
            this.provider = newValue;
            this.providerId = newValue.getProviderId();
        }
    }

    @Column(name = "quantity", updatable = false, nullable = true)
    private Integer quantity;
    
    public Optional<Integer> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
        } else {
            return Optional.empty();
        }
    }

    public void setQuantity(final Integer newValue) {
        this.quantity = newValue;
    }

    @Column(name = "unique_device_id", updatable = false, nullable = true)
    private String uniqueDeviceId;
    
    public Optional<String> getUniqueDeviceId() {
        if (this.uniqueDeviceId != null) {
            return Optional.of(this.uniqueDeviceId);
        } else {
            return Optional.empty();
        }
    }

    public void setUniqueDeviceId(final String newValue) {
        this.uniqueDeviceId = newValue;
    }

    @Column(name = "unit_concept_id", updatable = false, nullable = true)
    private Integer unitConceptId;
    
    public Optional<Integer> getUnitConceptId() {
        if (this.unitConceptId != null) {
            return Optional.of(this.unitConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setUnitConceptId(final Integer newValue) {
        this.unitConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_concept_id", insertable = false, updatable = false)
    private Concept unitConcept;
    
    public Optional<Concept> getUnitConcept() {
        return Optional.ofNullable(this.unitConcept);
    }

    public void setUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.unitConcept = null;
            this.unitConceptId = null;
        } else {
            this.unitConcept = newValue;
            this.unitConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "unit_source_concept_id", updatable = false, nullable = true)
    private Integer unitSourceConceptId;
    
    public Optional<Integer> getUnitSourceConceptId() {
        if (this.unitSourceConceptId != null) {
            return Optional.of(this.unitSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setUnitSourceConceptId(final Integer newValue) {
        this.unitSourceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_source_concept_id", insertable = false,
                updatable = false)
    private Concept unitSourceConcept;
    
    public Optional<Concept> getUnitSourceConcept() {
        return Optional.ofNullable(this.unitSourceConcept);
    }

    public void setUnitSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.unitSourceConcept = null;
            this.unitSourceConceptId = null;
        } else {
            this.unitSourceConcept = newValue;
            this.unitSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "unit_source_value", updatable = false, nullable = true)
    private String unitSourceValue;
    
    public Optional<String> getUnitSourceValue() {
        if (this.unitSourceValue != null) {
            return Optional.of(this.unitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setUnitSourceValue(final String newValue) {
        this.unitSourceValue = newValue;
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Long visitDetailId;
    
    public Optional<Long> getVisitDetailId() {
        if (this.visitDetailId != null) {
            return Optional.of(this.visitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_id", insertable = false, updatable = false)
    private VisitDetail visitDetail;
    
    public Optional<VisitDetail> getVisitDetail() {
        return Optional.ofNullable(this.visitDetail);
    }

    public void setVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.visitDetail = null;
            this.visitDetailId = null;
        } else {
            this.visitDetail = newValue;
            this.visitDetailId = newValue.getVisitDetailId();
        }
    }

    @Column(name = "visit_occurrence_id", updatable = false, nullable = true)
    private Long visitOccurrenceId;
    
    public Optional<Long> getVisitOccurrenceId() {
        if (this.visitOccurrenceId != null) {
            return Optional.of(this.visitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id", insertable = false,
                updatable = false)
    private VisitOccurrence visitOccurrence;
    
    public Optional<VisitOccurrence> getVisitOccurrence() {
        return Optional.ofNullable(this.visitOccurrence);
    }

    public void setVisitOccurrence(final VisitOccurrence newValue) {
        if (newValue == null) {
            this.visitOccurrence = null;
            this.visitOccurrenceId = null;
        } else {
            this.visitOccurrence = newValue;
            this.visitOccurrenceId = newValue.getVisitOccurrenceId();
        }
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("DeviceExposure{");
        result.append("id=");
        result.append(this.deviceExposureId);
        {
            result.append(", concept='");
            if (this.getDeviceConcept() != null) {
                result.append(this.getDeviceConcept().getConceptName());

            } else {
                result.append("«broken relation»");

            }result.append("'");

        }
        result.append("}");
        return result.toString();
    }


}
