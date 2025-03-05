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
@Table(name = "vocabulary", schema = "cds_cdm")
public class Vocabulary {

  @Column(name = "vocabulary_concept_id", insertable = false, updatable = false)
  private Integer vocabularyConceptId;
  
  public Optional<Integer> getVocabularyConceptId() {
    if (this.vocabularyConceptId != null) {
      return Optional.of(this.vocabularyConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "vocabulary_concept_id")
  private Concept vocabularyConcept;
  
  public Optional<Concept> getVocabularyConcept() {
    return Optional.ofNullable(this.vocabularyConcept);
  }
  @Column(name = "vocabulary_version", insertable = false, updatable = false)
  private String vocabularyVersion;
  
  public Optional<String> getVocabularyVersion() {
    if (this.vocabularyVersion != null) {
      return Optional.of(this.vocabularyVersion);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "vocabulary_reference", insertable = false, updatable = false)
  private String vocabularyReference;
  
  public Optional<String> getVocabularyReference() {
    if (this.vocabularyReference != null) {
      return Optional.of(this.vocabularyReference);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "vocabulary_name", insertable = false, updatable = false)
  private String vocabularyName;
  
  public Optional<String> getVocabularyName() {
    if (this.vocabularyName != null) {
      return Optional.of(this.vocabularyName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "vocabulary_id", insertable = false, updatable = false)
  private String vocabularyId;
  
  public Optional<String> getVocabularyId() {
    if (this.vocabularyId != null) {
      return Optional.of(this.vocabularyId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Vocabulary{id=").append(this.vocabularyId);
    this.getVocabularyConcept().ifPresent(concept -> {
      result.append(", concept='")
      .append(concept.getConceptName().get())
      .append("'");
    });
    result.append("}");
    return result.toString();
}
  
}
