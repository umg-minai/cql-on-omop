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
@Table(name = "source_to_concept_map", schema = "cds_cdm")
public class SourceToConceptMap {

    @Embeddable
    private static class CompoundId {

        @Column(name = "source_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Integer sourceConceptId;

        @Column(name = "target_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Integer targetConceptId;

        @Column(name = "target_vocabulary_id", insertable = false,
                updatable = false, nullable = false)
        private String targetVocabularyId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.sourceConceptId, otherInstance.sourceConceptId)
                            && Objects.equals(this.targetConceptId, otherInstance.targetConceptId)
                            && Objects.equals(this.targetVocabularyId, otherInstance.targetVocabularyId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.sourceConceptId, this.targetConceptId, this.targetVocabularyId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("sourceConceptId=");
            result.append(this.sourceConceptId);
            result.append(", ");
            result.append("targetConceptId=");
            result.append(this.targetConceptId);
            result.append(", ");
            result.append("targetVocabularyId=");
            result.append(this.targetVocabularyId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId;

    @Column(name = "invalid_reason", insertable = false, updatable = false,
            nullable = true)
    private String invalidReason;
    
    public Optional<String> getInvalidReason() {
        if (this.invalidReason != null) {
            return Optional.of(this.invalidReason);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "source_code", insertable = false, updatable = false,
            nullable = false)
    private String sourceCode;
    
    public String getSourceCode() {
        return this.sourceCode;
    }

    @Column(name = "source_code_description", insertable = false,
            updatable = false, nullable = true)
    private String sourceCodeDescription;
    
    public Optional<String> getSourceCodeDescription() {
        if (this.sourceCodeDescription != null) {
            return Optional.of(this.sourceCodeDescription);
        } else {
            return Optional.empty();
        }
    }

    public Integer getSourceConceptId() {
        return this.compoundId.sourceConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "source_concept_id")
    @MapsId("sourceConceptId")
    private Concept sourceConcept;
    
    public Concept getSourceConcept() {
        return this.sourceConcept;
    }

    @Column(name = "source_vocabulary_id", insertable = false,
            updatable = false, nullable = false)
    private String sourceVocabularyId;
    
    public String getSourceVocabularyId() {
        return this.sourceVocabularyId;
    }

    public Integer getTargetConceptId() {
        return this.compoundId.targetConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_concept_id")
    @MapsId("targetConceptId")
    private Concept targetConcept;
    
    public Concept getTargetConcept() {
        return this.targetConcept;
    }

    public String getTargetVocabularyId() {
        return this.compoundId.targetVocabularyId;
    }

    @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "target_vocabulary_id")
    @MapsId("targetVocabularyId")
    private Vocabulary targetVocabulary;
    
    public Vocabulary getTargetVocabulary() {
        return this.targetVocabulary;
    }

    @Column(name = "valid_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime validEndDate;
    
    public Date getValidEndDate() {
        return new Date(this.validEndDate.toLocalDate());
    }

    @Column(name = "valid_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime validStartDate;
    
    public Date getValidStartDate() {
        return new Date(this.validStartDate.toLocalDate());
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("SourceToConceptMap{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
