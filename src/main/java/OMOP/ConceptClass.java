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
@Table(name = "concept_class", schema = "cds_cdm")
public class ConceptClass {

  @Column(name = "concept_class_concept_id", insertable = false, updatable = false)
  private Integer conceptClassConceptId;
  
  public Optional<Integer> getConceptClassConceptId() {
    return Optional.of(this.conceptClassConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "concept_class_concept_id")
  private Concept conceptClassConcept;
  
  public Optional<Concept> getConceptClassConcept() {
    return Optional.of(this.conceptClassConcept);
  }
  @Column(name = "concept_class_name", insertable = false, updatable = false)
  private String conceptClassName;
  
  public Optional<String> getConceptClassName() {
    return Optional.of(this.conceptClassName);
  }
  
  @Id
  @Column(name = "concept_class_id", insertable = false, updatable = false)
  private String conceptClassId;
  
  public Optional<String> getConceptClassId() {
    return Optional.of(this.conceptClassId);
  }
  
  @Override
  public String toString() {
    return "ConceptClass{id=" + this.conceptClassId + "}";
  }
  
  
}
