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
@Table(name = "source_to_concept_map", schema = "cds_cdm")
public class SourceToConceptMap {

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    if (this.invalidReason != null) {
      return Optional.of(this.invalidReason);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_end_date", insertable = false, updatable = false)
  private ZonedDateTime validEndDate;
  
  public Optional<Date> getValidEndDate() {
    if (this.validEndDate != null) {
      return Optional.of(new Date(this.validEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_start_date", insertable = false, updatable = false)
  private ZonedDateTime validStartDate;
  
  public Optional<Date> getValidStartDate() {
    if (this.validStartDate != null) {
      return Optional.of(new Date(this.validStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "target_vocabulary_id", insertable = false, updatable = false)
  private String targetVocabularyId;
  
  public Optional<String> getTargetVocabularyId() {
    if (this.targetVocabularyId != null) {
      return Optional.of(this.targetVocabularyId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "target_vocabulary_id")
  private Vocabulary targetVocabulary;
  
  public Optional<Vocabulary> getTargetVocabulary() {
    return Optional.ofNullable(this.targetVocabulary);
  }
  @Column(name = "target_concept_id", insertable = false, updatable = false)
  private Integer targetConceptId;
  
  public Optional<Integer> getTargetConceptId() {
    if (this.targetConceptId != null) {
      return Optional.of(this.targetConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "target_concept_id")
  private Concept targetConcept;
  
  public Optional<Concept> getTargetConcept() {
    return Optional.ofNullable(this.targetConcept);
  }
  @Column(name = "source_code_description", insertable = false, updatable = false)
  private String sourceCodeDescription;
  
  public Optional<String> getSourceCodeDescription() {
    if (this.sourceCodeDescription != null) {
      return Optional.of(this.sourceCodeDescription);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "source_vocabulary_id", insertable = false, updatable = false)
  private String sourceVocabularyId;
  
  public Optional<String> getSourceVocabularyId() {
    if (this.sourceVocabularyId != null) {
      return Optional.of(this.sourceVocabularyId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "source_concept_id", insertable = false, updatable = false)
  private Integer sourceConceptId;
  
  public Optional<Integer> getSourceConceptId() {
    if (this.sourceConceptId != null) {
      return Optional.of(this.sourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "source_concept_id")
  private Concept sourceConcept;
  
  public Optional<Concept> getSourceConcept() {
    return Optional.ofNullable(this.sourceConcept);
  }
  @Column(name = "source_code", insertable = false, updatable = false)
  private String sourceCode;
  
  public Optional<String> getSourceCode() {
    if (this.sourceCode != null) {
      return Optional.of(this.sourceCode);
    } else {
      return Optional.empty();
    }
  }
  
  
  
}
