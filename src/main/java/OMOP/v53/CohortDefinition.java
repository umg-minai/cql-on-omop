// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
package OMOP.v53;

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
@Table(name = "cohort_definition")
public class CohortDefinition {

    @Embeddable
    private static class CompoundId {

        @Column(name = "definition_type_concept_id", updatable = false,
                nullable = false)
        private Integer definitionTypeConceptId;

        @Column(name = "subject_concept_id", updatable = false,
                nullable = false)
        private Integer subjectConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return ((other.getClass() == this.getClass())
                            && (Objects.equals(this.definitionTypeConceptId, otherInstance.definitionTypeConceptId))
                            && (Objects.equals(this.subjectConceptId, otherInstance.subjectConceptId)));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.definitionTypeConceptId, this.subjectConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("definitionTypeConceptId=");
            result.append(this.definitionTypeConceptId);
            result.append(", ");
            result.append("subjectConceptId=");
            result.append(this.subjectConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    @Column(name = "cohort_definition_description", updatable = false,
            nullable = true)
    private String cohortDefinitionDescription;
    
    public Optional<String> getCohortDefinitionDescription() {
        if (this.cohortDefinitionDescription != null) {
            return Optional.of(this.cohortDefinitionDescription);
        } else {
            return Optional.empty();
        }
    }

    public void setCohortDefinitionDescription(final String newValue) {
        this.cohortDefinitionDescription = newValue;
    }

    @Column(name = "cohort_definition_id", updatable = false, nullable = false)
    private Integer cohortDefinitionId;
    
    public Integer getCohortDefinitionId() {
        return this.cohortDefinitionId;
    }

    public void setCohortDefinitionId(final Integer newValue) {
        this.cohortDefinitionId = newValue;
    }

    @Column(name = "cohort_definition_name", updatable = false,
            nullable = false)
    private String cohortDefinitionName;
    
    public String getCohortDefinitionName() {
        return this.cohortDefinitionName;
    }

    public void setCohortDefinitionName(final String newValue) {
        this.cohortDefinitionName = newValue;
    }

    @Column(name = "cohort_definition_syntax", updatable = false,
            nullable = true)
    private String cohortDefinitionSyntax;
    
    public Optional<String> getCohortDefinitionSyntax() {
        if (this.cohortDefinitionSyntax != null) {
            return Optional.of(this.cohortDefinitionSyntax);
        } else {
            return Optional.empty();
        }
    }

    public void setCohortDefinitionSyntax(final String newValue) {
        this.cohortDefinitionSyntax = newValue;
    }

    @Column(name = "cohort_initiation_date", updatable = false, nullable = true)
    private ZonedDateTime cohortInitiationDate;
    
    public Optional<Date> getCohortInitiationDate() {
        if (this.cohortInitiationDate != null) {
            return Optional.of(new Date(this.cohortInitiationDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setCohortInitiationDate(final Date newValue) {
        if (newValue == null) {
            this.cohortInitiationDate = null;
        } else {
            this.cohortInitiationDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    public Integer getDefinitionTypeConceptId() {
        return this.compoundId.definitionTypeConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDefinitionTypeConceptId(final Integer newValue) {
        this.compoundId.definitionTypeConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "definition_type_concept_id", insertable = false,
                updatable = false)
    @MapsId("definitionTypeConceptId")
    private Concept definitionTypeConcept;
    
    public Concept getDefinitionTypeConcept() {
        return this.definitionTypeConcept;
    }

    public void setDefinitionTypeConcept(final Concept newValue) {
        this.definitionTypeConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.definitionTypeConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    public Integer getSubjectConceptId() {
        return this.compoundId.subjectConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setSubjectConceptId(final Integer newValue) {
        this.compoundId.subjectConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_concept_id", insertable = false,
                updatable = false)
    @MapsId("subjectConceptId")
    private Concept subjectConcept;
    
    public Concept getSubjectConcept() {
        return this.subjectConcept;
    }

    public void setSubjectConcept(final Concept newValue) {
        this.subjectConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.compoundId.subjectConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("CohortDefinition{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
