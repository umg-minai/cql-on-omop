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
            nullable = true)
    private String cdmHolder;
    
    public Optional<String> getCdmHolder() {
        if (this.cdmHolder != null) {
            return Optional.of(this.cdmHolder);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "cdm_release_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime cdmReleaseDate;
    
    public Optional<Date> getCdmReleaseDate() {
        if (this.cdmReleaseDate != null) {
            return Optional.of(new Date(this.cdmReleaseDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "cdm_source_abbreviation", insertable = false,
            updatable = false, nullable = true)
    private String cdmSourceAbbreviation;
    
    public Optional<String> getCdmSourceAbbreviation() {
        if (this.cdmSourceAbbreviation != null) {
            return Optional.of(this.cdmSourceAbbreviation);
        } else {
            return Optional.empty();
        }
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
            updatable = false, nullable = true)
    private ZonedDateTime sourceReleaseDate;
    
    public Optional<Date> getSourceReleaseDate() {
        if (this.sourceReleaseDate != null) {
            return Optional.of(new Date(this.sourceReleaseDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "vocabulary_version", insertable = false, updatable = false,
            nullable = true)
    private String vocabularyVersion;
    
    public Optional<String> getVocabularyVersion() {
        if (this.vocabularyVersion != null) {
            return Optional.of(this.vocabularyVersion);
        } else {
            return Optional.empty();
        }
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
