package OMOP.v54MIMIC;

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
@Table(name = "observation", schema = "cds_cdm")
public class Observation {

    @Column(name = "obs_event_field_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long obsEventFieldConceptId;
    
    public Optional<Long> getObsEventFieldConceptId() {
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

    @Column(name = "observation_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long observationConceptId;
    
    public Long getObservationConceptId() {
        return this.observationConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_concept_id")
    private Concept observationConcept;
    
    public Concept getObservationConcept() {
        return this.observationConcept;
    }

    @Column(name = "observation_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime observationDate;
    
    public Date getObservationDate() {
        return new Date(this.observationDate.toLocalDate());
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

    @Column(name = "observation_event_id", insertable = false,
            updatable = false, nullable = true)
    private Long observationEventId;
    
    public Optional<Long> getObservationEventId() {
        if (this.observationEventId != null) {
            return Optional.of(this.observationEventId);
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "observation_id", insertable = false, updatable = false,
            nullable = false)
    private Long observationId;
    
    public Long getObservationId() {
        return this.observationId;
    }

    @Column(name = "observation_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long observationSourceConceptId;
    
    public Optional<Long> getObservationSourceConceptId() {
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

    @Column(name = "observation_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long observationTypeConceptId;
    
    public Long getObservationTypeConceptId() {
        return this.observationTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "observation_type_concept_id")
    private Concept observationTypeConcept;
    
    public Concept getObservationTypeConcept() {
        return this.observationTypeConcept;
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Long personId;
    
    public Long getPersonId() {
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
    private Long providerId;
    
    public Optional<Long> getProviderId() {
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

    @Column(name = "qualifier_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long qualifierConceptId;
    
    public Optional<Long> getQualifierConceptId() {
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

    @Column(name = "unit_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Long unitConceptId;
    
    public Optional<Long> getUnitConceptId() {
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
    private Long valueAsConceptId;
    
    public Optional<Long> getValueAsConceptId() {
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
    private Long visitDetailId;
    
    public Optional<Long> getVisitDetailId() {
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
    private Long visitOccurrenceId;
    
    public Optional<Long> getVisitOccurrenceId() {
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
