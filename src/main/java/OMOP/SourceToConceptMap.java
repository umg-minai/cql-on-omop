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
@Table(name = "source_to_concept_map", schema = "cds_cdm")
public class SourceToConceptMap {

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    return Optional.of(this.invalidReason);
  }
  
  @Column(name = "target_vocabulary_id", insertable = false, updatable = false)
  private String targetVocabularyId;
  
  public Optional<String> getTargetVocabularyId() {
    return Optional.of(this.targetVocabularyId);
  }
  
  @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "target_vocabulary_id")
  private Vocabulary targetVocabulary;
  
  public Optional<Vocabulary> getTargetVocabulary() {
    return Optional.of(this.targetVocabulary);
  }
  @Column(name = "target_concept_id", insertable = false, updatable = false)
  private Integer targetConceptId;
  
  public Optional<Integer> getTargetConceptId() {
    return Optional.of(this.targetConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "target_concept_id")
  private Concept targetConcept;
  
  public Optional<Concept> getTargetConcept() {
    return Optional.of(this.targetConcept);
  }
  @Column(name = "source_code_description", insertable = false, updatable = false)
  private String sourceCodeDescription;
  
  public Optional<String> getSourceCodeDescription() {
    return Optional.of(this.sourceCodeDescription);
  }
  
  @Column(name = "source_vocabulary_id", insertable = false, updatable = false)
  private String sourceVocabularyId;
  
  public Optional<String> getSourceVocabularyId() {
    return Optional.of(this.sourceVocabularyId);
  }
  
  @Column(name = "source_concept_id", insertable = false, updatable = false)
  private Integer sourceConceptId;
  
  public Optional<Integer> getSourceConceptId() {
    return Optional.of(this.sourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "source_concept_id")
  private Concept sourceConcept;
  
  public Optional<Concept> getSourceConcept() {
    return Optional.of(this.sourceConcept);
  }
  @Column(name = "source_code", insertable = false, updatable = false)
  private String sourceCode;
  
  public Optional<String> getSourceCode() {
    return Optional.of(this.sourceCode);
  }
  
  
}
