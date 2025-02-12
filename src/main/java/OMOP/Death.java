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
@Table(name = "death", schema = "cds_cdm")
public class Death {

  @Column(name = "cause_source_concept_id", insertable = false, updatable = false)
  private Integer causeSourceConceptId;
  
  public Optional<Integer> getCauseSourceConceptId() {
    return Optional.of(this.causeSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cause_source_concept_id")
  private Concept causeSourceConcept;
  
  public Optional<Concept> getCauseSourceConcept() {
    return Optional.of(this.causeSourceConcept);
  }
  @Column(name = "cause_source_value", insertable = false, updatable = false)
  private String causeSourceValue;
  
  public Optional<String> getCauseSourceValue() {
    return Optional.of(this.causeSourceValue);
  }
  
  @Column(name = "cause_concept_id", insertable = false, updatable = false)
  private Integer causeConceptId;
  
  public Optional<Integer> getCauseConceptId() {
    return Optional.of(this.causeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cause_concept_id")
  private Concept causeConcept;
  
  public Optional<Concept> getCauseConcept() {
    return Optional.of(this.causeConcept);
  }
  @Column(name = "death_type_concept_id", insertable = false, updatable = false)
  private Integer deathTypeConceptId;
  
  public Optional<Integer> getDeathTypeConceptId() {
    return Optional.of(this.deathTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "death_type_concept_id")
  private Concept deathTypeConcept;
  
  public Optional<Concept> getDeathTypeConcept() {
    return Optional.of(this.deathTypeConcept);
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
  
}
