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
@Table(name = "concept_synonym", schema = "cds_cdm")
public class ConceptSynonym {

  @Column(name = "language_concept_id", insertable = false, updatable = false)
  private Integer languageConceptId;
  
  public Optional<Integer> getLanguageConceptId() {
    if (this.languageConceptId != null) {
      return Optional.of(this.languageConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "language_concept_id")
  private Concept languageConcept;
  
  public Optional<Concept> getLanguageConcept() {
    return Optional.of(this.languageConcept);
  }
  @Column(name = "concept_synonym_name", insertable = false, updatable = false)
  private String conceptSynonymName;
  
  public Optional<String> getConceptSynonymName() {
    if (this.conceptSynonymName != null) {
      return Optional.of(this.conceptSynonymName);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "concept_id", insertable = false, updatable = false)
  private Integer conceptId;
  
  public Optional<Integer> getConceptId() {
    if (this.conceptId != null) {
      return Optional.of(this.conceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "concept_id")
  private Concept concept;
  
  public Optional<Concept> getConcept() {
    return Optional.of(this.concept);
  }
  
}
