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
@Table(name = "domain", schema = "cds_cdm")
public class Domain {

  @Column(name = "domain_concept_id", insertable = false, updatable = false)
  private Integer domainConceptId;
  
  public Optional<Integer> getDomainConceptId() {
    if (this.domainConceptId != null) {
      return Optional.of(this.domainConceptId);
    } else {
      return Optional.empty();
    }
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
    if (this.domainName != null) {
      return Optional.of(this.domainName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "domain_id", insertable = false, updatable = false)
  private String domainId;
  
  public Optional<String> getDomainId() {
    if (this.domainId != null) {
      return Optional.of(this.domainId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "Domain{id=" + this.domainId + "}";
  }
  
  
}
