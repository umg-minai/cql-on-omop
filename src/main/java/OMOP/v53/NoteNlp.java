package OMOP.v53;

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
@Table(name = "note_nlp", schema = "cds_cdm")
public class NoteNlp {

    @Column(name = "lexical_variant", insertable = false, updatable = false,
            nullable = false)
    private String lexicalVariant;
    
    public String getLexicalVariant() {
        return this.lexicalVariant;
    }

    @Column(name = "nlp_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime nlpDate;
    
    public Date getNlpDate() {
        return new Date(this.nlpDate.toLocalDate());
    }

    @Column(name = "nlp_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime nlpDatetime;
    
    public Optional<DateTime> getNlpDatetime() {
        if (this.nlpDatetime != null) {
            return Optional.of(new DateTime(this.nlpDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "nlp_system", insertable = false, updatable = false,
            nullable = true)
    private String nlpSystem;
    
    public Optional<String> getNlpSystem() {
        if (this.nlpSystem != null) {
            return Optional.of(this.nlpSystem);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "note_id", insertable = false, updatable = false,
            nullable = false)
    private Integer noteId;
    
    public Integer getNoteId() {
        return this.noteId;
    }

    @Column(name = "note_nlp_concept_id", insertable = false,
            updatable = false, nullable = true)
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
        return Optional.ofNullable(this.noteNlpConcept);
    }

    @Id
    @Column(name = "note_nlp_id", insertable = false, updatable = false,
            nullable = false)
    private Integer noteNlpId;
    
    public Integer getNoteNlpId() {
        return this.noteNlpId;
    }

    @Column(name = "note_nlp_source_concept_id", insertable = false,
            updatable = false, nullable = true)
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
        return Optional.ofNullable(this.noteNlpSourceConcept);
    }

    @Column(name = "offset", insertable = false, updatable = false,
            nullable = true)
    private String offset;
    
    public Optional<String> getOffset() {
        if (this.offset != null) {
            return Optional.of(this.offset);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "section_concept_id", insertable = false, updatable = false,
            nullable = true)
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
        return Optional.ofNullable(this.sectionConcept);
    }

    @Column(name = "snippet", insertable = false, updatable = false,
            nullable = true)
    private String snippet;
    
    public Optional<String> getSnippet() {
        if (this.snippet != null) {
            return Optional.of(this.snippet);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "term_exists", insertable = false, updatable = false,
            nullable = true)
    private String termExists;
    
    public Optional<String> getTermExists() {
        if (this.termExists != null) {
            return Optional.of(this.termExists);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "term_modifiers", insertable = false, updatable = false,
            nullable = true)
    private String termModifiers;
    
    public Optional<String> getTermModifiers() {
        if (this.termModifiers != null) {
            return Optional.of(this.termModifiers);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "term_temporal", insertable = false, updatable = false,
            nullable = true)
    private String termTemporal;
    
    public Optional<String> getTermTemporal() {
        if (this.termTemporal != null) {
            return Optional.of(this.termTemporal);
        } else {
            return Optional.empty();
        }
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
