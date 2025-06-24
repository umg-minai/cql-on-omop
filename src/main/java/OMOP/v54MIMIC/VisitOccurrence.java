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
@Table(name = "visit_occurrence", schema = "cds_cdm")
public class VisitOccurrence {

    @Column(name = "admitted_from_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long admittedFromConceptId;
    
    public Optional<Long> getAdmittedFromConceptId() {
        if (this.admittedFromConceptId != null) {
            return Optional.of(this.admittedFromConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admitted_from_concept_id")
    private Concept admittedFromConcept;
    
    public Optional<Concept> getAdmittedFromConcept() {
        return Optional.ofNullable(this.admittedFromConcept);
    }

    @Column(name = "admitted_from_source_value", insertable = false,
            updatable = false, nullable = true)
    private String admittedFromSourceValue;
    
    public Optional<String> getAdmittedFromSourceValue() {
        if (this.admittedFromSourceValue != null) {
            return Optional.of(this.admittedFromSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "care_site_id", insertable = false, updatable = false,
            nullable = true)
    private Long careSiteId;
    
    public Optional<Long> getCareSiteId() {
        if (this.careSiteId != null) {
            return Optional.of(this.careSiteId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_site_id")
    private CareSite careSite;
    
    public Optional<CareSite> getCareSite() {
        return Optional.ofNullable(this.careSite);
    }

    @Column(name = "discharged_to_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long dischargedToConceptId;
    
    public Optional<Long> getDischargedToConceptId() {
        if (this.dischargedToConceptId != null) {
            return Optional.of(this.dischargedToConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "discharged_to_concept_id")
    private Concept dischargedToConcept;
    
    public Optional<Concept> getDischargedToConcept() {
        return Optional.ofNullable(this.dischargedToConcept);
    }

    @Column(name = "discharged_to_source_value", insertable = false,
            updatable = false, nullable = true)
    private String dischargedToSourceValue;
    
    public Optional<String> getDischargedToSourceValue() {
        if (this.dischargedToSourceValue != null) {
            return Optional.of(this.dischargedToSourceValue);
        } else {
            return Optional.empty();
        }
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

    @Column(name = "preceding_visit_occurrence_id", insertable = false,
            updatable = false, nullable = true)
    private Long precedingVisitOccurrenceId;
    
    public Optional<Long> getPrecedingVisitOccurrenceId() {
        if (this.precedingVisitOccurrenceId != null) {
            return Optional.of(this.precedingVisitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "preceding_visit_occurrence_id")
    private VisitOccurrence precedingVisitOccurrence;
    
    public Optional<VisitOccurrence> getPrecedingVisitOccurrence() {
        return Optional.ofNullable(this.precedingVisitOccurrence);
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

    @Column(name = "visit_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Long visitConceptId;
    
    public Long getVisitConceptId() {
        return this.visitConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_concept_id")
    private Concept visitConcept;
    
    public Concept getVisitConcept() {
        return this.visitConcept;
    }

    @Column(name = "visit_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime visitEndDate;
    
    public Date getVisitEndDate() {
        return new Date(this.visitEndDate.toLocalDate());
    }

    @Column(name = "visit_end_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime visitEndDatetime;
    
    public Optional<DateTime> getVisitEndDatetime() {
        if (this.visitEndDatetime != null) {
            return Optional.of(new DateTime(this.visitEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = false)
    private Long visitOccurrenceId;
    
    public Long getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }

    @Column(name = "visit_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long visitSourceConceptId;
    
    public Optional<Long> getVisitSourceConceptId() {
        if (this.visitSourceConceptId != null) {
            return Optional.of(this.visitSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_source_concept_id")
    private Concept visitSourceConcept;
    
    public Optional<Concept> getVisitSourceConcept() {
        return Optional.ofNullable(this.visitSourceConcept);
    }

    @Column(name = "visit_source_value", insertable = false, updatable = false,
            nullable = true)
    private String visitSourceValue;
    
    public Optional<String> getVisitSourceValue() {
        if (this.visitSourceValue != null) {
            return Optional.of(this.visitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "visit_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime visitStartDate;
    
    public Date getVisitStartDate() {
        return new Date(this.visitStartDate.toLocalDate());
    }

    @Column(name = "visit_start_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime visitStartDatetime;
    
    public Optional<DateTime> getVisitStartDatetime() {
        if (this.visitStartDatetime != null) {
            return Optional.of(new DateTime(this.visitStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "visit_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long visitTypeConceptId;
    
    public Long getVisitTypeConceptId() {
        return this.visitTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_concept_id")
    private Concept visitTypeConcept;
    
    public Concept getVisitTypeConcept() {
        return this.visitTypeConcept;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("VisitOccurrence{");
        result.append("id=");
        result.append(this.visitOccurrenceId);
        {
            result.append(", concept='");
            result.append(this.getVisitConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
