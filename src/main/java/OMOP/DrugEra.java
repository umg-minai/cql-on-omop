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
@Table(name = "drug_era", schema = "cds_cdm")
public class DrugEra {

  @Column(name = "gap_days", insertable = false, updatable = false)
  private Integer gapDays;
  
  public Optional<Integer> getGapDays() {
    return Optional.of(this.gapDays);
  }
  
  @Column(name = "drug_exposure_count", insertable = false, updatable = false)
  private Integer drugExposureCount;
  
  public Optional<Integer> getDrugExposureCount() {
    return Optional.of(this.drugExposureCount);
  }
  
  @Column(name = "drug_concept_id", insertable = false, updatable = false)
  private Integer drugConceptId;
  
  public Optional<Integer> getDrugConceptId() {
    return Optional.of(this.drugConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.of(this.drugConcept);
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
  @Column(name = "drug_era_id", insertable = false, updatable = false)
  private Integer drugEraId;
  
  public Optional<Integer> getDrugEraId() {
    return Optional.of(this.drugEraId);
  }
  
  @Override
  public String toString() {
    return "DrugEra{id=" + this.drugEraId + "}";
  }
  
  
}
