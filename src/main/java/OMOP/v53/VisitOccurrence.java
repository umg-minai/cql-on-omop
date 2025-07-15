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
@Table(name = "visit_occurrence", schema = "cds_cdm")
public class VisitOccurrence {

    @Column(name = "admitting_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer admittingSourceConceptId;
    
    public Optional<Integer> getAdmittingSourceConceptId() {
        if (this.admittingSourceConceptId != null) {
            return Optional.of(this.admittingSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admitting_source_concept_id")
    private Concept admittingSourceConcept;
    
    public Optional<Concept> getAdmittingSourceConcept() {
        return Optional.ofNullable(this.admittingSourceConcept);
    }

    public void setAdmittingSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.admittingSourceConcept = null;
            this.admittingSourceConceptId = null;
        } else {
            this.admittingSourceConcept = newValue;
            this.admittingSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "admitting_source_value", insertable = false,
            updatable = false, nullable = true)
    private String admittingSourceValue;
    
    public Optional<String> getAdmittingSourceValue() {
        if (this.admittingSourceValue != null) {
            return Optional.of(this.admittingSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setAdmittingSourceValue(final String newValue) {
        this.admittingSourceValue = newValue;
    }

    @Column(name = "care_site_id", insertable = false, updatable = false,
            nullable = true)
    private Integer careSiteId;
    
    public Optional<Integer> getCareSiteId() {
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

    public void setCareSite(final CareSite newValue) {
        if (newValue == null) {
            this.careSite = null;
            this.careSiteId = null;
        } else {
            this.careSite = newValue;
            this.careSiteId = newValue.getCareSiteId();
        }
    }

    @Column(name = "discharge_to_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer dischargeToConceptId;
    
    public Optional<Integer> getDischargeToConceptId() {
        if (this.dischargeToConceptId != null) {
            return Optional.of(this.dischargeToConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "discharge_to_concept_id")
    private Concept dischargeToConcept;
    
    public Optional<Concept> getDischargeToConcept() {
        return Optional.ofNullable(this.dischargeToConcept);
    }

    public void setDischargeToConcept(final Concept newValue) {
        if (newValue == null) {
            this.dischargeToConcept = null;
            this.dischargeToConceptId = null;
        } else {
            this.dischargeToConcept = newValue;
            this.dischargeToConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "discharge_to_source_value", insertable = false,
            updatable = false, nullable = true)
    private String dischargeToSourceValue;
    
    public Optional<String> getDischargeToSourceValue() {
        if (this.dischargeToSourceValue != null) {
            return Optional.of(this.dischargeToSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDischargeToSourceValue(final String newValue) {
        this.dischargeToSourceValue = newValue;
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

    @Column(name = "preceding_visit_occurrence_id", insertable = false,
            updatable = false, nullable = true)
    private Integer precedingVisitOccurrenceId;
    
    public Optional<Integer> getPrecedingVisitOccurrenceId() {
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

    public void setPrecedingVisitOccurrence(final VisitOccurrence newValue) {
        if (newValue == null) {
            this.precedingVisitOccurrence = null;
            this.precedingVisitOccurrenceId = null;
        } else {
            this.precedingVisitOccurrence = newValue;
            this.precedingVisitOccurrenceId = newValue.getVisitOccurrenceId();
        }
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

    @Column(name = "visit_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer visitConceptId;
    
    public Integer getVisitConceptId() {
        return this.visitConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_concept_id")
    private Concept visitConcept;
    
    public Concept getVisitConcept() {
        return this.visitConcept;
    }

    public void setVisitConcept(final Concept newValue) {
        this.visitConcept = newValue;
        this.visitConceptId = newValue.getConceptId();
    }

    @Column(name = "visit_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime visitEndDate;
    
    public Date getVisitEndDate() {
        return new Date(this.visitEndDate.toLocalDate());
    }

    public void setVisitEndDate(final Date newValue) {
        this.visitEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
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

    public void setVisitEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitEndDatetime = null;
        } else {
            this.visitEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = false)
    private Integer visitOccurrenceId;
    
    public Integer getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }

    @Column(name = "visit_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer visitSourceConceptId;
    
    public Optional<Integer> getVisitSourceConceptId() {
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

    public void setVisitSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.visitSourceConcept = null;
            this.visitSourceConceptId = null;
        } else {
            this.visitSourceConcept = newValue;
            this.visitSourceConceptId = newValue.getConceptId();
        }
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

    public void setVisitSourceValue(final String newValue) {
        this.visitSourceValue = newValue;
    }

    @Column(name = "visit_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime visitStartDate;
    
    public Date getVisitStartDate() {
        return new Date(this.visitStartDate.toLocalDate());
    }

    public void setVisitStartDate(final Date newValue) {
        this.visitStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
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

    public void setVisitStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitStartDatetime = null;
        } else {
            this.visitStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "visit_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer visitTypeConceptId;
    
    public Integer getVisitTypeConceptId() {
        return this.visitTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_concept_id")
    private Concept visitTypeConcept;
    
    public Concept getVisitTypeConcept() {
        return this.visitTypeConcept;
    }

    public void setVisitTypeConcept(final Concept newValue) {
        this.visitTypeConcept = newValue;
        this.visitTypeConceptId = newValue.getConceptId();
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
