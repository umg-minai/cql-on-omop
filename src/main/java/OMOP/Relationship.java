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
@Table(name = "relationship", schema = "cds_cdm")
public class Relationship {

  @Column(name = "relationship_concept_id", insertable = false, updatable = false)
  private Integer relationshipConceptId;
  
  public Optional<Integer> getRelationshipConceptId() {
    return Optional.of(this.relationshipConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "relationship_concept_id")
  private Concept relationshipConcept;
  
  public Optional<Concept> getRelationshipConcept() {
    return Optional.of(this.relationshipConcept);
  }
  @Column(name = "reverse_relationship_id", insertable = false, updatable = false)
  private String reverseRelationshipId;
  
  public Optional<String> getReverseRelationshipId() {
    return Optional.of(this.reverseRelationshipId);
  }
  
  @Column(name = "defines_ancestry", insertable = false, updatable = false)
  private String definesAncestry;
  
  public Optional<String> getDefinesAncestry() {
    return Optional.of(this.definesAncestry);
  }
  
  @Column(name = "is_hierarchical", insertable = false, updatable = false)
  private String isHierarchical;
  
  public Optional<String> getIsHierarchical() {
    return Optional.of(this.isHierarchical);
  }
  
  @Column(name = "relationship_name", insertable = false, updatable = false)
  private String relationshipName;
  
  public Optional<String> getRelationshipName() {
    return Optional.of(this.relationshipName);
  }
  
  @Id
  @Column(name = "relationship_id", insertable = false, updatable = false)
  private String relationshipId;
  
  public Optional<String> getRelationshipId() {
    return Optional.of(this.relationshipId);
  }
  
  @Override
  public String toString() {
    return "Relationship{id=" + this.relationshipId + "}";
  }
  
  
}
