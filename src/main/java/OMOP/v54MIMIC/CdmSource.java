package OMOP.v54MIMIC;

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
@Table(name = "cdm_source", schema = "cds_cdm")
public class CdmSource {

    @Embeddable
    private static class CompoundId {

        @Column(name = "cdm_source_name", insertable = false,
                updatable = false, nullable = false)
        private String cdmSourceName;

        @Column(name = "cdm_version", insertable = false, updatable = false,
                nullable = true)
        private String cdmVersion;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.cdmSourceName, otherInstance.cdmSourceName)
                            && Objects.equals(this.cdmVersion, otherInstance.cdmVersion));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.cdmSourceName, this.cdmVersion);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("cdmSourceName=");
            result.append(this.cdmSourceName);
            result.append(", ");
            result.append("cdmVersion=");
            result.append(this.cdmVersion);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId;

    @Column(name = "cdm_etl_reference", insertable = false, updatable = false,
            nullable = true)
    private String cdmEtlReference;
    
    public Optional<String> getCdmEtlReference() {
        if (this.cdmEtlReference != null) {
            return Optional.of(this.cdmEtlReference);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "cdm_holder", insertable = false, updatable = false,
            nullable = false)
    private String cdmHolder;
    
    public String getCdmHolder() {
        return this.cdmHolder;
    }

    @Column(name = "cdm_release_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime cdmReleaseDate;
    
    public Date getCdmReleaseDate() {
        return new Date(this.cdmReleaseDate.toLocalDate());
    }

    @Column(name = "cdm_source_abbreviation", insertable = false,
            updatable = false, nullable = false)
    private String cdmSourceAbbreviation;
    
    public String getCdmSourceAbbreviation() {
        return this.cdmSourceAbbreviation;
    }

    public String getCdmSourceName() {
        return this.compoundId.cdmSourceName;
    }

    public Optional<String> getCdmVersion() {
        if (this.compoundId.cdmVersion != null) {
            return Optional.of(this.compoundId.cdmVersion);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "cdm_version_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long cdmVersionConceptId;
    
    public Long getCdmVersionConceptId() {
        return this.cdmVersionConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cdm_version_concept_id")
    private Concept cdmVersionConcept;
    
    public Concept getCdmVersionConcept() {
        return this.cdmVersionConcept;
    }

    @Column(name = "source_description", insertable = false, updatable = false,
            nullable = true)
    private String sourceDescription;
    
    public Optional<String> getSourceDescription() {
        if (this.sourceDescription != null) {
            return Optional.of(this.sourceDescription);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "source_documentation_reference", insertable = false,
            updatable = false, nullable = true)
    private String sourceDocumentationReference;
    
    public Optional<String> getSourceDocumentationReference() {
        if (this.sourceDocumentationReference != null) {
            return Optional.of(this.sourceDocumentationReference);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "source_release_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime sourceReleaseDate;
    
    public Date getSourceReleaseDate() {
        return new Date(this.sourceReleaseDate.toLocalDate());
    }

    @Column(name = "vocabulary_version", insertable = false, updatable = false,
            nullable = false)
    private String vocabularyVersion;
    
    public String getVocabularyVersion() {
        return this.vocabularyVersion;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("CdmSource{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
