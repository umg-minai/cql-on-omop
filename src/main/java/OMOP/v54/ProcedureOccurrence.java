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
@Table(name = "procedure_occurrence", schema = "cds_cdm")
public class ProcedureOccurrence {

    @Column(name = "modifier_concept_id", updatable = false, nullable = true)
    private Integer modifierConceptId;
    
    public Optional<Integer> getModifierConceptId() {
        if (this.modifierConceptId != null) {
            return Optional.of(this.modifierConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_concept_id", insertable = false,
                updatable = false)
    private Concept modifierConcept;
    
    public Optional<Concept> getModifierConcept() {
        return Optional.ofNullable(this.modifierConcept);
    }

    public void setModifierConcept(final Concept newValue) {
        if (newValue == null) {
            this.modifierConcept = null;
            this.modifierConceptId = null;
        } else {
            this.modifierConcept = newValue;
            this.modifierConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "modifier_source_value", updatable = false, nullable = true)
    private String modifierSourceValue;
    
    public Optional<String> getModifierSourceValue() {
        if (this.modifierSourceValue != null) {
            return Optional.of(this.modifierSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setModifierSourceValue(final String newValue) {
        this.modifierSourceValue = newValue;
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
        this.personId = newValue.getPersonId();
    }

    @Column(name = "procedure_concept_id", updatable = false, nullable = false)
    private Integer procedureConceptId;
    
    public Integer getProcedureConceptId() {
        return this.procedureConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_concept_id", insertable = false,
                updatable = false)
    private Concept procedureConcept;
    
    public Concept getProcedureConcept() {
        return this.procedureConcept;
    }

    public void setProcedureConcept(final Concept newValue) {
        this.procedureConcept = newValue;
        this.procedureConceptId = newValue.getConceptId();
    }

    @Column(name = "procedure_date", updatable = false, nullable = false)
    private ZonedDateTime procedureDate;
    
    public Date getProcedureDate() {
        return new Date(this.procedureDate.toLocalDate());
    }

    public void setProcedureDate(final Date newValue) {
        this.procedureDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "procedure_datetime", updatable = false, nullable = true)
    private ZonedDateTime procedureDatetime;
    
    public Optional<DateTime> getProcedureDatetime() {
        if (this.procedureDatetime != null) {
            return Optional.of(new DateTime(this.procedureDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setProcedureDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.procedureDatetime = null;
        } else {
            this.procedureDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "procedure_end_date", updatable = false, nullable = true)
    private ZonedDateTime procedureEndDate;
    
    public Optional<Date> getProcedureEndDate() {
        if (this.procedureEndDate != null) {
            return Optional.of(new Date(this.procedureEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setProcedureEndDate(final Date newValue) {
        if (newValue == null) {
            this.procedureEndDate = null;
        } else {
            this.procedureEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    @Column(name = "procedure_end_datetime", updatable = false, nullable = true)
    private ZonedDateTime procedureEndDatetime;
    
    public Optional<DateTime> getProcedureEndDatetime() {
        if (this.procedureEndDatetime != null) {
            return Optional.of(new DateTime(this.procedureEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setProcedureEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.procedureEndDatetime = null;
        } else {
            this.procedureEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedure_occurrence_id", updatable = false,
            nullable = false)
    private Integer procedureOccurrenceId;
    
    public Integer getProcedureOccurrenceId() {
        return this.procedureOccurrenceId;
    }

    @Column(name = "procedure_source_concept_id", updatable = false,
            nullable = true)
    private Integer procedureSourceConceptId;
    
    public Optional<Integer> getProcedureSourceConceptId() {
        if (this.procedureSourceConceptId != null) {
            return Optional.of(this.procedureSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_source_concept_id", insertable = false,
                updatable = false)
    private Concept procedureSourceConcept;
    
    public Optional<Concept> getProcedureSourceConcept() {
        return Optional.ofNullable(this.procedureSourceConcept);
    }

    public void setProcedureSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.procedureSourceConcept = null;
            this.procedureSourceConceptId = null;
        } else {
            this.procedureSourceConcept = newValue;
            this.procedureSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "procedure_source_value", updatable = false, nullable = true)
    private String procedureSourceValue;
    
    public Optional<String> getProcedureSourceValue() {
        if (this.procedureSourceValue != null) {
            return Optional.of(this.procedureSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setProcedureSourceValue(final String newValue) {
        this.procedureSourceValue = newValue;
    }

    @Column(name = "procedure_type_concept_id", updatable = false,
            nullable = false)
    private Integer procedureTypeConceptId;
    
    public Integer getProcedureTypeConceptId() {
        return this.procedureTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_type_concept_id", insertable = false,
                updatable = false)
    private Concept procedureTypeConcept;
    
    public Concept getProcedureTypeConcept() {
        return this.procedureTypeConcept;
    }

    public void setProcedureTypeConcept(final Concept newValue) {
        this.procedureTypeConcept = newValue;
        this.procedureTypeConceptId = newValue.getConceptId();
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

    @Column(name = "quantity", updatable = false, nullable = true)
    private Integer quantity;
    
    public Optional<Integer> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
        } else {
            return Optional.empty();
        }
    }

    public void setQuantity(final Integer newValue) {
        this.quantity = newValue;
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Integer visitDetailId;
    
    public Optional<Integer> getVisitDetailId() {
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
    private Integer visitOccurrenceId;
    
    public Optional<Integer> getVisitOccurrenceId() {
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
        result.append("ProcedureOccurrence{");
        result.append("id=");
        result.append(this.procedureOccurrenceId);
        {
            result.append(", concept='");
            result.append(this.getProcedureConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
