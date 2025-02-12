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
@Table(name = "observation_period", schema = "cds_cdm")
public class ObservationPeriod {

  @Column(name = "period_type_concept_id", insertable = false, updatable = false)
  private Integer periodTypeConceptId;
  
  public Optional<Integer> getPeriodTypeConceptId() {
    return Optional.of(this.periodTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "period_type_concept_id")
  private Concept periodTypeConcept;
  
  public Optional<Concept> getPeriodTypeConcept() {
    return Optional.of(this.periodTypeConcept);
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
  @Column(name = "observation_period_id", insertable = false, updatable = false)
  private Integer observationPeriodId;
  
  public Optional<Integer> getObservationPeriodId() {
    return Optional.of(this.observationPeriodId);
  }
  
  @Override
  public String toString() {
    return "ObservationPeriod{id=" + this.observationPeriodId + "}";
  }
  
  
}
