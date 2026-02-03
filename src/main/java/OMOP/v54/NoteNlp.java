// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
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
@Table(name = "note_nlp")
public class NoteNlp {

    @Column(name = "lexical_variant", updatable = false, nullable = false)
    private String lexicalVariant;
    
    public String getLexicalVariant() {
        return this.lexicalVariant;
    }

    public void setLexicalVariant(final String newValue) {
        this.lexicalVariant = newValue;
    }

    @Column(name = "nlp_date", updatable = false, nullable = false)
    private ZonedDateTime nlpDate;
    
    public Date getNlpDate() {
        return new Date(this.nlpDate.toLocalDate());
    }

    public void setNlpDate(final Date newValue) {
        this.nlpDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "nlp_datetime", updatable = false, nullable = true)
    private ZonedDateTime nlpDatetime;
    
    public Optional<DateTime> getNlpDatetime() {
        if (this.nlpDatetime != null) {
            return Optional.of(new DateTime(this.nlpDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setNlpDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.nlpDatetime = null;
        } else {
            this.nlpDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "nlp_system", updatable = false, nullable = true)
    private String nlpSystem;
    
    public Optional<String> getNlpSystem() {
        if (this.nlpSystem != null) {
            return Optional.of(this.nlpSystem);
        } else {
            return Optional.empty();
        }
    }

    public void setNlpSystem(final String newValue) {
        this.nlpSystem = newValue;
    }

    @Column(name = "note_id", updatable = false, nullable = false)
    private Integer noteId;
    
    public Integer getNoteId() {
        return this.noteId;
    }

    public void setNoteId(final Integer newValue) {
        this.noteId = newValue;
    }

    @Column(name = "note_nlp_concept_id", updatable = false, nullable = true)
    private Integer noteNlpConceptId;
    
    public Optional<Integer> getNoteNlpConceptId() {
        if (this.noteNlpConceptId != null) {
            return Optional.of(this.noteNlpConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setNoteNlpConceptId(final Integer newValue) {
        this.noteNlpConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_nlp_concept_id", insertable = false,
                updatable = false)
    private Concept noteNlpConcept;
    
    public Optional<Concept> getNoteNlpConcept() {
        return Optional.ofNullable(this.noteNlpConcept);
    }

    public void setNoteNlpConcept(final Concept newValue) {
        if (newValue == null) {
            this.noteNlpConcept = null;
            this.noteNlpConceptId = null;
        } else {
            this.noteNlpConcept = newValue;
            this.noteNlpConceptId = newValue.getConceptId();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_nlp_id", updatable = false, nullable = false)
    private Integer noteNlpId;
    
    public Integer getNoteNlpId() {
        return this.noteNlpId;
    }

    @Column(name = "note_nlp_source_concept_id", updatable = false,
            nullable = true)
    private Integer noteNlpSourceConceptId;
    
    public Optional<Integer> getNoteNlpSourceConceptId() {
        if (this.noteNlpSourceConceptId != null) {
            return Optional.of(this.noteNlpSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setNoteNlpSourceConceptId(final Integer newValue) {
        this.noteNlpSourceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_nlp_source_concept_id", insertable = false,
                updatable = false)
    private Concept noteNlpSourceConcept;
    
    public Optional<Concept> getNoteNlpSourceConcept() {
        return Optional.ofNullable(this.noteNlpSourceConcept);
    }

    public void setNoteNlpSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.noteNlpSourceConcept = null;
            this.noteNlpSourceConceptId = null;
        } else {
            this.noteNlpSourceConcept = newValue;
            this.noteNlpSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "offset", updatable = false, nullable = true)
    private String offset;
    
    public Optional<String> getOffset() {
        if (this.offset != null) {
            return Optional.of(this.offset);
        } else {
            return Optional.empty();
        }
    }

    public void setOffset(final String newValue) {
        this.offset = newValue;
    }

    @Column(name = "section_concept_id", updatable = false, nullable = true)
    private Integer sectionConceptId;
    
    public Optional<Integer> getSectionConceptId() {
        if (this.sectionConceptId != null) {
            return Optional.of(this.sectionConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setSectionConceptId(final Integer newValue) {
        this.sectionConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "section_concept_id", insertable = false,
                updatable = false)
    private Concept sectionConcept;
    
    public Optional<Concept> getSectionConcept() {
        return Optional.ofNullable(this.sectionConcept);
    }

    public void setSectionConcept(final Concept newValue) {
        if (newValue == null) {
            this.sectionConcept = null;
            this.sectionConceptId = null;
        } else {
            this.sectionConcept = newValue;
            this.sectionConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "snippet", updatable = false, nullable = true)
    private String snippet;
    
    public Optional<String> getSnippet() {
        if (this.snippet != null) {
            return Optional.of(this.snippet);
        } else {
            return Optional.empty();
        }
    }

    public void setSnippet(final String newValue) {
        this.snippet = newValue;
    }

    @Column(name = "term_exists", updatable = false, nullable = true)
    private String termExists;
    
    public Optional<String> getTermExists() {
        if (this.termExists != null) {
            return Optional.of(this.termExists);
        } else {
            return Optional.empty();
        }
    }

    public void setTermExists(final String newValue) {
        this.termExists = newValue;
    }

    @Column(name = "term_modifiers", updatable = false, nullable = true)
    private String termModifiers;
    
    public Optional<String> getTermModifiers() {
        if (this.termModifiers != null) {
            return Optional.of(this.termModifiers);
        } else {
            return Optional.empty();
        }
    }

    public void setTermModifiers(final String newValue) {
        this.termModifiers = newValue;
    }

    @Column(name = "term_temporal", updatable = false, nullable = true)
    private String termTemporal;
    
    public Optional<String> getTermTemporal() {
        if (this.termTemporal != null) {
            return Optional.of(this.termTemporal);
        } else {
            return Optional.empty();
        }
    }

    public void setTermTemporal(final String newValue) {
        this.termTemporal = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("NoteNlp{");
        result.append("id=");
        result.append(this.noteNlpId);
        this.getNoteNlpConcept().ifPresent(concept -> {
            result.append(", concept='");
            result.append(concept.getConceptName());
            result.append("'");

        });
        result.append("}");
        return result.toString();
    }


}
