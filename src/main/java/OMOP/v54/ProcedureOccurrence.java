package OMOP.v54;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "procedure_occurrence", schema = "cds_cdm")
public class ProcedureOccurrence {

  @Column(name = "modifier_source_value", insertable = false, updatable = false)
  private String modifierSourceValue;
  
  public Optional<String> getModifierSourceValue() {
    if (this.modifierSourceValue != null) {
      return Optional.of(this.modifierSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "procedure_source_concept_id", insertable = false, updatable = false)
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
  @Column(name = "procedure_source_value", insertable = false, updatable = false)
  private String procedureSourceValue;
  
  public Optional<String> getProcedureSourceValue() {
    if (this.procedureSourceValue != null) {
      return Optional.of(this.procedureSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
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
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
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
  @Column(name = "provider_id", insertable = false, updatable = false)
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
  @Column(name = "quantity", insertable = false, updatable = false)
  private Integer quantity;
  
  public Optional<Integer> getQuantity() {
    if (this.quantity != null) {
      return Optional.of(this.quantity);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "modifier_concept_id", insertable = false, updatable = false)
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
  @Column(name = "procedure_type_concept_id", insertable = false, updatable = false)
  private Integer procedureTypeConceptId;
  
  public Optional<Integer> getProcedureTypeConceptId() {
    if (this.procedureTypeConceptId != null) {
      return Optional.of(this.procedureTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "procedure_type_concept_id")
  private Concept procedureTypeConcept;
  
  public Optional<Concept> getProcedureTypeConcept() {
    return Optional.ofNullable(this.procedureTypeConcept);
  }
  @Column(name = "procedure_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime procedureEndDatetime;
  
  public Optional<DateTime> getProcedureEndDatetime() {
    if (this.procedureEndDatetime != null) {
      return Optional.of(new DateTime(this.procedureEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "procedure_end_date", insertable = false, updatable = false)
  private ZonedDateTime procedureEndDate;
  
  public Optional<Date> getProcedureEndDate() {
    if (this.procedureEndDate != null) {
      return Optional.of(new Date(this.procedureEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "procedure_datetime", insertable = false, updatable = false)
  private ZonedDateTime procedureDatetime;
  
  public Optional<DateTime> getProcedureDatetime() {
    if (this.procedureDatetime != null) {
      return Optional.of(new DateTime(this.procedureDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "procedure_date", insertable = false, updatable = false)
  private ZonedDateTime procedureDate;
  
  public Optional<Date> getProcedureDate() {
    if (this.procedureDate != null) {
      return Optional.of(new Date(this.procedureDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "procedure_concept_id", insertable = false, updatable = false)
  private Integer procedureConceptId;
  
  public Optional<Integer> getProcedureConceptId() {
    if (this.procedureConceptId != null) {
      return Optional.of(this.procedureConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "procedure_concept_id")
  private Concept procedureConcept;
  
  public Optional<Concept> getProcedureConcept() {
    return Optional.ofNullable(this.procedureConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.ofNullable(this.person);
  }
  @Id
  @Column(name = "procedure_occurrence_id", insertable = false, updatable = false)
  private Integer procedureOccurrenceId;
  
  public Optional<Integer> getProcedureOccurrenceId() {
    if (this.procedureOccurrenceId != null) {
      return Optional.of(this.procedureOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("ProcedureOccurrence{id=").append(this.procedureOccurrenceId);
    this.getProcedureConcept().ifPresent(concept -> {
      result.append(", concept='")
      .append(concept.getConceptName().get())
      .append("'");
    });
    result.append("}");
    return result.toString();
}
  
}
