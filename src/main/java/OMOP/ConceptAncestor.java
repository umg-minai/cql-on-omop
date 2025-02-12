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
@Table(name = "concept_ancestor", schema = "cds_cdm")
public class ConceptAncestor {

  @Column(name = "max_levels_of_separation", insertable = false, updatable = false)
  private Integer maxLevelsOfSeparation;
  
  public Optional<Integer> getMaxLevelsOfSeparation() {
    return Optional.of(this.maxLevelsOfSeparation);
  }
  
  @Column(name = "min_levels_of_separation", insertable = false, updatable = false)
  private Integer minLevelsOfSeparation;
  
  public Optional<Integer> getMinLevelsOfSeparation() {
    return Optional.of(this.minLevelsOfSeparation);
  }
  
  @Column(name = "descendant_concept_id", insertable = false, updatable = false)
  private Integer descendantConceptId;
  
  public Optional<Integer> getDescendantConceptId() {
    return Optional.of(this.descendantConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "descendant_concept_id")
  private Concept descendantConcept;
  
  public Optional<Concept> getDescendantConcept() {
    return Optional.of(this.descendantConcept);
  }
  @Column(name = "ancestor_concept_id", insertable = false, updatable = false)
  private Integer ancestorConceptId;
  
  public Optional<Integer> getAncestorConceptId() {
    return Optional.of(this.ancestorConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ancestor_concept_id")
  private Concept ancestorConcept;
  
  public Optional<Concept> getAncestorConcept() {
    return Optional.of(this.ancestorConcept);
  }
  
}
