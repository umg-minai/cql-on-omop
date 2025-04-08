package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "fact_relationship", schema = "cds_cdm")
public class FactRelationship {

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
  @Column(name = "fact_id_2", insertable = false, updatable = false)
  private Integer factId2;
  
  public Optional<Integer> getFactId2() {
    if (this.factId2 != null) {
      return Optional.of(this.factId2);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "domain_concept_id_2", insertable = false, updatable = false)
  private Integer domainConceptId2;
  
  public Optional<Integer> getDomainConceptId2() {
    if (this.domainConceptId2 != null) {
      return Optional.of(this.domainConceptId2);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_concept_id_2")
  private Concept domainConcept2;
  
  public Optional<Concept> getDomainConcept2() {
    return Optional.ofNullable(this.domainConcept2);
  }
  @Column(name = "fact_id_1", insertable = false, updatable = false)
  private Integer factId1;
  
  public Optional<Integer> getFactId1() {
    if (this.factId1 != null) {
      return Optional.of(this.factId1);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "domain_concept_id_1", insertable = false, updatable = false)
  private Integer domainConceptId1;
  
  public Optional<Integer> getDomainConceptId1() {
    if (this.domainConceptId1 != null) {
      return Optional.of(this.domainConceptId1);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_concept_id_1")
  private Concept domainConcept1;
  
  public Optional<Concept> getDomainConcept1() {
    return Optional.ofNullable(this.domainConcept1);
  }
  
  
}
