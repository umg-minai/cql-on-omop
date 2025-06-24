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
@Table(name = "visit_detail", schema = "cds_cdm")
public class VisitDetail {

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

    @Column(name = "parent_visit_detail_id", insertable = false,
            updatable = false, nullable = true)
    private Long parentVisitDetailId;
    
    public Optional<Long> getParentVisitDetailId() {
        if (this.parentVisitDetailId != null) {
            return Optional.of(this.parentVisitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_visit_detail_id")
    private VisitDetail parentVisitDetail;
    
    public Optional<VisitDetail> getParentVisitDetail() {
        return Optional.ofNullable(this.parentVisitDetail);
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

    @Column(name = "preceding_visit_detail_id", insertable = false,
            updatable = false, nullable = true)
    private Long precedingVisitDetailId;
    
    public Optional<Long> getPrecedingVisitDetailId() {
        if (this.precedingVisitDetailId != null) {
            return Optional.of(this.precedingVisitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "preceding_visit_detail_id")
    private VisitDetail precedingVisitDetail;
    
    public Optional<VisitDetail> getPrecedingVisitDetail() {
        return Optional.ofNullable(this.precedingVisitDetail);
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

    @Column(name = "visit_detail_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long visitDetailConceptId;
    
    public Long getVisitDetailConceptId() {
        return this.visitDetailConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_concept_id")
    private Concept visitDetailConcept;
    
    public Concept getVisitDetailConcept() {
        return this.visitDetailConcept;
    }

    @Column(name = "visit_detail_end_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime visitDetailEndDate;
    
    public Date getVisitDetailEndDate() {
        return new Date(this.visitDetailEndDate.toLocalDate());
    }

    @Column(name = "visit_detail_end_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime visitDetailEndDatetime;
    
    public Optional<DateTime> getVisitDetailEndDatetime() {
        if (this.visitDetailEndDatetime != null) {
            return Optional.of(new DateTime(this.visitDetailEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "visit_detail_id", insertable = false, updatable = false,
            nullable = false)
    private Long visitDetailId;
    
    public Long getVisitDetailId() {
        return this.visitDetailId;
    }

    @Column(name = "visit_detail_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long visitDetailSourceConceptId;
    
    public Optional<Long> getVisitDetailSourceConceptId() {
        if (this.visitDetailSourceConceptId != null) {
            return Optional.of(this.visitDetailSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_source_concept_id")
    private Concept visitDetailSourceConcept;
    
    public Optional<Concept> getVisitDetailSourceConcept() {
        return Optional.ofNullable(this.visitDetailSourceConcept);
    }

    @Column(name = "visit_detail_source_value", insertable = false,
            updatable = false, nullable = true)
    private String visitDetailSourceValue;
    
    public Optional<String> getVisitDetailSourceValue() {
        if (this.visitDetailSourceValue != null) {
            return Optional.of(this.visitDetailSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "visit_detail_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime visitDetailStartDate;
    
    public Date getVisitDetailStartDate() {
        return new Date(this.visitDetailStartDate.toLocalDate());
    }

    @Column(name = "visit_detail_start_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime visitDetailStartDatetime;
    
    public Optional<DateTime> getVisitDetailStartDatetime() {
        if (this.visitDetailStartDatetime != null) {
            return Optional.of(new DateTime(this.visitDetailStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "visit_detail_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long visitDetailTypeConceptId;
    
    public Long getVisitDetailTypeConceptId() {
        return this.visitDetailTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_type_concept_id")
    private Concept visitDetailTypeConcept;
    
    public Concept getVisitDetailTypeConcept() {
        return this.visitDetailTypeConcept;
    }

    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = false)
    private Long visitOccurrenceId;
    
    public Long getVisitOccurrenceId() {
        return this.visitOccurrenceId;
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id")
    private VisitOccurrence visitOccurrence;
    
    public VisitOccurrence getVisitOccurrence() {
        return this.visitOccurrence;
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
