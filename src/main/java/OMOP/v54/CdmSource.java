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
@Table(name = "cdm_source")
public class CdmSource {

    @Embeddable
    private static class CompoundId {

        @Column(name = "cdm_source_name", updatable = false, nullable = false)
        private String cdmSourceName;

        @Column(name = "cdm_version", updatable = false, nullable = true)
        private String cdmVersion;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return ((other.getClass() == this.getClass())
                            && (Objects.equals(this.cdmSourceName, otherInstance.cdmSourceName))
                            && (Objects.equals(this.cdmVersion, otherInstance.cdmVersion)));
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
    private CompoundId compoundId = new CompoundId();

    @Column(name = "cdm_etl_reference", updatable = false, nullable = true)
    private String cdmEtlReference;
    
    public Optional<String> getCdmEtlReference() {
        if (this.cdmEtlReference != null) {
            return Optional.of(this.cdmEtlReference);
        } else {
            return Optional.empty();
        }
    }

    public void setCdmEtlReference(final String newValue) {
        this.cdmEtlReference = newValue;
    }

    @Column(name = "cdm_holder", updatable = false, nullable = false)
    private String cdmHolder;
    
    public String getCdmHolder() {
        return this.cdmHolder;
    }

    public void setCdmHolder(final String newValue) {
        this.cdmHolder = newValue;
    }

    @Column(name = "cdm_release_date", updatable = false, nullable = false)
    private ZonedDateTime cdmReleaseDate;
    
    public Date getCdmReleaseDate() {
        return new Date(this.cdmReleaseDate.toLocalDate());
    }

    public void setCdmReleaseDate(final Date newValue) {
        this.cdmReleaseDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "cdm_source_abbreviation", updatable = false,
            nullable = false)
    private String cdmSourceAbbreviation;
    
    public String getCdmSourceAbbreviation() {
        return this.cdmSourceAbbreviation;
    }

    public void setCdmSourceAbbreviation(final String newValue) {
        this.cdmSourceAbbreviation = newValue;
    }

    public String getCdmSourceName() {
        return this.compoundId.cdmSourceName;
    }

    public void setCdmSourceName(final String newValue) {
        this.compoundId.cdmSourceName = newValue;
    }

    public Optional<String> getCdmVersion() {
        if (this.compoundId.cdmVersion != null) {
            return Optional.of(this.compoundId.cdmVersion);
        } else {
            return Optional.empty();
        }
    }

    public void setCdmVersion(final String newValue) {
        this.compoundId.cdmVersion = newValue;
    }

    @Column(name = "cdm_version_concept_id", updatable = false,
            nullable = false)
    private Integer cdmVersionConceptId;
    
    public Integer getCdmVersionConceptId() {
        return this.cdmVersionConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cdm_version_concept_id", insertable = false,
                updatable = false)
    private Concept cdmVersionConcept;
    
    public Concept getCdmVersionConcept() {
        return this.cdmVersionConcept;
    }

    public void setCdmVersionConcept(final Concept newValue) {
        this.cdmVersionConcept = newValue;
        this.cdmVersionConceptId = newValue.getConceptId();
    }

    @Column(name = "source_description", updatable = false, nullable = true)
    private String sourceDescription;
    
    public Optional<String> getSourceDescription() {
        if (this.sourceDescription != null) {
            return Optional.of(this.sourceDescription);
        } else {
            return Optional.empty();
        }
    }

    public void setSourceDescription(final String newValue) {
        this.sourceDescription = newValue;
    }

    @Column(name = "source_documentation_reference", updatable = false,
            nullable = true)
    private String sourceDocumentationReference;
    
    public Optional<String> getSourceDocumentationReference() {
        if (this.sourceDocumentationReference != null) {
            return Optional.of(this.sourceDocumentationReference);
        } else {
            return Optional.empty();
        }
    }

    public void setSourceDocumentationReference(final String newValue) {
        this.sourceDocumentationReference = newValue;
    }

    @Column(name = "source_release_date", updatable = false, nullable = false)
    private ZonedDateTime sourceReleaseDate;
    
    public Date getSourceReleaseDate() {
        return new Date(this.sourceReleaseDate.toLocalDate());
    }

    public void setSourceReleaseDate(final Date newValue) {
        this.sourceReleaseDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "vocabulary_version", updatable = false, nullable = false)
    private String vocabularyVersion;
    
    public String getVocabularyVersion() {
        return this.vocabularyVersion;
    }

    public void setVocabularyVersion(final String newValue) {
        this.vocabularyVersion = newValue;
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
