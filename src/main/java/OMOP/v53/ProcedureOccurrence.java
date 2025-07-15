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

    public void setModifierConcept(final Concept newValue) {
        if (newValue == null) {
            this.modifierConcept = null;
            this.modifierConceptId = null;
        } else {
            this.modifierConcept = newValue;
            this.modifierConceptId = newValue.getConceptId();
        }
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

    public void setModifierSourceValue(final String newValue) {
        this.modifierSourceValue = newValue;
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

    public void setProcedureConcept(final Concept newValue) {
        this.procedureConcept = newValue;
        this.procedureConceptId = newValue.getConceptId();
    }

    @Column(name = "procedure_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime procedureDate;
    
    public Date getProcedureDate() {
        return new Date(this.procedureDate.toLocalDate());
    }

    public void setProcedureDate(final Date newValue) {
        this.procedureDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
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

    public void setProcedureDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.procedureDatetime = null;
        } else {
            this.procedureDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "procedure_occurrence_id", insertable = false,
            updatable = false, nullable = false)
    private Integer procedureOccurrenceId;
    
    public Integer getProcedureOccurrenceId() {
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

    public void setProcedureSourceConceptId(final Integer newValue) {
        this.procedureSourceConceptId = newValue;
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

    public void setProcedureSourceValue(final String newValue) {
        this.procedureSourceValue = newValue;
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

    public void setProcedureTypeConcept(final Concept newValue) {
        this.procedureTypeConcept = newValue;
        this.procedureTypeConceptId = newValue.getConceptId();
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

    public void setProviderId(final Integer newValue) {
        this.providerId = newValue;
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

    public void setQuantity(final Integer newValue) {
        this.quantity = newValue;
    }

    @Column(name = "visit_detail_id", insertable = false, updatable = false,
            nullable = true)
    private Integer visitDetailId;
    
    public Optional<Integer> getVisitDetailId() {
        if (this.visitDetailId != null) {
            return Optional.of(this.visitDetailId);
        } else {
            return Optional.empty();
        }
    }

    public void setVisitDetailId(final Integer newValue) {
        this.visitDetailId = newValue;
    }

    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = true)
    private Integer visitOccurrenceId;
    
    public Optional<Integer> getVisitOccurrenceId() {
        if (this.visitOccurrenceId != null) {
            return Optional.of(this.visitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    public void setVisitOccurrenceId(final Integer newValue) {
        this.visitOccurrenceId = newValue;
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
