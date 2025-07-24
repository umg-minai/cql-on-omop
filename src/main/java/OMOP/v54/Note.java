package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "note", schema = "cds_cdm")
public class Note {

    @Column(name = "encoding_concept_id", updatable = false, nullable = false)
    private Integer encodingConceptId;
    
    public Integer getEncodingConceptId() {
        return this.encodingConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "encoding_concept_id", insertable = false,
                updatable = false)
    private Concept encodingConcept;
    
    public Concept getEncodingConcept() {
        return this.encodingConcept;
    }

    public void setEncodingConcept(final Concept newValue) {
        this.encodingConcept = newValue;
        this.encodingConceptId = newValue.getConceptId();
    }

    @Column(name = "language_concept_id", updatable = false, nullable = false)
    private Integer languageConceptId;
    
    public Integer getLanguageConceptId() {
        return this.languageConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_concept_id", insertable = false,
                updatable = false)
    private Concept languageConcept;
    
    public Concept getLanguageConcept() {
        return this.languageConcept;
    }

    public void setLanguageConcept(final Concept newValue) {
        this.languageConcept = newValue;
        this.languageConceptId = newValue.getConceptId();
    }

    @Column(name = "note_class_concept_id", updatable = false, nullable = false)
    private Integer noteClassConceptId;
    
    public Integer getNoteClassConceptId() {
        return this.noteClassConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_class_concept_id", insertable = false,
                updatable = false)
    private Concept noteClassConcept;
    
    public Concept getNoteClassConcept() {
        return this.noteClassConcept;
    }

    public void setNoteClassConcept(final Concept newValue) {
        this.noteClassConcept = newValue;
        this.noteClassConceptId = newValue.getConceptId();
    }

    @Column(name = "note_date", updatable = false, nullable = false)
    private ZonedDateTime noteDate;
    
    public Date getNoteDate() {
        return new Date(this.noteDate.toLocalDate());
    }

    public void setNoteDate(final Date newValue) {
        this.noteDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "note_datetime", updatable = false, nullable = true)
    private ZonedDateTime noteDatetime;
    
    public Optional<DateTime> getNoteDatetime() {
        if (this.noteDatetime != null) {
            return Optional.of(new DateTime(this.noteDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setNoteDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.noteDatetime = null;
        } else {
            this.noteDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "note_event_field_concept_id", updatable = false,
            nullable = true)
    private Integer noteEventFieldConceptId;
    
    public Optional<Integer> getNoteEventFieldConceptId() {
        if (this.noteEventFieldConceptId != null) {
            return Optional.of(this.noteEventFieldConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_event_field_concept_id", insertable = false,
                updatable = false)
    private Concept noteEventFieldConcept;
    
    public Optional<Concept> getNoteEventFieldConcept() {
        return Optional.ofNullable(this.noteEventFieldConcept);
    }

    public void setNoteEventFieldConcept(final Concept newValue) {
        if (newValue == null) {
            this.noteEventFieldConcept = null;
            this.noteEventFieldConceptId = null;
        } else {
            this.noteEventFieldConcept = newValue;
            this.noteEventFieldConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "note_event_id", updatable = false, nullable = true)
    private Integer noteEventId;
    
    public Optional<Integer> getNoteEventId() {
        if (this.noteEventId != null) {
            return Optional.of(this.noteEventId);
        } else {
            return Optional.empty();
        }
    }

    public void setNoteEventId(final Integer newValue) {
        this.noteEventId = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", updatable = false, nullable = false)
    private Integer noteId;
    
    public Integer getNoteId() {
        return this.noteId;
    }

    @Column(name = "note_source_value", updatable = false, nullable = true)
    private String noteSourceValue;
    
    public Optional<String> getNoteSourceValue() {
        if (this.noteSourceValue != null) {
            return Optional.of(this.noteSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setNoteSourceValue(final String newValue) {
        this.noteSourceValue = newValue;
    }

    @Column(name = "note_text", updatable = false, nullable = false)
    private String noteText;
    
    public String getNoteText() {
        return this.noteText;
    }

    public void setNoteText(final String newValue) {
        this.noteText = newValue;
    }

    @Column(name = "note_title", updatable = false, nullable = true)
    private String noteTitle;
    
    public Optional<String> getNoteTitle() {
        if (this.noteTitle != null) {
            return Optional.of(this.noteTitle);
        } else {
            return Optional.empty();
        }
    }

    public void setNoteTitle(final String newValue) {
        this.noteTitle = newValue;
    }

    @Column(name = "note_type_concept_id", updatable = false, nullable = false)
    private Integer noteTypeConceptId;
    
    public Integer getNoteTypeConceptId() {
        return this.noteTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_type_concept_id", insertable = false,
                updatable = false)
    private Concept noteTypeConcept;
    
    public Concept getNoteTypeConcept() {
        return this.noteTypeConcept;
    }

    public void setNoteTypeConcept(final Concept newValue) {
        this.noteTypeConcept = newValue;
        this.noteTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        this.personId = newValue.getPersonId();
    }

    @Column(name = "provider_id", updatable = false, nullable = true)
    private Integer providerId;
    
    public Optional<Integer> getProviderId() {
        if (this.providerId != null) {
            return Optional.of(this.providerId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", insertable = false, updatable = false)
    private Provider provider;
    
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(this.provider);
    }

    public void setProvider(final Provider newValue) {
        if (newValue == null) {
            this.provider = null;
            this.providerId = null;
        } else {
            this.provider = newValue;
            this.providerId = newValue.getProviderId();
        }
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Integer visitDetailId;
    
    public Optional<Integer> getVisitDetailId() {
        if (this.visitDetailId != null) {
            return Optional.of(this.visitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_id", insertable = false, updatable = false)
    private VisitDetail visitDetail;
    
    public Optional<VisitDetail> getVisitDetail() {
        return Optional.ofNullable(this.visitDetail);
    }

    public void setVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.visitDetail = null;
            this.visitDetailId = null;
        } else {
            this.visitDetail = newValue;
            this.visitDetailId = newValue.getVisitDetailId();
        }
    }

    @Column(name = "visit_occurrence_id", updatable = false, nullable = true)
    private Integer visitOccurrenceId;
    
    public Optional<Integer> getVisitOccurrenceId() {
        if (this.visitOccurrenceId != null) {
            return Optional.of(this.visitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id", insertable = false,
                updatable = false)
    private VisitOccurrence visitOccurrence;
    
    public Optional<VisitOccurrence> getVisitOccurrence() {
        return Optional.ofNullable(this.visitOccurrence);
    }

    public void setVisitOccurrence(final VisitOccurrence newValue) {
        if (newValue == null) {
            this.visitOccurrence = null;
            this.visitOccurrenceId = null;
        } else {
            this.visitOccurrence = newValue;
            this.visitOccurrenceId = newValue.getVisitOccurrenceId();
        }
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Note{");
        result.append("id=");
        result.append(this.noteId);
        result.append("}");
        return result.toString();
    }


}
