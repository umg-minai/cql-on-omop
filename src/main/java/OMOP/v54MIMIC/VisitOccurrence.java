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
@Table(name = "visit_occurrence")
public class VisitOccurrence {

    @Column(name = "admitted_from_concept_id", updatable = false,
            nullable = true)
    private Integer admittedFromConceptId;
    
    public Optional<Integer> getAdmittedFromConceptId() {
        if (this.admittedFromConceptId != null) {
            return Optional.of(this.admittedFromConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setAdmittedFromConceptId(final Integer newValue) {
        this.admittedFromConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admitted_from_concept_id", insertable = false,
                updatable = false)
    private Concept admittedFromConcept;
    
    public Optional<Concept> getAdmittedFromConcept() {
        return Optional.ofNullable(this.admittedFromConcept);
    }

    public void setAdmittedFromConcept(final Concept newValue) {
        if (newValue == null) {
            this.admittedFromConcept = null;
            this.admittedFromConceptId = null;
        } else {
            this.admittedFromConcept = newValue;
            this.admittedFromConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "admitted_from_source_value", updatable = false,
            nullable = true)
    private String admittedFromSourceValue;
    
    public Optional<String> getAdmittedFromSourceValue() {
        if (this.admittedFromSourceValue != null) {
            return Optional.of(this.admittedFromSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setAdmittedFromSourceValue(final String newValue) {
        this.admittedFromSourceValue = newValue;
    }

    @Column(name = "care_site_id", updatable = false, nullable = true)
    private Long careSiteId;
    
    public Optional<Long> getCareSiteId() {
        if (this.careSiteId != null) {
            return Optional.of(this.careSiteId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_site_id", insertable = false, updatable = false)
    private CareSite careSite;
    
    public Optional<CareSite> getCareSite() {
        return Optional.ofNullable(this.careSite);
    }

    public void setCareSite(final CareSite newValue) {
        if (newValue == null) {
            this.careSite = null;
            this.careSiteId = null;
        } else {
            this.careSite = newValue;
            this.careSiteId = newValue.getCareSiteId();
        }
    }

    @Column(name = "discharged_to_concept_id", updatable = false,
            nullable = true)
    private Integer dischargedToConceptId;
    
    public Optional<Integer> getDischargedToConceptId() {
        if (this.dischargedToConceptId != null) {
            return Optional.of(this.dischargedToConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDischargedToConceptId(final Integer newValue) {
        this.dischargedToConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "discharged_to_concept_id", insertable = false,
                updatable = false)
    private Concept dischargedToConcept;
    
    public Optional<Concept> getDischargedToConcept() {
        return Optional.ofNullable(this.dischargedToConcept);
    }

    public void setDischargedToConcept(final Concept newValue) {
        if (newValue == null) {
            this.dischargedToConcept = null;
            this.dischargedToConceptId = null;
        } else {
            this.dischargedToConcept = newValue;
            this.dischargedToConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "discharged_to_source_value", updatable = false,
            nullable = true)
    private String dischargedToSourceValue;
    
    public Optional<String> getDischargedToSourceValue() {
        if (this.dischargedToSourceValue != null) {
            return Optional.of(this.dischargedToSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDischargedToSourceValue(final String newValue) {
        this.dischargedToSourceValue = newValue;
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

    @Column(name = "preceding_visit_occurrence_id", updatable = false,
            nullable = true)
    private Long precedingVisitOccurrenceId;
    
    public Optional<Long> getPrecedingVisitOccurrenceId() {
        if (this.precedingVisitOccurrenceId != null) {
            return Optional.of(this.precedingVisitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "preceding_visit_occurrence_id", insertable = false,
                updatable = false)
    private VisitOccurrence precedingVisitOccurrence;
    
    public Optional<VisitOccurrence> getPrecedingVisitOccurrence() {
        return Optional.ofNullable(this.precedingVisitOccurrence);
    }

    public void setPrecedingVisitOccurrence(final VisitOccurrence newValue) {
        if (newValue == null) {
            this.precedingVisitOccurrence = null;
            this.precedingVisitOccurrenceId = null;
        } else {
            this.precedingVisitOccurrence = newValue;
            this.precedingVisitOccurrenceId = newValue.getVisitOccurrenceId();
        }
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

    @Column(name = "visit_concept_id", updatable = false, nullable = false)
    private Integer visitConceptId;
    
    public Integer getVisitConceptId() {
        return this.visitConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setVisitConceptId(final Integer newValue) {
        this.visitConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_concept_id", insertable = false,
                updatable = false)
    private Concept visitConcept;
    
    public Concept getVisitConcept() {
        return this.visitConcept;
    }

    public void setVisitConcept(final Concept newValue) {
        this.visitConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.visitConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "visit_end_date", updatable = false, nullable = false)
    private ZonedDateTime visitEndDate;
    
    public Date getVisitEndDate() {
        return new Date(this.visitEndDate.toLocalDate());
    }

    public void setVisitEndDate(final Date newValue) {
        this.visitEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "visit_end_datetime", updatable = false, nullable = true)
    private ZonedDateTime visitEndDatetime;
    
    public Optional<DateTime> getVisitEndDatetime() {
        if (this.visitEndDatetime != null) {
            return Optional.of(new DateTime(this.visitEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setVisitEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitEndDatetime = null;
        } else {
            this.visitEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_occurrence_id", updatable = false, nullable = false)
    private Long visitOccurrenceId;
    
    public Long getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }

    @Column(name = "visit_source_concept_id", updatable = false,
            nullable = true)
    private Integer visitSourceConceptId;
    
    public Optional<Integer> getVisitSourceConceptId() {
        if (this.visitSourceConceptId != null) {
            return Optional.of(this.visitSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setVisitSourceConceptId(final Integer newValue) {
        this.visitSourceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_source_concept_id", insertable = false,
                updatable = false)
    private Concept visitSourceConcept;
    
    public Optional<Concept> getVisitSourceConcept() {
        return Optional.ofNullable(this.visitSourceConcept);
    }

    public void setVisitSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.visitSourceConcept = null;
            this.visitSourceConceptId = null;
        } else {
            this.visitSourceConcept = newValue;
            this.visitSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "visit_source_value", updatable = false, nullable = true)
    private String visitSourceValue;
    
    public Optional<String> getVisitSourceValue() {
        if (this.visitSourceValue != null) {
            return Optional.of(this.visitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setVisitSourceValue(final String newValue) {
        this.visitSourceValue = newValue;
    }

    @Column(name = "visit_start_date", updatable = false, nullable = false)
    private ZonedDateTime visitStartDate;
    
    public Date getVisitStartDate() {
        return new Date(this.visitStartDate.toLocalDate());
    }

    public void setVisitStartDate(final Date newValue) {
        this.visitStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "visit_start_datetime", updatable = false, nullable = true)
    private ZonedDateTime visitStartDatetime;
    
    public Optional<DateTime> getVisitStartDatetime() {
        if (this.visitStartDatetime != null) {
            return Optional.of(new DateTime(this.visitStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setVisitStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitStartDatetime = null;
        } else {
            this.visitStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "visit_type_concept_id", updatable = false, nullable = false)
    private Integer visitTypeConceptId;
    
    public Integer getVisitTypeConceptId() {
        return this.visitTypeConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setVisitTypeConceptId(final Integer newValue) {
        this.visitTypeConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_concept_id", insertable = false,
                updatable = false)
    private Concept visitTypeConcept;
    
    public Concept getVisitTypeConcept() {
        return this.visitTypeConcept;
    }

    public void setVisitTypeConcept(final Concept newValue) {
        this.visitTypeConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.visitTypeConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("VisitOccurrence{");
        result.append("id=");
        result.append(this.visitOccurrenceId);
        {
            result.append(", concept='");
            if (this.getVisitConcept() != null) {
                result.append(this.getVisitConcept().getConceptName());

            } else {
                result.append("«broken relation»");

            }result.append("'");

        }
        result.append("}");
        return result.toString();
    }


}
