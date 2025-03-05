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
@Table(name = "concept_ancestor", schema = "cds_cdm")
public class ConceptAncestor {

  @Column(name = "max_levels_of_separation", insertable = false, updatable = false)
  private Integer maxLevelsOfSeparation;
  
  public Optional<Integer> getMaxLevelsOfSeparation() {
    if (this.maxLevelsOfSeparation != null) {
      return Optional.of(this.maxLevelsOfSeparation);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "min_levels_of_separation", insertable = false, updatable = false)
  private Integer minLevelsOfSeparation;
  
  public Optional<Integer> getMinLevelsOfSeparation() {
    if (this.minLevelsOfSeparation != null) {
      return Optional.of(this.minLevelsOfSeparation);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "descendant_concept_id", insertable = false, updatable = false)
  private Integer descendantConceptId;
  
  public Optional<Integer> getDescendantConceptId() {
    if (this.descendantConceptId != null) {
      return Optional.of(this.descendantConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "descendant_concept_id")
  private Concept descendantConcept;
  
  public Optional<Concept> getDescendantConcept() {
    return Optional.ofNullable(this.descendantConcept);
  }
  @Column(name = "ancestor_concept_id", insertable = false, updatable = false)
  private Integer ancestorConceptId;
  
  public Optional<Integer> getAncestorConceptId() {
    if (this.ancestorConceptId != null) {
      return Optional.of(this.ancestorConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ancestor_concept_id")
  private Concept ancestorConcept;
  
  public Optional<Concept> getAncestorConcept() {
    return Optional.ofNullable(this.ancestorConcept);
  }
  
}
