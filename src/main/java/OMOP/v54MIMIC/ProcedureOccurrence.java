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
@Table(name = "procedure_occurrence", schema = "cds_cdm")
public class ProcedureOccurrence {

    @Column(name = "modifier_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer modifierConceptId;

    public Optional<Integer> getModifierConceptId() {
        if (this.modifierConceptId != null) {
            return Optional.of(this.modifierConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "modifier_concept_id")
    private Concept modifierConcept;
    
    public Optional<Concept> getModifierConcept() {
        return Optional.ofNullable(this.modifierConcept);
    }

    @Column(name = "modifier_source_value", insertable = false,
            updatable = false, nullable = true)
    private String modifierSourceValue;
    
    public Optional<String> getModifierSourceValue() {
        if (this.modifierSourceValue != null) {
            return Optional.of(this.modifierSourceValue);
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

    @Column(name = "procedure_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer procedureConceptId;

    public Integer getProcedureConceptId() {
        return this.procedureConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_concept_id")
    private Concept procedureConcept;
    
    public Concept getProcedureConcept() {
        return this.procedureConcept;
    }

    @Column(name = "procedure_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime procedureDate;
    
    public Date getProcedureDate() {
        return new Date(this.procedureDate.toLocalDate());
    }

    @Column(name = "procedure_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime procedureDatetime;
    
    public Optional<DateTime> getProcedureDatetime() {
        if (this.procedureDatetime != null) {
            return Optional.of(new DateTime(this.procedureDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "procedure_end_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime procedureEndDate;
    
    public Optional<Date> getProcedureEndDate() {
        if (this.procedureEndDate != null) {
            return Optional.of(new Date(this.procedureEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "procedure_end_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime procedureEndDatetime;
    
    public Optional<DateTime> getProcedureEndDatetime() {
        if (this.procedureEndDatetime != null) {
            return Optional.of(new DateTime(this.procedureEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "procedure_occurrence_id", insertable = false,
            updatable = false, nullable = false)
    private Long procedureOccurrenceId;
    
    public Long getProcedureOccurrenceId() {
        return this.procedureOccurrenceId;
    }

    @Column(name = "procedure_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer procedureSourceConceptId;

    public Optional<Integer> getProcedureSourceConceptId() {
        if (this.procedureSourceConceptId != null) {
            return Optional.of(this.procedureSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_source_concept_id")
    private Concept procedureSourceConcept;
    
    public Optional<Concept> getProcedureSourceConcept() {
        return Optional.ofNullable(this.procedureSourceConcept);
    }

    @Column(name = "procedure_source_value", insertable = false,
            updatable = false, nullable = true)
    private String procedureSourceValue;
    
    public Optional<String> getProcedureSourceValue() {
        if (this.procedureSourceValue != null) {
            return Optional.of(this.procedureSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "procedure_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer procedureTypeConceptId;

    public Integer getProcedureTypeConceptId() {
        return this.procedureTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_type_concept_id")
    private Concept procedureTypeConcept;
    
    public Concept getProcedureTypeConcept() {
        return this.procedureTypeConcept;
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

    @Column(name = "quantity", insertable = false, updatable = false,
            nullable = true)
    private Integer quantity;

    public Optional<Integer> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
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
