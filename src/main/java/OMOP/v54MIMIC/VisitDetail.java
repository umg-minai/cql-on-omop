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
@Table(name = "visit_detail")
public class VisitDetail {

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

    @Column(name = "parent_visit_detail_id", updatable = false, nullable = true)
    private Long parentVisitDetailId;
    
    public Optional<Long> getParentVisitDetailId() {
        if (this.parentVisitDetailId != null) {
            return Optional.of(this.parentVisitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_visit_detail_id", insertable = false,
                updatable = false)
    private VisitDetail parentVisitDetail;
    
    public Optional<VisitDetail> getParentVisitDetail() {
        return Optional.ofNullable(this.parentVisitDetail);
    }

    public void setParentVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.parentVisitDetail = null;
            this.parentVisitDetailId = null;
        } else {
            this.parentVisitDetail = newValue;
            this.parentVisitDetailId = newValue.getVisitDetailId();
        }
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
        this.personId = newValue.getPersonId();
    }

    @Column(name = "preceding_visit_detail_id", updatable = false,
            nullable = true)
    private Long precedingVisitDetailId;
    
    public Optional<Long> getPrecedingVisitDetailId() {
        if (this.precedingVisitDetailId != null) {
            return Optional.of(this.precedingVisitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "preceding_visit_detail_id", insertable = false,
                updatable = false)
    private VisitDetail precedingVisitDetail;
    
    public Optional<VisitDetail> getPrecedingVisitDetail() {
        return Optional.ofNullable(this.precedingVisitDetail);
    }

    public void setPrecedingVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.precedingVisitDetail = null;
            this.precedingVisitDetailId = null;
        } else {
            this.precedingVisitDetail = newValue;
            this.precedingVisitDetailId = newValue.getVisitDetailId();
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

    @Column(name = "visit_detail_concept_id", updatable = false,
            nullable = false)
    private Integer visitDetailConceptId;
    
    public Integer getVisitDetailConceptId() {
        return this.visitDetailConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_concept_id", insertable = false,
                updatable = false)
    private Concept visitDetailConcept;
    
    public Concept getVisitDetailConcept() {
        return this.visitDetailConcept;
    }

    public void setVisitDetailConcept(final Concept newValue) {
        this.visitDetailConcept = newValue;
        this.visitDetailConceptId = newValue.getConceptId();
    }

    @Column(name = "visit_detail_end_date", updatable = false, nullable = false)
    private ZonedDateTime visitDetailEndDate;
    
    public Date getVisitDetailEndDate() {
        return new Date(this.visitDetailEndDate.toLocalDate());
    }

    public void setVisitDetailEndDate(final Date newValue) {
        this.visitDetailEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "visit_detail_end_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime visitDetailEndDatetime;
    
    public Optional<DateTime> getVisitDetailEndDatetime() {
        if (this.visitDetailEndDatetime != null) {
            return Optional.of(new DateTime(this.visitDetailEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setVisitDetailEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitDetailEndDatetime = null;
        } else {
            this.visitDetailEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_detail_id", updatable = false, nullable = false)
    private Long visitDetailId;
    
    public Long getVisitDetailId() {
        return this.visitDetailId;
    }

    @Column(name = "visit_detail_source_concept_id", updatable = false,
            nullable = true)
    private Integer visitDetailSourceConceptId;
    
    public Optional<Integer> getVisitDetailSourceConceptId() {
        if (this.visitDetailSourceConceptId != null) {
            return Optional.of(this.visitDetailSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_source_concept_id", insertable = false,
                updatable = false)
    private Concept visitDetailSourceConcept;
    
    public Optional<Concept> getVisitDetailSourceConcept() {
        return Optional.ofNullable(this.visitDetailSourceConcept);
    }

    public void setVisitDetailSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.visitDetailSourceConcept = null;
            this.visitDetailSourceConceptId = null;
        } else {
            this.visitDetailSourceConcept = newValue;
            this.visitDetailSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "visit_detail_source_value", updatable = false,
            nullable = true)
    private String visitDetailSourceValue;
    
    public Optional<String> getVisitDetailSourceValue() {
        if (this.visitDetailSourceValue != null) {
            return Optional.of(this.visitDetailSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setVisitDetailSourceValue(final String newValue) {
        this.visitDetailSourceValue = newValue;
    }

    @Column(name = "visit_detail_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime visitDetailStartDate;
    
    public Date getVisitDetailStartDate() {
        return new Date(this.visitDetailStartDate.toLocalDate());
    }

    public void setVisitDetailStartDate(final Date newValue) {
        this.visitDetailStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "visit_detail_start_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime visitDetailStartDatetime;
    
    public Optional<DateTime> getVisitDetailStartDatetime() {
        if (this.visitDetailStartDatetime != null) {
            return Optional.of(new DateTime(this.visitDetailStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setVisitDetailStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.visitDetailStartDatetime = null;
        } else {
            this.visitDetailStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "visit_detail_type_concept_id", updatable = false,
            nullable = false)
    private Integer visitDetailTypeConceptId;
    
    public Integer getVisitDetailTypeConceptId() {
        return this.visitDetailTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_type_concept_id", insertable = false,
                updatable = false)
    private Concept visitDetailTypeConcept;
    
    public Concept getVisitDetailTypeConcept() {
        return this.visitDetailTypeConcept;
    }

    public void setVisitDetailTypeConcept(final Concept newValue) {
        this.visitDetailTypeConcept = newValue;
        this.visitDetailTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "visit_occurrence_id", updatable = false, nullable = false)
    private Long visitOccurrenceId;
    
    public Long getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id", insertable = false,
                updatable = false)
    private VisitOccurrence visitOccurrence;
    
    public VisitOccurrence getVisitOccurrence() {
        return this.visitOccurrence;
    }

    public void setVisitOccurrence(final VisitOccurrence newValue) {
        this.visitOccurrence = newValue;
        this.visitOccurrenceId = newValue.getVisitOccurrenceId();
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("VisitDetail{");
        result.append("id=");
        result.append(this.visitDetailId);
        {
            result.append(", concept='");
            result.append(this.getVisitDetailConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
