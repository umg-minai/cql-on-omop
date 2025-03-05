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
@Table(name = "relationship", schema = "cds_cdm")
public class Relationship {

  @Column(name = "relationship_concept_id", insertable = false, updatable = false)
  private Integer relationshipConceptId;
  
  public Optional<Integer> getRelationshipConceptId() {
    if (this.relationshipConceptId != null) {
      return Optional.of(this.relationshipConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "relationship_concept_id")
  private Concept relationshipConcept;
  
  public Optional<Concept> getRelationshipConcept() {
    return Optional.ofNullable(this.relationshipConcept);
  }
  @Column(name = "reverse_relationship_id", insertable = false, updatable = false)
  private String reverseRelationshipId;
  
  public Optional<String> getReverseRelationshipId() {
    if (this.reverseRelationshipId != null) {
      return Optional.of(this.reverseRelationshipId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "defines_ancestry", insertable = false, updatable = false)
  private String definesAncestry;
  
  public Optional<String> getDefinesAncestry() {
    if (this.definesAncestry != null) {
      return Optional.of(this.definesAncestry);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "is_hierarchical", insertable = false, updatable = false)
  private String isHierarchical;
  
  public Optional<String> getIsHierarchical() {
    if (this.isHierarchical != null) {
      return Optional.of(this.isHierarchical);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "relationship_name", insertable = false, updatable = false)
  private String relationshipName;
  
  public Optional<String> getRelationshipName() {
    if (this.relationshipName != null) {
      return Optional.of(this.relationshipName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "relationship_id", insertable = false, updatable = false)
  private String relationshipId;
  
  public Optional<String> getRelationshipId() {
    if (this.relationshipId != null) {
      return Optional.of(this.relationshipId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Relationship{id=").append(this.relationshipId);
    this.getRelationshipConcept().ifPresent(concept -> {
      result.append(", concept='")
      .append(concept.getConceptName().get())
      .append("'");
    });
    result.append("}");
    return result.toString();
}
  
}
