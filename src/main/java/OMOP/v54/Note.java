package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "note", schema = "cds_cdm")
public class Note {

    @Column(name = "encoding_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer encodingConceptId;
    
    public Integer getEncodingConceptId() {
        return this.encodingConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "encoding_concept_id")
    private Concept encodingConcept;
    
    public Concept getEncodingConcept() {
        return this.encodingConcept;
    }

    @Column(name = "language_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer languageConceptId;
    
    public Integer getLanguageConceptId() {
        return this.languageConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_concept_id")
    private Concept languageConcept;
    
    public Concept getLanguageConcept() {
        return this.languageConcept;
    }

    @Column(name = "note_class_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer noteClassConceptId;
    
    public Integer getNoteClassConceptId() {
        return this.noteClassConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_class_concept_id")
    private Concept noteClassConcept;
    
    public Concept getNoteClassConcept() {
        return this.noteClassConcept;
    }

    @Column(name = "note_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime noteDate;
    
    public Date getNoteDate() {
        return new Date(this.noteDate.toLocalDate());
    }

    @Column(name = "note_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime noteDatetime;
    
    public Optional<DateTime> getNoteDatetime() {
        if (this.noteDatetime != null) {
            return Optional.of(new DateTime(this.noteDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "note_event_field_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "note_event_id", insertable = false, updatable = false,
            nullable = true)
    private Integer noteEventId;
    
    public Optional<Integer> getNoteEventId() {
        if (this.noteEventId != null) {
            return Optional.of(this.noteEventId);
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "note_id", insertable = false, updatable = false,
            nullable = false)
    private Integer noteId;
    
    public Integer getNoteId() {
        return this.noteId;
    }

    @Column(name = "note_source_value", insertable = false, updatable = false,
            nullable = true)
    private String noteSourceValue;
    
    public Optional<String> getNoteSourceValue() {
        if (this.noteSourceValue != null) {
            return Optional.of(this.noteSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "note_text", insertable = false, updatable = false,
            nullable = false)
    private String noteText;
    
    public String getNoteText() {
        return this.noteText;
    }

    @Column(name = "note_title", insertable = false, updatable = false,
            nullable = true)
    private String noteTitle;
    
    public Optional<String> getNoteTitle() {
        if (this.noteTitle != null) {
            return Optional.of(this.noteTitle);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "note_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer noteTypeConceptId;
    
    public Integer getNoteTypeConceptId() {
        return this.noteTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_type_concept_id")
    private Concept noteTypeConcept;
    
    public Concept getNoteTypeConcept() {
        return this.noteTypeConcept;
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    @Column(name = "provider_id", insertable = false, updatable = false,
            nullable = true)
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

    @Column(name = "visit_detail_id", insertable = false, updatable = false,
            nullable = true)
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

    @Column(name = "visit_occurrence_id", insertable = false,
            updatable = false, nullable = true)
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
