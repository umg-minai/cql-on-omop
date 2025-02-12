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
@Table(name = "fact_relationship", schema = "cds_cdm")
public class FactRelationship {

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
  @Column(name = "fact_id_2", insertable = false, updatable = false)
  private Integer factId2;
  
  public Optional<Integer> getFactId2() {
    return Optional.of(this.factId2);
  }
  
  @Column(name = "domain_concept_id_2", insertable = false, updatable = false)
  private Integer domainConceptId2;
  
  public Optional<Integer> getDomainConceptId2() {
    return Optional.of(this.domainConceptId2);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_concept_id_2")
  private Concept domainConcept2;
  
  public Optional<Concept> getDomainConcept2() {
    return Optional.of(this.domainConcept2);
  }
  @Column(name = "fact_id_1", insertable = false, updatable = false)
  private Integer factId1;
  
  public Optional<Integer> getFactId1() {
    return Optional.of(this.factId1);
  }
  
  @Column(name = "domain_concept_id_1", insertable = false, updatable = false)
  private Integer domainConceptId1;
  
  public Optional<Integer> getDomainConceptId1() {
    return Optional.of(this.domainConceptId1);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_concept_id_1")
  private Concept domainConcept1;
  
  public Optional<Concept> getDomainConcept1() {
    return Optional.of(this.domainConcept1);
  }
  
}
