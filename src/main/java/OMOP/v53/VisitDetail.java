// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
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
@Table(name = "visit_detail")
public class VisitDetail {

    @Column(name = "admitting_source_concept_id", updatable = false,
            nullable = true)
    private Integer admittingSourceConceptId;
    
    public Optional<Integer> getAdmittingSourceConceptId() {
        if (this.admittingSourceConceptId != null) {
            return Optional.of(this.admittingSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "admitting_source_concept_id", insertable = false,
                updatable = false)
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

    @Column(name = "admitting_source_value", updatable = false, nullable = true)
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

    @Column(name = "care_site_id", updatable = false, nullable = true)
    private Integer careSiteId;
    
    public Optional<Integer> getCareSiteId() {
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

    @Column(name = "discharge_to_concept_id", updatable = false,
            nullable = true)
    private Integer dischargeToConceptId;
    
    public Optional<Integer> getDischargeToConceptId() {
        if (this.dischargeToConceptId != null) {
            return Optional.of(this.dischargeToConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "discharge_to_concept_id", insertable = false,
                updatable = false)
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

    @Column(name = "discharge_to_source_value", updatable = false,
            nullable = true)
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
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.personId = (newValue != null) ? newValue.getPersonId() : 0;
    }

    @Column(name = "preceding_visit_detail_id", updatable = false,
            nullable = true)
    private Integer precedingVisitDetailId;
    
    public Optional<Integer> getPrecedingVisitDetailId() {
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
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.visitDetailConceptId = (newValue != null) ? newValue.getConceptId() : 0;
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
    private Integer visitDetailId;
    
    public Integer getVisitDetailId() {
        return this.visitDetailId;
    }

    @Column(name = "visit_detail_parent_id", updatable = false, nullable = true)
    private Integer visitDetailParentId;
    
    public Optional<Integer> getVisitDetailParentId() {
        if (this.visitDetailParentId != null) {
            return Optional.of(this.visitDetailParentId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_parent_id", insertable = false,
                updatable = false)
    private VisitDetail visitDetailParent;
    
    public Optional<VisitDetail> getVisitDetailParent() {
        return Optional.ofNullable(this.visitDetailParent);
    }

    public void setVisitDetailParent(final VisitDetail newValue) {
        if (newValue == null) {
            this.visitDetailParent = null;
            this.visitDetailParentId = null;
        } else {
            this.visitDetailParent = newValue;
            this.visitDetailParentId = newValue.getVisitDetailId();
        }
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
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.visitDetailTypeConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "visit_occurrence_id", updatable = false, nullable = false)
    private Integer visitOccurrenceId;
    
    public Integer getVisitOccurrenceId() {
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
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.visitOccurrenceId = (newValue != null) ? newValue.getVisitOccurrenceId() : 0;
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

        }
        result.append("}");
        return result.toString();
    }


}
