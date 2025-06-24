package OMOP.v53;

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

    @Column(name = "preceding_visit_detail_id", insertable = false,
            updatable = false, nullable = true)
    private Integer precedingVisitDetailId;
    
    public Optional<Integer> getPrecedingVisitDetailId() {
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

    @Column(name = "visit_detail_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer visitDetailConceptId;
    
    public Integer getVisitDetailConceptId() {
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
    private Integer visitDetailId;
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }

    @Column(name = "visit_detail_parent_id", insertable = false,
            updatable = false, nullable = true)
    private Integer visitDetailParentId;
    
    public Optional<Integer> getVisitDetailParentId() {
        if (this.visitDetailParentId != null) {
            return Optional.of(this.visitDetailParentId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_parent_id")
    private VisitDetail visitDetailParent;
    
    public Optional<VisitDetail> getVisitDetailParent() {
        return Optional.ofNullable(this.visitDetailParent);
    }

    @Column(name = "visit_detail_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer visitDetailSourceConceptId;
    
    public Optional<Integer> getVisitDetailSourceConceptId() {
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
    private Integer visitDetailTypeConceptId;
    
    public Integer getVisitDetailTypeConceptId() {
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
    private Integer visitOccurrenceId;
    
    public Integer getVisitOccurrenceId() {
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
