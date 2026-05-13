// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
package OMOP.v54MIMIC;

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
@Table(name = "concept_synonym")
public class ConceptSynonym {

    @Embeddable
    private static class CompoundId {

        @Column(name = "concept_id", updatable = false, nullable = false)
        private Integer conceptId;

        @Column(name = "language_concept_id", updatable = false,
                nullable = false)
        private Integer languageConceptId;

        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof CompoundId other)) {
                return false;
            } else {
                return ((Objects.equals(this.conceptId, other.conceptId))
                        && (Objects.equals(this.languageConceptId, other.languageConceptId)));
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.conceptId, this.languageConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("conceptId=");
            result.append(this.conceptId);
            result.append(", ");
            result.append("languageConceptId=");
            result.append(this.languageConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getConceptId() {
        return this.compoundId.conceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setConceptId(final Integer newValue) {
        this.compoundId.conceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_id", insertable = false, updatable = false)
    @MapsId("conceptId")
    private Concept concept;
    
    public Concept getConcept() {
        return this.concept;
    }

    public void setConcept(final Concept newValue) {
        this.concept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.conceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Column(name = "concept_synonym_name", updatable = false, nullable = false)
    private String conceptSynonymName;
    
    public String getConceptSynonymName() {
        return this.conceptSynonymName;
    }

    public void setConceptSynonymName(final String newValue) {
        this.conceptSynonymName = newValue;
    }

    public Integer getLanguageConceptId() {
        return this.compoundId.languageConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setLanguageConceptId(final Integer newValue) {
        this.compoundId.languageConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_concept_id", insertable = false,
                updatable = false)
    @MapsId("languageConceptId")
    private Concept languageConcept;
    
    public Concept getLanguageConcept() {
        return this.languageConcept;
    }

    public void setLanguageConcept(final Concept newValue) {
        this.languageConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.languageConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ConceptSynonym other)) {
            return false;
        } else {
            return Objects.equals(this.compoundId, other.compoundId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.compoundId);
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptSynonym{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
