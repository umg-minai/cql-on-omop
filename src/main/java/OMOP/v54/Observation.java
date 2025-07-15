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
@Table(name = "observation", schema = "cds_cdm")
public class Observation {

    @Column(name = "obs_event_field_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    public void setObsEventFieldConcept(final Concept newValue) {
        if (newValue == null) {
            this.obsEventFieldConcept = null;
            this.obsEventFieldConceptId = null;
        } else {
            this.obsEventFieldConcept = newValue;
            this.obsEventFieldConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "observation_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer observationConceptId;
    
    public Integer getObservationConceptId() {
        return this.observationConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_concept_id")
    private Concept observationConcept;
    
    public Concept getObservationConcept() {
        return this.observationConcept;
    }

    public void setObservationConcept(final Concept newValue) {
        this.observationConcept = newValue;
        this.observationConceptId = newValue.getConceptId();
    }

    @Column(name = "observation_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime observationDate;
    
    public Date getObservationDate() {
        return new Date(this.observationDate.toLocalDate());
    }

    public void setObservationDate(final Date newValue) {
        this.observationDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "observation_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime observationDatetime;
    
    public Optional<DateTime> getObservationDatetime() {
        if (this.observationDatetime != null) {
            return Optional.of(new DateTime(this.observationDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setObservationDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.observationDatetime = null;
        } else {
            this.observationDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "observation_event_id", insertable = false,
            updatable = false, nullable = true)
    private Integer observationEventId;
    
    public Optional<Integer> getObservationEventId() {
        if (this.observationEventId != null) {
            return Optional.of(this.observationEventId);
        } else {
            return Optional.empty();
        }
    }

    public void setObservationEventId(final Integer newValue) {
        this.observationEventId = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observation_id", insertable = false, updatable = false,
            nullable = false)
    private Integer observationId;
    
    public Integer getObservationId() {
        return this.observationId;
    }

    @Column(name = "observation_source_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    public void setObservationSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.observationSourceConcept = null;
            this.observationSourceConceptId = null;
        } else {
            this.observationSourceConcept = newValue;
            this.observationSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "observation_source_value", insertable = false,
            updatable = false, nullable = true)
    private String observationSourceValue;
    
    public Optional<String> getObservationSourceValue() {
        if (this.observationSourceValue != null) {
            return Optional.of(this.observationSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setObservationSourceValue(final String newValue) {
        this.observationSourceValue = newValue;
    }

    @Column(name = "observation_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer observationTypeConceptId;
    
    public Integer getObservationTypeConceptId() {
        return this.observationTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_type_concept_id")
    private Concept observationTypeConcept;
    
    public Concept getObservationTypeConcept() {
        return this.observationTypeConcept;
    }

    public void setObservationTypeConcept(final Concept newValue) {
        this.observationTypeConcept = newValue;
        this.observationTypeConceptId = newValue.getConceptId();
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

    @Column(name = "qualifier_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    public void setQualifierConcept(final Concept newValue) {
        if (newValue == null) {
            this.qualifierConcept = null;
            this.qualifierConceptId = null;
        } else {
            this.qualifierConcept = newValue;
            this.qualifierConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "qualifier_source_value", insertable = false,
            updatable = false, nullable = true)
    private String qualifierSourceValue;
    
    public Optional<String> getQualifierSourceValue() {
        if (this.qualifierSourceValue != null) {
            return Optional.of(this.qualifierSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setQualifierSourceValue(final String newValue) {
        this.qualifierSourceValue = newValue;
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

    public void setUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.unitConcept = null;
            this.unitConceptId = null;
        } else {
            this.unitConcept = newValue;
            this.unitConceptId = newValue.getConceptId();
        }
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

    public void setUnitSourceValue(final String newValue) {
        this.unitSourceValue = newValue;
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

    public void setValueAsConcept(final Concept newValue) {
        if (newValue == null) {
            this.valueAsConcept = null;
            this.valueAsConceptId = null;
        } else {
            this.valueAsConcept = newValue;
            this.valueAsConceptId = newValue.getConceptId();
        }
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

    public void setValueAsNumber(final BigDecimal newValue) {
        this.valueAsNumber = newValue;
    }

    @Column(name = "value_as_string", insertable = false, updatable = false,
            nullable = true)
    private String valueAsString;
    
    public Optional<String> getValueAsString() {
        if (this.valueAsString != null) {
            return Optional.of(this.valueAsString);
        } else {
            return Optional.empty();
        }
    }

    public void setValueAsString(final String newValue) {
        this.valueAsString = newValue;
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

    public void setValueSourceValue(final String newValue) {
        this.valueSourceValue = newValue;
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
        result.append("Observation{");
        result.append("id=");
        result.append(this.observationId);
        {
            result.append(", concept='");
            result.append(this.getObservationConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
