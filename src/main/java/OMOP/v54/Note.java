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
@Table(name = "note", schema = "cds_cdm")
public class Note {

  @Column(name = "note_event_field_concept_id", insertable = false, updatable = false)
  private Integer noteEventFieldConceptId;
  
  public Optional<Integer> getNoteEventFieldConceptId() {
    if (this.noteEventFieldConceptId != null) {
      return Optional.of(this.noteEventFieldConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_event_field_concept_id")
  private Concept noteEventFieldConcept;
  
  public Optional<Concept> getNoteEventFieldConcept() {
    return Optional.ofNullable(this.noteEventFieldConcept);
  }
  @Column(name = "note_event_id", insertable = false, updatable = false)
  private Integer noteEventId;
  
  public Optional<Integer> getNoteEventId() {
    if (this.noteEventId != null) {
      return Optional.of(this.noteEventId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "note_source_value", insertable = false, updatable = false)
  private String noteSourceValue;
  
  public Optional<String> getNoteSourceValue() {
    if (this.noteSourceValue != null) {
      return Optional.of(this.noteSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    if (this.visitDetailId != null) {
      return Optional.of(this.visitDetailId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.ofNullable(this.visitDetail);
  }
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    if (this.visitOccurrenceId != null) {
      return Optional.of(this.visitOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.ofNullable(this.visitOccurrence);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    if (this.providerId != null) {
      return Optional.of(this.providerId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.ofNullable(this.provider);
  }
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
    return Optional.ofNullable(this.languageConcept);
  }
  @Column(name = "encoding_concept_id", insertable = false, updatable = false)
  private Integer encodingConceptId;
  
  public Optional<Integer> getEncodingConceptId() {
    if (this.encodingConceptId != null) {
      return Optional.of(this.encodingConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "encoding_concept_id")
  private Concept encodingConcept;
  
  public Optional<Concept> getEncodingConcept() {
    return Optional.ofNullable(this.encodingConcept);
  }
  @Column(name = "note_text", insertable = false, updatable = false)
  private String noteText;
  
  public Optional<String> getNoteText() {
    if (this.noteText != null) {
      return Optional.of(this.noteText);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "note_title", insertable = false, updatable = false)
  private String noteTitle;
  
  public Optional<String> getNoteTitle() {
    if (this.noteTitle != null) {
      return Optional.of(this.noteTitle);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "note_class_concept_id", insertable = false, updatable = false)
  private Integer noteClassConceptId;
  
  public Optional<Integer> getNoteClassConceptId() {
    if (this.noteClassConceptId != null) {
      return Optional.of(this.noteClassConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_class_concept_id")
  private Concept noteClassConcept;
  
  public Optional<Concept> getNoteClassConcept() {
    return Optional.ofNullable(this.noteClassConcept);
  }
  @Column(name = "note_type_concept_id", insertable = false, updatable = false)
  private Integer noteTypeConceptId;
  
  public Optional<Integer> getNoteTypeConceptId() {
    if (this.noteTypeConceptId != null) {
      return Optional.of(this.noteTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "note_type_concept_id")
  private Concept noteTypeConcept;
  
  public Optional<Concept> getNoteTypeConcept() {
    return Optional.ofNullable(this.noteTypeConcept);
  }
  @Column(name = "note_datetime", insertable = false, updatable = false)
  private ZonedDateTime noteDatetime;
  
  public Optional<DateTime> getNoteDatetime() {
    if (this.noteDatetime != null) {
      return Optional.of(new DateTime(this.noteDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "note_date", insertable = false, updatable = false)
  private ZonedDateTime noteDate;
  
  public Optional<Date> getNoteDate() {
    if (this.noteDate != null) {
      return Optional.of(new Date(this.noteDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.ofNullable(this.person);
  }
  @Id
  @Column(name = "note_id", insertable = false, updatable = false)
  private Integer noteId;
  
  public Optional<Integer> getNoteId() {
    if (this.noteId != null) {
      return Optional.of(this.noteId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Note{id=").append(this.noteId);
    result.append("}");
    return result.toString();
}
  
}
