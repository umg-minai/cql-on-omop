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
@Table(name = "condition_occurrence")
public class ConditionOccurrence {

    @Column(name = "condition_concept_id", updatable = false, nullable = false)
    private Integer conditionConceptId;
    
    public Integer getConditionConceptId() {
        return this.conditionConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_concept_id", insertable = false,
                updatable = false)
    private Concept conditionConcept;
    
    public Concept getConditionConcept() {
        return this.conditionConcept;
    }

    public void setConditionConcept(final Concept newValue) {
        this.conditionConcept = newValue;
        this.conditionConceptId = newValue.getConceptId();
    }

    @Column(name = "condition_end_date", updatable = false, nullable = true)
    private ZonedDateTime conditionEndDate;
    
    public Optional<Date> getConditionEndDate() {
        if (this.conditionEndDate != null) {
            return Optional.of(new Date(this.conditionEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setConditionEndDate(final Date newValue) {
        if (newValue == null) {
            this.conditionEndDate = null;
        } else {
            this.conditionEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    @Column(name = "condition_end_datetime", updatable = false, nullable = true)
    private ZonedDateTime conditionEndDatetime;
    
    public Optional<DateTime> getConditionEndDatetime() {
        if (this.conditionEndDatetime != null) {
            return Optional.of(new DateTime(this.conditionEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setConditionEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.conditionEndDatetime = null;
        } else {
            this.conditionEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_occurrence_id", updatable = false,
            nullable = false)
    private Long conditionOccurrenceId;
    
    public Long getConditionOccurrenceId() {
        return this.conditionOccurrenceId;
    }

    @Column(name = "condition_source_concept_id", updatable = false,
            nullable = true)
    private Integer conditionSourceConceptId;
    
    public Optional<Integer> getConditionSourceConceptId() {
        if (this.conditionSourceConceptId != null) {
            return Optional.of(this.conditionSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_source_concept_id", insertable = false,
                updatable = false)
    private Concept conditionSourceConcept;
    
    public Optional<Concept> getConditionSourceConcept() {
        return Optional.ofNullable(this.conditionSourceConcept);
    }

    public void setConditionSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.conditionSourceConcept = null;
            this.conditionSourceConceptId = null;
        } else {
            this.conditionSourceConcept = newValue;
            this.conditionSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "condition_source_value", updatable = false, nullable = true)
    private String conditionSourceValue;
    
    public Optional<String> getConditionSourceValue() {
        if (this.conditionSourceValue != null) {
            return Optional.of(this.conditionSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setConditionSourceValue(final String newValue) {
        this.conditionSourceValue = newValue;
    }

    @Column(name = "condition_start_date", updatable = false, nullable = false)
    private ZonedDateTime conditionStartDate;
    
    public Date getConditionStartDate() {
        return new Date(this.conditionStartDate.toLocalDate());
    }

    public void setConditionStartDate(final Date newValue) {
        this.conditionStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "condition_start_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime conditionStartDatetime;
    
    public Optional<DateTime> getConditionStartDatetime() {
        if (this.conditionStartDatetime != null) {
            return Optional.of(new DateTime(this.conditionStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setConditionStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.conditionStartDatetime = null;
        } else {
            this.conditionStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "condition_status_concept_id", updatable = false,
            nullable = true)
    private Integer conditionStatusConceptId;
    
    public Optional<Integer> getConditionStatusConceptId() {
        if (this.conditionStatusConceptId != null) {
            return Optional.of(this.conditionStatusConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_status_concept_id", insertable = false,
                updatable = false)
    private Concept conditionStatusConcept;
    
    public Optional<Concept> getConditionStatusConcept() {
        return Optional.ofNullable(this.conditionStatusConcept);
    }

    public void setConditionStatusConcept(final Concept newValue) {
        if (newValue == null) {
            this.conditionStatusConcept = null;
            this.conditionStatusConceptId = null;
        } else {
            this.conditionStatusConcept = newValue;
            this.conditionStatusConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "condition_status_source_value", updatable = false,
            nullable = true)
    private String conditionStatusSourceValue;
    
    public Optional<String> getConditionStatusSourceValue() {
        if (this.conditionStatusSourceValue != null) {
            return Optional.of(this.conditionStatusSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setConditionStatusSourceValue(final String newValue) {
        this.conditionStatusSourceValue = newValue;
    }

    @Column(name = "condition_type_concept_id", updatable = false,
            nullable = false)
    private Integer conditionTypeConceptId;
    
    public Integer getConditionTypeConceptId() {
        return this.conditionTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_type_concept_id", insertable = false,
                updatable = false)
    private Concept conditionTypeConcept;
    
    public Concept getConditionTypeConcept() {
        return this.conditionTypeConcept;
    }

    public void setConditionTypeConcept(final Concept newValue) {
        this.conditionTypeConcept = newValue;
        this.conditionTypeConceptId = newValue.getConceptId();
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

    @Column(name = "stop_reason", updatable = false, nullable = true)
    private String stopReason;
    
    public Optional<String> getStopReason() {
        if (this.stopReason != null) {
            return Optional.of(this.stopReason);
        } else {
            return Optional.empty();
        }
    }

    public void setStopReason(final String newValue) {
        this.stopReason = newValue;
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Long visitDetailId;
    
    public Optional<Long> getVisitDetailId() {
        if (this.visitDetailId != null) {
            return Optional.of(this.visitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_id", insertable = false, updatable = false)
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

    @Column(name = "visit_occurrence_id", updatable = false, nullable = true)
    private Long visitOccurrenceId;
    
    public Optional<Long> getVisitOccurrenceId() {
        if (this.visitOccurrenceId != null) {
            return Optional.of(this.visitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id", insertable = false,
                updatable = false)
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
        result.append("ConditionOccurrence{");
        result.append("id=");
        result.append(this.conditionOccurrenceId);
        {
            result.append(", concept='");
            result.append(this.getConditionConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
