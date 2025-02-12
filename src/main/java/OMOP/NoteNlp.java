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
@Table(name = "note_nlp", schema = "cds_cdm")
public class NoteNlp {

  @Column(name = "term_modifiers", insertable = false, updatable = false)
  private String termModifiers;
  
  public Optional<String> getTermModifiers() {
    return Optional.of(this.termModifiers);
  }
  
  @Column(name = "term_temporal", insertable = false, updatable = false)
  private String termTemporal;
  
  public Optional<String> getTermTemporal() {
    return Optional.of(this.termTemporal);
  }
  
  @Column(name = "term_exists", insertable = false, updatable = false)
  private String termExists;
  
  public Optional<String> getTermExists() {
    return Optional.of(this.termExists);
  }
  
  @Column(name = "nlp_system", insertable = false, updatable = false)
  private String nlpSystem;
  
  public Optional<String> getNlpSystem() {
    return Optional.of(this.nlpSystem);
  }
  
  @Column(name = "note_nlp_source_concept_id", insertable = false, updatable = false)
  private Integer noteNlpSourceConceptId;
  
  public Optional<Integer> getNoteNlpSourceConceptId() {
    return Optional.of(this.noteNlpSourceConceptId);
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
    return Optional.of(this.noteNlpConceptId);
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
    return Optional.of(this.lexicalVariant);
  }
  
  @Column(name = "offset", insertable = false, updatable = false)
  private String offset;
  
  public Optional<String> getOffset() {
    return Optional.of(this.offset);
  }
  
  @Column(name = "snippet", insertable = false, updatable = false)
  private String snippet;
  
  public Optional<String> getSnippet() {
    return Optional.of(this.snippet);
  }
  
  @Column(name = "section_concept_id", insertable = false, updatable = false)
  private Integer sectionConceptId;
  
  public Optional<Integer> getSectionConceptId() {
    return Optional.of(this.sectionConceptId);
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
    return Optional.of(this.noteId);
  }
  
  @Id
  @Column(name = "note_nlp_id", insertable = false, updatable = false)
  private Integer noteNlpId;
  
  public Optional<Integer> getNoteNlpId() {
    return Optional.of(this.noteNlpId);
  }
  
  @Override
  public String toString() {
    return "NoteNlp{id=" + this.noteNlpId + "}";
  }
  
  
}
