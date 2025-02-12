package OMOP;

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

@Entity
@Table(name = "cdm_source", schema = "cds_cdm")
public class CdmSource {

  @Column(name = "vocabulary_version", insertable = false, updatable = false)
  private String vocabularyVersion;
  
  public Optional<String> getVocabularyVersion() {
    return Optional.of(this.vocabularyVersion);
  }
  
  @Column(name = "cdm_version_concept_id", insertable = false, updatable = false)
  private Integer cdmVersionConceptId;
  
  public Optional<Integer> getCdmVersionConceptId() {
    return Optional.of(this.cdmVersionConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cdm_version_concept_id")
  private Concept cdmVersionConcept;
  
  public Optional<Concept> getCdmVersionConcept() {
    return Optional.of(this.cdmVersionConcept);
  }
  @Column(name = "cdm_version", insertable = false, updatable = false)
  private String cdmVersion;
  
  public Optional<String> getCdmVersion() {
    return Optional.of(this.cdmVersion);
  }
  
  @Column(name = "cdm_etl_reference", insertable = false, updatable = false)
  private String cdmEtlReference;
  
  public Optional<String> getCdmEtlReference() {
    return Optional.of(this.cdmEtlReference);
  }
  
  @Column(name = "source_documentation_reference", insertable = false, updatable = false)
  private String sourceDocumentationReference;
  
  public Optional<String> getSourceDocumentationReference() {
    return Optional.of(this.sourceDocumentationReference);
  }
  
  @Column(name = "source_description", insertable = false, updatable = false)
  private String sourceDescription;
  
  public Optional<String> getSourceDescription() {
    return Optional.of(this.sourceDescription);
  }
  
  @Column(name = "cdm_holder", insertable = false, updatable = false)
  private String cdmHolder;
  
  public Optional<String> getCdmHolder() {
    return Optional.of(this.cdmHolder);
  }
  
  @Column(name = "cdm_source_abbreviation", insertable = false, updatable = false)
  private String cdmSourceAbbreviation;
  
  public Optional<String> getCdmSourceAbbreviation() {
    return Optional.of(this.cdmSourceAbbreviation);
  }
  
  @Column(name = "cdm_source_name", insertable = false, updatable = false)
  private String cdmSourceName;
  
  public Optional<String> getCdmSourceName() {
    return Optional.of(this.cdmSourceName);
  }
  
  
}
