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
@Table(name = "domain", schema = "cds_cdm")
public class Domain {

  @Column(name = "domain_concept_id", insertable = false, updatable = false)
  private Integer domainConceptId;
  
  public Optional<Integer> getDomainConceptId() {
    return Optional.of(this.domainConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_concept_id")
  private Concept domainConcept;
  
  public Optional<Concept> getDomainConcept() {
    return Optional.of(this.domainConcept);
  }
  @Column(name = "domain_name", insertable = false, updatable = false)
  private String domainName;
  
  public Optional<String> getDomainName() {
    return Optional.of(this.domainName);
  }
  
  @Id
  @Column(name = "domain_id", insertable = false, updatable = false)
  private String domainId;
  
  public Optional<String> getDomainId() {
    return Optional.of(this.domainId);
  }
  
  @Override
  public String toString() {
    return "Domain{id=" + this.domainId + "}";
  }
  
  
}
