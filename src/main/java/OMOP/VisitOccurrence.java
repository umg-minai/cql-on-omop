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
@Table(name = "visit_occurrence", schema = "cds_cdm")
public class VisitOccurrence {

  @Column(name = "preceding_visit_occurrence_id", insertable = false, updatable = false)
  private Integer precedingVisitOccurrenceId;
  
  public Optional<Integer> getPrecedingVisitOccurrenceId() {
    return Optional.of(this.precedingVisitOccurrenceId);
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "preceding_visit_occurrence_id")
  private VisitOccurrence precedingVisitOccurrence;
  
  public Optional<VisitOccurrence> getPrecedingVisitOccurrence() {
    return Optional.of(this.precedingVisitOccurrence);
  }
  @Column(name = "discharged_to_source_value", insertable = false, updatable = false)
  private String dischargedToSourceValue;
  
  public Optional<String> getDischargedToSourceValue() {
    return Optional.of(this.dischargedToSourceValue);
  }
  
  @Column(name = "discharged_to_concept_id", insertable = false, updatable = false)
  private Integer dischargedToConceptId;
  
  public Optional<Integer> getDischargedToConceptId() {
    return Optional.of(this.dischargedToConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "discharged_to_concept_id")
  private Concept dischargedToConcept;
  
  public Optional<Concept> getDischargedToConcept() {
    return Optional.of(this.dischargedToConcept);
  }
  @Column(name = "admitted_from_source_value", insertable = false, updatable = false)
  private String admittedFromSourceValue;
  
  public Optional<String> getAdmittedFromSourceValue() {
    return Optional.of(this.admittedFromSourceValue);
  }
  
  @Column(name = "admitted_from_concept_id", insertable = false, updatable = false)
  private Integer admittedFromConceptId;
  
  public Optional<Integer> getAdmittedFromConceptId() {
    return Optional.of(this.admittedFromConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "admitted_from_concept_id")
  private Concept admittedFromConcept;
  
  public Optional<Concept> getAdmittedFromConcept() {
    return Optional.of(this.admittedFromConcept);
  }
  @Column(name = "visit_source_concept_id", insertable = false, updatable = false)
  private Integer visitSourceConceptId;
  
  public Optional<Integer> getVisitSourceConceptId() {
    return Optional.of(this.visitSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_source_concept_id")
  private Concept visitSourceConcept;
  
  public Optional<Concept> getVisitSourceConcept() {
    return Optional.of(this.visitSourceConcept);
  }
  @Column(name = "visit_source_value", insertable = false, updatable = false)
  private String visitSourceValue;
  
  public Optional<String> getVisitSourceValue() {
    return Optional.of(this.visitSourceValue);
  }
  
  @Column(name = "care_site_id", insertable = false, updatable = false)
  private Integer careSiteId;
  
  public Optional<Integer> getCareSiteId() {
    return Optional.of(this.careSiteId);
  }
  
  @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "care_site_id")
  private CareSite careSite;
  
  public Optional<CareSite> getCareSite() {
    return Optional.of(this.careSite);
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
  @Column(name = "visit_type_concept_id", insertable = false, updatable = false)
  private Integer visitTypeConceptId;
  
  public Optional<Integer> getVisitTypeConceptId() {
    return Optional.of(this.visitTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_type_concept_id")
  private Concept visitTypeConcept;
  
  public Optional<Concept> getVisitTypeConcept() {
    return Optional.of(this.visitTypeConcept);
  }
  @Column(name = "visit_concept_id", insertable = false, updatable = false)
  private Integer visitConceptId;
  
  public Optional<Integer> getVisitConceptId() {
    return Optional.of(this.visitConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_concept_id")
  private Concept visitConcept;
  
  public Optional<Concept> getVisitConcept() {
    return Optional.of(this.visitConcept);
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
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    return Optional.of(this.visitOccurrenceId);
  }
  
  @Override
  public String toString() {
    return "VisitOccurrence{id=" + this.visitOccurrenceId + "}";
  }
  
  
}
