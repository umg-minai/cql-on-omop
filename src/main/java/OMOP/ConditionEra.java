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
@Table(name = "condition_era", schema = "cds_cdm")
public class ConditionEra {

  @Column(name = "condition_occurrence_count", insertable = false, updatable = false)
  private Integer conditionOccurrenceCount;
  
  public Optional<Integer> getConditionOccurrenceCount() {
    return Optional.of(this.conditionOccurrenceCount);
  }
  
  @Column(name = "condition_concept_id", insertable = false, updatable = false)
  private Integer conditionConceptId;
  
  public Optional<Integer> getConditionConceptId() {
    return Optional.of(this.conditionConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_concept_id")
  private Concept conditionConcept;
  
  public Optional<Concept> getConditionConcept() {
    return Optional.of(this.conditionConcept);
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
  @Column(name = "condition_era_id", insertable = false, updatable = false)
  private Integer conditionEraId;
  
  public Optional<Integer> getConditionEraId() {
    return Optional.of(this.conditionEraId);
  }
  
  @Override
  public String toString() {
    return "ConditionEra{id=" + this.conditionEraId + "}";
  }
  
  
}
