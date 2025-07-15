package OMOP.v53;

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
@Table(name = "device_exposure", schema = "cds_cdm")
public class DeviceExposure {

    @Column(name = "device_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer deviceConceptId;
    
    public Integer getDeviceConceptId() {
        return this.deviceConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_concept_id")
    private Concept deviceConcept;
    
    public Concept getDeviceConcept() {
        return this.deviceConcept;
    }

    public void setDeviceConcept(final Concept newValue) {
        this.deviceConcept = newValue;
        this.deviceConceptId = newValue.getConceptId();
    }

    @Column(name = "device_exposure_end_date", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "device_exposure_end_datetime", insertable = false,
            updatable = false, nullable = true)
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
    @Column(name = "device_exposure_id", insertable = false, updatable = false,
            nullable = false)
    private Integer deviceExposureId;
    
    public Integer getDeviceExposureId() {
        return this.deviceExposureId;
    }

    @Column(name = "device_exposure_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime deviceExposureStartDate;
    
    public Date getDeviceExposureStartDate() {
        return new Date(this.deviceExposureStartDate.toLocalDate());
    }

    public void setDeviceExposureStartDate(final Date newValue) {
        this.deviceExposureStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "device_exposure_start_datetime", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "device_source_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    public void setDeviceSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.deviceSourceConcept = null;
            this.deviceSourceConceptId = null;
        } else {
            this.deviceSourceConcept = newValue;
            this.deviceSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "device_source_value", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "device_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer deviceTypeConceptId;
    
    public Integer getDeviceTypeConceptId() {
        return this.deviceTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_type_concept_id")
    private Concept deviceTypeConcept;
    
    public Concept getDeviceTypeConcept() {
        return this.deviceTypeConcept;
    }

    public void setDeviceTypeConcept(final Concept newValue) {
        this.deviceTypeConcept = newValue;
        this.deviceTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        this.personId = newValue.getPersonId();
    }

    @Column(name = "provider_id", insertable = false, updatable = false,
            nullable = true)
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

    public void setProvider(final Provider newValue) {
        if (newValue == null) {
            this.provider = null;
            this.providerId = null;
        } else {
            this.provider = newValue;
            this.providerId = newValue.getProviderId();
        }
    }

    @Column(name = "quantity", insertable = false, updatable = false,
            nullable = true)
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

    @Column(name = "unique_device_id", insertable = false, updatable = false,
            nullable = true)
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

    @Column(name = "visit_detail_id", insertable = false, updatable = false,
            nullable = true)
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

    public void setVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.visitDetail = null;
            this.visitDetailId = null;
        } else {
            this.visitDetail = newValue;
            this.visitDetailId = newValue.getVisitDetailId();
        }
    }

    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = true)
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
            result.append(this.getDeviceConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
