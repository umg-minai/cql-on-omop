package OMOP;

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

@Entity
@Table(name = "procedure_occurrence", schema = "cds_cdm")
public class ProcedureOccurrence {

  @Column(name = "modifier_source_value", insertable = false, updatable = false)
  private String modifierSourceValue;
  
  public Optional<String> getModifierSourceValue() {
    return Optional.of(this.modifierSourceValue);
  }
  
  @Column(name = "procedure_source_concept_id", insertable = false, updatable = false)
  private Integer procedureSourceConceptId;
  
  public Optional<Integer> getProcedureSourceConceptId() {
    return Optional.of(this.procedureSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "procedure_source_concept_id")
  private Concept procedureSourceConcept;
  
  public Optional<Concept> getProcedureSourceConcept() {
    return Optional.of(this.procedureSourceConcept);
  }
  @Column(name = "procedure_source_value", insertable = false, updatable = false)
  private String procedureSourceValue;
  
  public Optional<String> getProcedureSourceValue() {
    return Optional.of(this.procedureSourceValue);
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    return Optional.of(this.visitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.of(this.visitDetail);
  }
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    return Optional.of(this.visitOccurrenceId);
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.of(this.visitOccurrence);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    return Optional.of(this.providerId);
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.of(this.provider);
  }
  @Column(name = "quantity", insertable = false, updatable = false)
  private Integer quantity;
  
  public Optional<Integer> getQuantity() {
    return Optional.of(this.quantity);
  }
  
  @Column(name = "modifier_concept_id", insertable = false, updatable = false)
  private Integer modifierConceptId;
  
  public Optional<Integer> getModifierConceptId() {
    return Optional.of(this.modifierConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "modifier_concept_id")
  private Concept modifierConcept;
  
  public Optional<Concept> getModifierConcept() {
    return Optional.of(this.modifierConcept);
  }
  @Column(name = "procedure_type_concept_id", insertable = false, updatable = false)
  private Integer procedureTypeConceptId;
  
  public Optional<Integer> getProcedureTypeConceptId() {
    return Optional.of(this.procedureTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "procedure_type_concept_id")
  private Concept procedureTypeConcept;
  
  public Optional<Concept> getProcedureTypeConcept() {
    return Optional.of(this.procedureTypeConcept);
  }
  @Column(name = "procedure_concept_id", insertable = false, updatable = false)
  private Integer procedureConceptId;
  
  public Optional<Integer> getProcedureConceptId() {
    return Optional.of(this.procedureConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "procedure_concept_id")
  private Concept procedureConcept;
  
  public Optional<Concept> getProcedureConcept() {
    return Optional.of(this.procedureConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    return Optional.of(this.personId);
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "procedure_occurrence_id", insertable = false, updatable = false)
  private Integer procedureOccurrenceId;
  
  public Optional<Integer> getProcedureOccurrenceId() {
    return Optional.of(this.procedureOccurrenceId);
  }
  
  @Override
  public String toString() {
    return "ProcedureOccurrence{id=" + this.procedureOccurrenceId + "}";
  }
  
  
}
