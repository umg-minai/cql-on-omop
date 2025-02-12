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
@Table(name = "vocabulary", schema = "cds_cdm")
public class Vocabulary {

  @Column(name = "vocabulary_concept_id", insertable = false, updatable = false)
  private Integer vocabularyConceptId;
  
  public Optional<Integer> getVocabularyConceptId() {
    return Optional.of(this.vocabularyConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "vocabulary_concept_id")
  private Concept vocabularyConcept;
  
  public Optional<Concept> getVocabularyConcept() {
    return Optional.of(this.vocabularyConcept);
  }
  @Column(name = "vocabulary_version", insertable = false, updatable = false)
  private String vocabularyVersion;
  
  public Optional<String> getVocabularyVersion() {
    return Optional.of(this.vocabularyVersion);
  }
  
  @Column(name = "vocabulary_reference", insertable = false, updatable = false)
  private String vocabularyReference;
  
  public Optional<String> getVocabularyReference() {
    return Optional.of(this.vocabularyReference);
  }
  
  @Column(name = "vocabulary_name", insertable = false, updatable = false)
  private String vocabularyName;
  
  public Optional<String> getVocabularyName() {
    return Optional.of(this.vocabularyName);
  }
  
  @Id
  @Column(name = "vocabulary_id", insertable = false, updatable = false)
  private String vocabularyId;
  
  public Optional<String> getVocabularyId() {
    return Optional.of(this.vocabularyId);
  }
  
  @Override
  public String toString() {
    return "Vocabulary{id=" + this.vocabularyId + "}";
  }
  
  
}
