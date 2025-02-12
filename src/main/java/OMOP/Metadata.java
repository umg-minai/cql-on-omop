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
@Table(name = "metadata", schema = "cds_cdm")
public class Metadata {

  @Column(name = "value_as_number", insertable = false, updatable = false)
  private Float valueAsNumber;
  
  public Optional<Float> getValueAsNumber() {
    return Optional.of(this.valueAsNumber);
  }
  
  @Column(name = "value_as_concept_id", insertable = false, updatable = false)
  private Integer valueAsConceptId;
  
  public Optional<Integer> getValueAsConceptId() {
    return Optional.of(this.valueAsConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "value_as_concept_id")
  private Concept valueAsConcept;
  
  public Optional<Concept> getValueAsConcept() {
    return Optional.of(this.valueAsConcept);
  }
  @Column(name = "value_as_string", insertable = false, updatable = false)
  private String valueAsString;
  
  public Optional<String> getValueAsString() {
    return Optional.of(this.valueAsString);
  }
  
  @Column(name = "name", insertable = false, updatable = false)
  private String name;
  
  public Optional<String> getName() {
    return Optional.of(this.name);
  }
  
  @Column(name = "metadata_type_concept_id", insertable = false, updatable = false)
  private Integer metadataTypeConceptId;
  
  public Optional<Integer> getMetadataTypeConceptId() {
    return Optional.of(this.metadataTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "metadata_type_concept_id")
  private Concept metadataTypeConcept;
  
  public Optional<Concept> getMetadataTypeConcept() {
    return Optional.of(this.metadataTypeConcept);
  }
  @Column(name = "metadata_concept_id", insertable = false, updatable = false)
  private Integer metadataConceptId;
  
  public Optional<Integer> getMetadataConceptId() {
    return Optional.of(this.metadataConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "metadata_concept_id")
  private Concept metadataConcept;
  
  public Optional<Concept> getMetadataConcept() {
    return Optional.of(this.metadataConcept);
  }
  @Id
  @Column(name = "metadata_id", insertable = false, updatable = false)
  private Integer metadataId;
  
  public Optional<Integer> getMetadataId() {
    return Optional.of(this.metadataId);
  }
  
  @Override
  public String toString() {
    return "Metadata{id=" + this.metadataId + "}";
  }
  
  
}
