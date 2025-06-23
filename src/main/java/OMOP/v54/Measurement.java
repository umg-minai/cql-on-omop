package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "measurement", schema = "cds_cdm")
public class Measurement {

    @Column(name = "meas_event_field_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer measEventFieldConceptId;
    
    public Optional<Integer> getMeasEventFieldConceptId() {
        if (this.measEventFieldConceptId != null) {
            return Optional.of(this.measEventFieldConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "meas_event_field_concept_id")
    private Concept measEventFieldConcept;
    
    public Optional<Concept> getMeasEventFieldConcept() {
        return Optional.ofNullable(this.measEventFieldConcept);
    }

    @Column(name = "measurement_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer measurementConceptId;
    
    public Integer getMeasurementConceptId() {
        return this.measurementConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_concept_id")
    private Concept measurementConcept;
    
    public Concept getMeasurementConcept() {
        return this.measurementConcept;
    }

    @Column(name = "measurement_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime measurementDate;
    
    public Date getMeasurementDate() {
        return new Date(this.measurementDate.toLocalDate());
    }

    @Column(name = "measurement_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime measurementDatetime;
    
    public Optional<DateTime> getMeasurementDatetime() {
        if (this.measurementDatetime != null) {
            return Optional.of(new DateTime(this.measurementDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "measurement_event_id", insertable = false,
            updatable = false, nullable = true)
    private Integer measurementEventId;
    
    public Optional<Integer> getMeasurementEventId() {
        if (this.measurementEventId != null) {
            return Optional.of(this.measurementEventId);
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "measurement_id", insertable = false, updatable = false,
            nullable = false)
    private Integer measurementId;
    
    public Integer getMeasurementId() {
        return this.measurementId;
    }

    @Column(name = "measurement_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer measurementSourceConceptId;
    
    public Optional<Integer> getMeasurementSourceConceptId() {
        if (this.measurementSourceConceptId != null) {
            return Optional.of(this.measurementSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_source_concept_id")
    private Concept measurementSourceConcept;
    
    public Optional<Concept> getMeasurementSourceConcept() {
        return Optional.ofNullable(this.measurementSourceConcept);
    }

    @Column(name = "measurement_source_value", insertable = false,
            updatable = false, nullable = true)
    private String measurementSourceValue;
    
    public Optional<String> getMeasurementSourceValue() {
        if (this.measurementSourceValue != null) {
            return Optional.of(this.measurementSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "measurement_time", insertable = false, updatable = false,
            nullable = true)
    private String measurementTime;
    
    public Optional<String> getMeasurementTime() {
        if (this.measurementTime != null) {
            return Optional.of(this.measurementTime);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "measurement_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer measurementTypeConceptId;
    
    public Integer getMeasurementTypeConceptId() {
        return this.measurementTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_type_concept_id")
    private Concept measurementTypeConcept;
    
    public Concept getMeasurementTypeConcept() {
        return this.measurementTypeConcept;
    }

    @Column(name = "operator_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer operatorConceptId;
    
    public Optional<Integer> getOperatorConceptId() {
        if (this.operatorConceptId != null) {
            return Optional.of(this.operatorConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_concept_id")
    private Concept operatorConcept;
    
    public Optional<Concept> getOperatorConcept() {
        return Optional.ofNullable(this.operatorConcept);
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

    @Column(name = "range_high", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal rangeHigh;
    
    public Optional<BigDecimal> getRangeHigh() {
        if (this.rangeHigh != null) {
            return Optional.of(this.rangeHigh);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "range_low", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal rangeLow;
    
    public Optional<BigDecimal> getRangeLow() {
        if (this.rangeLow != null) {
            return Optional.of(this.rangeLow);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "unit_concept_id", insertable = false, updatable = false,
            nullable = true)
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

    @Column(name = "unit_source_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "unit_source_value", insertable = false, updatable = false,
            nullable = true)
    private String unitSourceValue;
    
    public Optional<String> getUnitSourceValue() {
        if (this.unitSourceValue != null) {
            return Optional.of(this.unitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "value_as_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "value_as_number", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal valueAsNumber;
    
    public Optional<BigDecimal> getValueAsNumber() {
        if (this.valueAsNumber != null) {
            return Optional.of(this.valueAsNumber);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "value_source_value", insertable = false, updatable = false,
            nullable = true)
    private String valueSourceValue;
    
    public Optional<String> getValueSourceValue() {
        if (this.valueSourceValue != null) {
            return Optional.of(this.valueSourceValue);
        } else {
            return Optional.empty();
        }
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
