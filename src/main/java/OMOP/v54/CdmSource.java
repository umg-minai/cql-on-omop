package OMOP.v54;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "cdm_source", schema = "cds_cdm")
public class CdmSource {

  @Column(name = "vocabulary_version", insertable = false, updatable = false)
  private String vocabularyVersion;
  
  public Optional<String> getVocabularyVersion() {
    if (this.vocabularyVersion != null) {
      return Optional.of(this.vocabularyVersion);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_version_concept_id", insertable = false, updatable = false)
  private Integer cdmVersionConceptId;
  
  public Optional<Integer> getCdmVersionConceptId() {
    if (this.cdmVersionConceptId != null) {
      return Optional.of(this.cdmVersionConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cdm_version_concept_id")
  private Concept cdmVersionConcept;
  
  public Optional<Concept> getCdmVersionConcept() {
    return Optional.ofNullable(this.cdmVersionConcept);
  }
  @Column(name = "cdm_version", insertable = false, updatable = false)
  private String cdmVersion;
  
  public Optional<String> getCdmVersion() {
    if (this.cdmVersion != null) {
      return Optional.of(this.cdmVersion);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_release_date", insertable = false, updatable = false)
  private ZonedDateTime cdmReleaseDate;
  
  public Optional<Date> getCdmReleaseDate() {
    if (this.cdmReleaseDate != null) {
      return Optional.of(new Date(this.cdmReleaseDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "source_release_date", insertable = false, updatable = false)
  private ZonedDateTime sourceReleaseDate;
  
  public Optional<Date> getSourceReleaseDate() {
    if (this.sourceReleaseDate != null) {
      return Optional.of(new Date(this.sourceReleaseDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_etl_reference", insertable = false, updatable = false)
  private String cdmEtlReference;
  
  public Optional<String> getCdmEtlReference() {
    if (this.cdmEtlReference != null) {
      return Optional.of(this.cdmEtlReference);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "source_documentation_reference", insertable = false, updatable = false)
  private String sourceDocumentationReference;
  
  public Optional<String> getSourceDocumentationReference() {
    if (this.sourceDocumentationReference != null) {
      return Optional.of(this.sourceDocumentationReference);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "source_description", insertable = false, updatable = false)
  private String sourceDescription;
  
  public Optional<String> getSourceDescription() {
    if (this.sourceDescription != null) {
      return Optional.of(this.sourceDescription);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_holder", insertable = false, updatable = false)
  private String cdmHolder;
  
  public Optional<String> getCdmHolder() {
    if (this.cdmHolder != null) {
      return Optional.of(this.cdmHolder);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_source_abbreviation", insertable = false, updatable = false)
  private String cdmSourceAbbreviation;
  
  public Optional<String> getCdmSourceAbbreviation() {
    if (this.cdmSourceAbbreviation != null) {
      return Optional.of(this.cdmSourceAbbreviation);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cdm_source_name", insertable = false, updatable = false)
  private String cdmSourceName;
  
  public Optional<String> getCdmSourceName() {
    if (this.cdmSourceName != null) {
      return Optional.of(this.cdmSourceName);
    } else {
      return Optional.empty();
    }
  }
  
  
}
