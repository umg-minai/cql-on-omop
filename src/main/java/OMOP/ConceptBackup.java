package OMOP;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "concept", schema = "cds_cdm")
public class ConceptBackup {

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    return Optional.of(this.invalidReason);
  }
  
  @Column(name = "concept_code", insertable = false, updatable = false)
  private String conceptCode;
  
  public Optional<String> getConceptCode() {
    return Optional.of(this.conceptCode);
  }
  
  @Column(name = "standard_concept", insertable = false, updatable = false)
  private String standardConcept;
  
  public Optional<String> getStandardConcept() {
    return Optional.of(this.standardConcept);
  }
  
  @Column(name = "concept_class_id", insertable = false, updatable = false)
  private String conceptClassId;
  
  public Optional<String> getConceptClassId() {
    return Optional.of(this.conceptClassId);
  }
  
  @ManyToOne(targetEntity = ConceptClass.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "concept_class_id")
  private ConceptClass conceptClass;
  
  public Optional<ConceptClass> getConceptClass() {
    return Optional.of(this.conceptClass);
  }
  @Column(name = "vocabulary_id", insertable = false, updatable = false)
  private String vocabularyId;
  
  public Optional<String> getVocabularyId() {
    return Optional.of(this.vocabularyId);
  }
  
  @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "vocabulary_id")
  private Vocabulary vocabulary;
  
  public Optional<Vocabulary> getVocabulary() {
    return Optional.of(this.vocabulary);
  }
  @Column(name = "domain_id", insertable = false, updatable = false)
  private String domainId;
  
  public Optional<String> getDomainId() {
    return Optional.of(this.domainId);
  }
  
  @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_id")
  private Domain domain;
  
  public Optional<Domain> getDomain() {
    return Optional.of(this.domain);
  }
  @Column(name = "concept_name", insertable = false, updatable = false)
  private String conceptName;
  
  public Optional<String> getConceptName() {
    return Optional.of(this.conceptName);
  }
  
  @Id
  @Column(name = "concept_id", insertable = false, updatable = false)
  private Integer conceptId;
  
  public Optional<Integer> getConceptId() {
    return Optional.of(this.conceptId);
  }
  
  @Override
  public String toString() {
    return "Concept{id=" + this.conceptId + "}";
  }

  @ManyToMany(targetEntity = ConceptBackup.class, fetch = FetchType.LAZY)
  @JoinTable(name="concept_ancestor", schema="cds_cdm", joinColumns = {
          @JoinColumn(name="descendant_concept_id")
  },
  inverseJoinColumns = {
          @JoinColumn(name="ancestor_concept_id")
  })
  //@JoinFormula(value = "SELECT ca.ancestor_id FROM cds_cdm.concept_ancestors ca WHERE ca.descendant_id = concept_id")
  private List<ConceptBackup> ancestors;
  
  public List<ConceptBackup> getAncestors() {
    return this.ancestors;
                          }
}
