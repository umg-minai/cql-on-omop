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
@Table(name = "note", schema = "cds_cdm")
public class Note {

  @Column(name = "note_event_field_concept_id", insertable = false, updatable = false)
  private Integer noteEventFieldConceptId;
  
  public Optional<Integer> getNoteEventFieldConceptId() {
    return Optional.of(this.noteEventFieldConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_event_field_concept_id")
  private Concept noteEventFieldConcept;
  
  public Optional<Concept> getNoteEventFieldConcept() {
    return Optional.of(this.noteEventFieldConcept);
  }
  @Column(name = "note_event_id", insertable = false, updatable = false)
  private Integer noteEventId;
  
  public Optional<Integer> getNoteEventId() {
    return Optional.of(this.noteEventId);
  }
  
  @Column(name = "note_source_value", insertable = false, updatable = false)
  private String noteSourceValue;
  
  public Optional<String> getNoteSourceValue() {
    return Optional.of(this.noteSourceValue);
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    return Optional.of(this.visitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.of(this.visitDetail);
  }
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    return Optional.of(this.visitOccurrenceId);
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.of(this.visitOccurrence);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    return Optional.of(this.providerId);
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.of(this.provider);
  }
  @Column(name = "language_concept_id", insertable = false, updatable = false)
  private Integer languageConceptId;
  
  public Optional<Integer> getLanguageConceptId() {
    return Optional.of(this.languageConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "language_concept_id")
  private Concept languageConcept;
  
  public Optional<Concept> getLanguageConcept() {
    return Optional.of(this.languageConcept);
  }
  @Column(name = "encoding_concept_id", insertable = false, updatable = false)
  private Integer encodingConceptId;
  
  public Optional<Integer> getEncodingConceptId() {
    return Optional.of(this.encodingConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "encoding_concept_id")
  private Concept encodingConcept;
  
  public Optional<Concept> getEncodingConcept() {
    return Optional.of(this.encodingConcept);
  }
  @Column(name = "note_text", insertable = false, updatable = false)
  private String noteText;
  
  public Optional<String> getNoteText() {
    return Optional.of(this.noteText);
  }
  
  @Column(name = "note_title", insertable = false, updatable = false)
  private String noteTitle;
  
  public Optional<String> getNoteTitle() {
    return Optional.of(this.noteTitle);
  }
  
  @Column(name = "note_class_concept_id", insertable = false, updatable = false)
  private Integer noteClassConceptId;
  
  public Optional<Integer> getNoteClassConceptId() {
    return Optional.of(this.noteClassConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_class_concept_id")
  private Concept noteClassConcept;
  
  public Optional<Concept> getNoteClassConcept() {
    return Optional.of(this.noteClassConcept);
  }
  @Column(name = "note_type_concept_id", insertable = false, updatable = false)
  private Integer noteTypeConceptId;
  
  public Optional<Integer> getNoteTypeConceptId() {
    return Optional.of(this.noteTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_type_concept_id")
  private Concept noteTypeConcept;
  
  public Optional<Concept> getNoteTypeConcept() {
    return Optional.of(this.noteTypeConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    return Optional.of(this.personId);
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "note_id", insertable = false, updatable = false)
  private Integer noteId;
  
  public Optional<Integer> getNoteId() {
    return Optional.of(this.noteId);
  }
  
  @Override
  public String toString() {
    return "Note{id=" + this.noteId + "}";
  }
  
  
}
