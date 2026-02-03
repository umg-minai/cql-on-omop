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
@Table(name = "vocabulary")
public class Vocabulary {

    @Column(name = "vocabulary_concept_id", updatable = false, nullable = false)
    private Integer vocabularyConceptId;
    
    public Integer getVocabularyConceptId() {
        return this.vocabularyConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setVocabularyConceptId(final Integer newValue) {
        this.vocabularyConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "vocabulary_concept_id", insertable = false,
                updatable = false)
    private Concept vocabularyConcept;
    
    public Concept getVocabularyConcept() {
        return this.vocabularyConcept;
    }

    public void setVocabularyConcept(final Concept newValue) {
        this.vocabularyConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.vocabularyConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vocabulary_id", updatable = false, nullable = false)
    private String vocabularyId;
    
    public String getVocabularyId() {
        return this.vocabularyId;
    }

    @Column(name = "vocabulary_name", updatable = false, nullable = false)
    private String vocabularyName;
    
    public String getVocabularyName() {
        return this.vocabularyName;
    }

    public void setVocabularyName(final String newValue) {
        this.vocabularyName = newValue;
    }

    @Column(name = "vocabulary_reference", updatable = false, nullable = true)
    private String vocabularyReference;
    
    public Optional<String> getVocabularyReference() {
        if (this.vocabularyReference != null) {
            return Optional.of(this.vocabularyReference);
        } else {
            return Optional.empty();
        }
    }

    public void setVocabularyReference(final String newValue) {
        this.vocabularyReference = newValue;
    }

    @Column(name = "vocabulary_version", updatable = false, nullable = true)
    private String vocabularyVersion;
    
    public Optional<String> getVocabularyVersion() {
        if (this.vocabularyVersion != null) {
            return Optional.of(this.vocabularyVersion);
        } else {
            return Optional.empty();
        }
    }

    public void setVocabularyVersion(final String newValue) {
        this.vocabularyVersion = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Vocabulary{");
        result.append("id=");
        result.append(this.vocabularyId);
        {
            result.append(", concept='");
            if (this.getVocabularyConcept() != null) {
                result.append(this.getVocabularyConcept().getConceptName());

            } else {
                result.append("«broken relation»");

            }result.append("'");

        }
        result.append("}");
        return result.toString();
    }


}
