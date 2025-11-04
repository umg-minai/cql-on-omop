package OMOP.v54;

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
@Table(name = "measurement")
public class Measurement {

    @Column(name = "meas_event_field_concept_id", updatable = false,
            nullable = true)
    private Integer measEventFieldConceptId;
    
    public Optional<Integer> getMeasEventFieldConceptId() {
        if (this.measEventFieldConceptId != null) {
            return Optional.of(this.measEventFieldConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "meas_event_field_concept_id", insertable = false,
                updatable = false)
    private Concept measEventFieldConcept;
    
    public Optional<Concept> getMeasEventFieldConcept() {
        return Optional.ofNullable(this.measEventFieldConcept);
    }

    public void setMeasEventFieldConcept(final Concept newValue) {
        if (newValue == null) {
            this.measEventFieldConcept = null;
            this.measEventFieldConceptId = null;
        } else {
            this.measEventFieldConcept = newValue;
            this.measEventFieldConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "measurement_concept_id", updatable = false,
            nullable = false)
    private Integer measurementConceptId;
    
    public Integer getMeasurementConceptId() {
        return this.measurementConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_concept_id", insertable = false,
                updatable = false)
    private Concept measurementConcept;
    
    public Concept getMeasurementConcept() {
        return this.measurementConcept;
    }

    public void setMeasurementConcept(final Concept newValue) {
        this.measurementConcept = newValue;
        this.measurementConceptId = newValue.getConceptId();
    }

    @Column(name = "measurement_date", updatable = false, nullable = false)
    private ZonedDateTime measurementDate;
    
    public Date getMeasurementDate() {
        return new Date(this.measurementDate.toLocalDate());
    }

    public void setMeasurementDate(final Date newValue) {
        this.measurementDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "measurement_datetime", updatable = false, nullable = true)
    private ZonedDateTime measurementDatetime;
    
    public Optional<DateTime> getMeasurementDatetime() {
        if (this.measurementDatetime != null) {
            return Optional.of(new DateTime(this.measurementDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setMeasurementDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.measurementDatetime = null;
        } else {
            this.measurementDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "measurement_event_id", updatable = false, nullable = true)
    private Integer measurementEventId;
    
    public Optional<Integer> getMeasurementEventId() {
        if (this.measurementEventId != null) {
            return Optional.of(this.measurementEventId);
        } else {
            return Optional.empty();
        }
    }

    public void setMeasurementEventId(final Integer newValue) {
        this.measurementEventId = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id", updatable = false, nullable = false)
    private Integer measurementId;
    
    public Integer getMeasurementId() {
        return this.measurementId;
    }

    @Column(name = "measurement_source_concept_id", updatable = false,
            nullable = true)
    private Integer measurementSourceConceptId;
    
    public Optional<Integer> getMeasurementSourceConceptId() {
        if (this.measurementSourceConceptId != null) {
            return Optional.of(this.measurementSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_source_concept_id", insertable = false,
                updatable = false)
    private Concept measurementSourceConcept;
    
    public Optional<Concept> getMeasurementSourceConcept() {
        return Optional.ofNullable(this.measurementSourceConcept);
    }

    public void setMeasurementSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.measurementSourceConcept = null;
            this.measurementSourceConceptId = null;
        } else {
            this.measurementSourceConcept = newValue;
            this.measurementSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "measurement_source_value", updatable = false,
            nullable = true)
    private String measurementSourceValue;
    
    public Optional<String> getMeasurementSourceValue() {
        if (this.measurementSourceValue != null) {
            return Optional.of(this.measurementSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setMeasurementSourceValue(final String newValue) {
        this.measurementSourceValue = newValue;
    }

    @Column(name = "measurement_time", updatable = false, nullable = true)
    private String measurementTime;
    
    public Optional<String> getMeasurementTime() {
        if (this.measurementTime != null) {
            return Optional.of(this.measurementTime);
        } else {
            return Optional.empty();
        }
    }

    public void setMeasurementTime(final String newValue) {
        this.measurementTime = newValue;
    }

    @Column(name = "measurement_type_concept_id", updatable = false,
            nullable = false)
    private Integer measurementTypeConceptId;
    
    public Integer getMeasurementTypeConceptId() {
        return this.measurementTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_type_concept_id", insertable = false,
                updatable = false)
    private Concept measurementTypeConcept;
    
    public Concept getMeasurementTypeConcept() {
        return this.measurementTypeConcept;
    }

    public void setMeasurementTypeConcept(final Concept newValue) {
        this.measurementTypeConcept = newValue;
        this.measurementTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "operator_concept_id", updatable = false, nullable = true)
    private Integer operatorConceptId;
    
    public Optional<Integer> getOperatorConceptId() {
        if (this.operatorConceptId != null) {
            return Optional.of(this.operatorConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_concept_id", insertable = false,
                updatable = false)
    private Concept operatorConcept;
    
    public Optional<Concept> getOperatorConcept() {
        return Optional.ofNullable(this.operatorConcept);
    }

    public void setOperatorConcept(final Concept newValue) {
        if (newValue == null) {
            this.operatorConcept = null;
            this.operatorConceptId = null;
        } else {
            this.operatorConcept = newValue;
            this.operatorConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
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
        this.personId = newValue.getPersonId();
    }

    @Column(name = "provider_id", updatable = false, nullable = true)
    private Integer providerId;
    
    public Optional<Integer> getProviderId() {
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

    @Column(name = "range_high", updatable = false, nullable = true)
    private BigDecimal rangeHigh;
    
    public Optional<BigDecimal> getRangeHigh() {
        if (this.rangeHigh != null) {
            return Optional.of(this.rangeHigh);
        } else {
            return Optional.empty();
        }
    }

    public void setRangeHigh(final BigDecimal newValue) {
        this.rangeHigh = newValue;
    }

    @Column(name = "range_low", updatable = false, nullable = true)
    private BigDecimal rangeLow;
    
    public Optional<BigDecimal> getRangeLow() {
        if (this.rangeLow != null) {
            return Optional.of(this.rangeLow);
        } else {
            return Optional.empty();
        }
    }

    public void setRangeLow(final BigDecimal newValue) {
        this.rangeLow = newValue;
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

    @Column(name = "value_as_concept_id", updatable = false, nullable = true)
    private Integer valueAsConceptId;
    
    public Optional<Integer> getValueAsConceptId() {
        if (this.valueAsConceptId != null) {
            return Optional.of(this.valueAsConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "value_as_concept_id", insertable = false,
                updatable = false)
    private Concept valueAsConcept;
    
    public Optional<Concept> getValueAsConcept() {
        return Optional.ofNullable(this.valueAsConcept);
    }

    public void setValueAsConcept(final Concept newValue) {
        if (newValue == null) {
            this.valueAsConcept = null;
            this.valueAsConceptId = null;
        } else {
            this.valueAsConcept = newValue;
            this.valueAsConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "value_as_number", updatable = false, nullable = true)
    private BigDecimal valueAsNumber;
    
    public Optional<BigDecimal> getValueAsNumber() {
        if (this.valueAsNumber != null) {
            return Optional.of(this.valueAsNumber);
        } else {
            return Optional.empty();
        }
    }

    public void setValueAsNumber(final BigDecimal newValue) {
        this.valueAsNumber = newValue;
    }

    @Column(name = "value_source_value", updatable = false, nullable = true)
    private String valueSourceValue;
    
    public Optional<String> getValueSourceValue() {
        if (this.valueSourceValue != null) {
            return Optional.of(this.valueSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setValueSourceValue(final String newValue) {
        this.valueSourceValue = newValue;
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Integer visitDetailId;
    
    public Optional<Integer> getVisitDetailId() {
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
    private Integer visitOccurrenceId;
    
    public Optional<Integer> getVisitOccurrenceId() {
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
        result.append("Measurement{");
        result.append("id=");
        result.append(this.measurementId);
        {
            result.append(", concept='");
            result.append(this.getMeasurementConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
