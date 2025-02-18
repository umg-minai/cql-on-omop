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
@Table(name = "note_nlp", schema = "cds_cdm")
public class NoteNlp {

  @Column(name = "term_modifiers", insertable = false, updatable = false)
  private String termModifiers;
  
  public Optional<String> getTermModifiers() {
    if (this.termModifiers != null) {
      return Optional.of(this.termModifiers);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "term_temporal", insertable = false, updatable = false)
  private String termTemporal;
  
  public Optional<String> getTermTemporal() {
    if (this.termTemporal != null) {
      return Optional.of(this.termTemporal);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "term_exists", insertable = false, updatable = false)
  private String termExists;
  
  public Optional<String> getTermExists() {
    if (this.termExists != null) {
      return Optional.of(this.termExists);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "nlp_datetime", insertable = false, updatable = false)
  private ZonedDateTime nlpDatetime;
  
  public Optional<DateTime> getNlpDatetime() {
    if (this.nlpDatetime != null) {
      return Optional.of(new DateTime(this.nlpDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "nlp_date", insertable = false, updatable = false)
  private ZonedDateTime nlpDate;
  
  public Optional<Date> getNlpDate() {
    if (this.nlpDate != null) {
      return Optional.of(new Date(this.nlpDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "nlp_system", insertable = false, updatable = false)
  private String nlpSystem;
  
  public Optional<String> getNlpSystem() {
    if (this.nlpSystem != null) {
      return Optional.of(this.nlpSystem);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "note_nlp_source_concept_id", insertable = false, updatable = false)
  private Integer noteNlpSourceConceptId;
  
  public Optional<Integer> getNoteNlpSourceConceptId() {
    if (this.noteNlpSourceConceptId != null) {
      return Optional.of(this.noteNlpSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_nlp_source_concept_id")
  private Concept noteNlpSourceConcept;
  
  public Optional<Concept> getNoteNlpSourceConcept() {
    return Optional.of(this.noteNlpSourceConcept);
  }
  @Column(name = "note_nlp_concept_id", insertable = false, updatable = false)
  private Integer noteNlpConceptId;
  
  public Optional<Integer> getNoteNlpConceptId() {
    if (this.noteNlpConceptId != null) {
      return Optional.of(this.noteNlpConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_nlp_concept_id")
  private Concept noteNlpConcept;
  
  public Optional<Concept> getNoteNlpConcept() {
    return Optional.of(this.noteNlpConcept);
  }
  @Column(name = "lexical_variant", insertable = false, updatable = false)
  private String lexicalVariant;
  
  public Optional<String> getLexicalVariant() {
    if (this.lexicalVariant != null) {
      return Optional.of(this.lexicalVariant);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "offset", insertable = false, updatable = false)
  private String offset;
  
  public Optional<String> getOffset() {
    if (this.offset != null) {
      return Optional.of(this.offset);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "snippet", insertable = false, updatable = false)
  private String snippet;
  
  public Optional<String> getSnippet() {
    if (this.snippet != null) {
      return Optional.of(this.snippet);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "section_concept_id", insertable = false, updatable = false)
  private Integer sectionConceptId;
  
  public Optional<Integer> getSectionConceptId() {
    if (this.sectionConceptId != null) {
      return Optional.of(this.sectionConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "section_concept_id")
  private Concept sectionConcept;
  
  public Optional<Concept> getSectionConcept() {
    return Optional.of(this.sectionConcept);
  }
  @Column(name = "note_id", insertable = false, updatable = false)
  private Integer noteId;
  
  public Optional<Integer> getNoteId() {
    if (this.noteId != null) {
      return Optional.of(this.noteId);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "note_nlp_id", insertable = false, updatable = false)
  private Integer noteNlpId;
  
  public Optional<Integer> getNoteNlpId() {
    if (this.noteNlpId != null) {
      return Optional.of(this.noteNlpId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "NoteNlp{id=" + this.noteNlpId + "}";
  }
  
  
}
