package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "metadata", schema = "cds_cdm")
public class Metadata {

  @Column(name = "metadata_datetime", insertable = false, updatable = false)
  private ZonedDateTime metadataDatetime;
  
  public Optional<DateTime> getMetadataDatetime() {
    if (this.metadataDatetime != null) {
      return Optional.of(new DateTime(this.metadataDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "metadata_date", insertable = false, updatable = false)
  private ZonedDateTime metadataDate;
  
  public Optional<Date> getMetadataDate() {
    if (this.metadataDate != null) {
      return Optional.of(new Date(this.metadataDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "value_as_number", insertable = false, updatable = false)
  private BigDecimal valueAsNumber;
  
  public Optional<BigDecimal> getValueAsNumber() {
    if (this.valueAsNumber != null) {
      return Optional.of(this.valueAsNumber);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "value_as_concept_id", insertable = false, updatable = false)
  private Integer valueAsConceptId;
  
  public Optional<Integer> getValueAsConceptId() {
    if (this.valueAsConceptId != null) {
      return Optional.of(this.valueAsConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "value_as_concept_id")
  private Concept valueAsConcept;
  
  public Optional<Concept> getValueAsConcept() {
    return Optional.ofNullable(this.valueAsConcept);
  }
  @Column(name = "value_as_string", insertable = false, updatable = false)
  private String valueAsString;
  
  public Optional<String> getValueAsString() {
    if (this.valueAsString != null) {
      return Optional.of(this.valueAsString);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "name", insertable = false, updatable = false)
  private String name;
  
  public Optional<String> getName() {
    if (this.name != null) {
      return Optional.of(this.name);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "metadata_type_concept_id", insertable = false, updatable = false)
  private Integer metadataTypeConceptId;
  
  public Optional<Integer> getMetadataTypeConceptId() {
    if (this.metadataTypeConceptId != null) {
      return Optional.of(this.metadataTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "metadata_type_concept_id")
  private Concept metadataTypeConcept;
  
  public Optional<Concept> getMetadataTypeConcept() {
    return Optional.ofNullable(this.metadataTypeConcept);
  }
  @Column(name = "metadata_concept_id", insertable = false, updatable = false)
  private Integer metadataConceptId;
  
  public Optional<Integer> getMetadataConceptId() {
    if (this.metadataConceptId != null) {
      return Optional.of(this.metadataConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "metadata_concept_id")
  private Concept metadataConcept;
  
  public Optional<Concept> getMetadataConcept() {
    return Optional.ofNullable(this.metadataConcept);
  }
  @Id
  @Column(name = "metadata_id", insertable = false, updatable = false)
  private Integer metadataId;
  
  public Optional<Integer> getMetadataId() {
    if (this.metadataId != null) {
      return Optional.of(this.metadataId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("Metadata{id=").append(this.metadataId);
      this.getMetadataConcept().ifPresent(concept -> {
        result.append(", concept='")
        .append(concept.getConceptName().get())
        .append("'");
      });
      result.append("}");
      return result.toString();
  }
  
}
