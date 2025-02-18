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
@Table(name = "concept_class", schema = "cds_cdm")
public class ConceptClass {

  @Column(name = "concept_class_concept_id", insertable = false, updatable = false)
  private Integer conceptClassConceptId;
  
  public Optional<Integer> getConceptClassConceptId() {
    if (this.conceptClassConceptId != null) {
      return Optional.of(this.conceptClassConceptId);
    } else {
      return Optional.empty();
    }
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
    if (this.conceptClassName != null) {
      return Optional.of(this.conceptClassName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "concept_class_id", insertable = false, updatable = false)
  private String conceptClassId;
  
  public Optional<String> getConceptClassId() {
    if (this.conceptClassId != null) {
      return Optional.of(this.conceptClassId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "ConceptClass{id=" + this.conceptClassId + "}";
  }
  
  
}
