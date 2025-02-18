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
@Table(name = "specimen", schema = "cds_cdm")
public class Specimen {

  @Column(name = "disease_status_source_value", insertable = false, updatable = false)
  private String diseaseStatusSourceValue;
  
  public Optional<String> getDiseaseStatusSourceValue() {
    if (this.diseaseStatusSourceValue != null) {
      return Optional.of(this.diseaseStatusSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "anatomic_site_source_value", insertable = false, updatable = false)
  private String anatomicSiteSourceValue;
  
  public Optional<String> getAnatomicSiteSourceValue() {
    if (this.anatomicSiteSourceValue != null) {
      return Optional.of(this.anatomicSiteSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "unit_source_value", insertable = false, updatable = false)
  private String unitSourceValue;
  
  public Optional<String> getUnitSourceValue() {
    if (this.unitSourceValue != null) {
      return Optional.of(this.unitSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specimen_source_value", insertable = false, updatable = false)
  private String specimenSourceValue;
  
  public Optional<String> getSpecimenSourceValue() {
    if (this.specimenSourceValue != null) {
      return Optional.of(this.specimenSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specimen_source_id", insertable = false, updatable = false)
  private String specimenSourceId;
  
  public Optional<String> getSpecimenSourceId() {
    if (this.specimenSourceId != null) {
      return Optional.of(this.specimenSourceId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "disease_status_concept_id", insertable = false, updatable = false)
  private Integer diseaseStatusConceptId;
  
  public Optional<Integer> getDiseaseStatusConceptId() {
    if (this.diseaseStatusConceptId != null) {
      return Optional.of(this.diseaseStatusConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "disease_status_concept_id")
  private Concept diseaseStatusConcept;
  
  public Optional<Concept> getDiseaseStatusConcept() {
    return Optional.of(this.diseaseStatusConcept);
  }
  @Column(name = "anatomic_site_concept_id", insertable = false, updatable = false)
  private Integer anatomicSiteConceptId;
  
  public Optional<Integer> getAnatomicSiteConceptId() {
    if (this.anatomicSiteConceptId != null) {
      return Optional.of(this.anatomicSiteConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "anatomic_site_concept_id")
  private Concept anatomicSiteConcept;
  
  public Optional<Concept> getAnatomicSiteConcept() {
    return Optional.of(this.anatomicSiteConcept);
  }
  @Column(name = "unit_concept_id", insertable = false, updatable = false)
  private Integer unitConceptId;
  
  public Optional<Integer> getUnitConceptId() {
    if (this.unitConceptId != null) {
      return Optional.of(this.unitConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "unit_concept_id")
  private Concept unitConcept;
  
  public Optional<Concept> getUnitConcept() {
    return Optional.of(this.unitConcept);
  }
  @Column(name = "quantity", insertable = false, updatable = false)
  private Float quantity;
  
  public Optional<Float> getQuantity() {
    if (this.quantity != null) {
      return Optional.of(this.quantity);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specimen_datetime", insertable = false, updatable = false)
  private ZonedDateTime specimenDatetime;
  
  public Optional<DateTime> getSpecimenDatetime() {
    if (this.specimenDatetime != null) {
      return Optional.of(new DateTime(this.specimenDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specimen_date", insertable = false, updatable = false)
  private ZonedDateTime specimenDate;
  
  public Optional<Date> getSpecimenDate() {
    if (this.specimenDate != null) {
      return Optional.of(new Date(this.specimenDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specimen_type_concept_id", insertable = false, updatable = false)
  private Integer specimenTypeConceptId;
  
  public Optional<Integer> getSpecimenTypeConceptId() {
    if (this.specimenTypeConceptId != null) {
      return Optional.of(this.specimenTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specimen_type_concept_id")
  private Concept specimenTypeConcept;
  
  public Optional<Concept> getSpecimenTypeConcept() {
    return Optional.of(this.specimenTypeConcept);
  }
  @Column(name = "specimen_concept_id", insertable = false, updatable = false)
  private Integer specimenConceptId;
  
  public Optional<Integer> getSpecimenConceptId() {
    if (this.specimenConceptId != null) {
      return Optional.of(this.specimenConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specimen_concept_id")
  private Concept specimenConcept;
  
  public Optional<Concept> getSpecimenConcept() {
    return Optional.of(this.specimenConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "specimen_id", insertable = false, updatable = false)
  private Integer specimenId;
  
  public Optional<Integer> getSpecimenId() {
    if (this.specimenId != null) {
      return Optional.of(this.specimenId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "Specimen{id=" + this.specimenId + "}";
  }
  
  
}
