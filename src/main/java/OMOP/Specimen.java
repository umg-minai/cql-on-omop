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
@Table(name = "specimen", schema = "cds_cdm")
public class Specimen {

  @Column(name = "disease_status_source_value", insertable = false, updatable = false)
  private String diseaseStatusSourceValue;
  
  public Optional<String> getDiseaseStatusSourceValue() {
    return Optional.of(this.diseaseStatusSourceValue);
  }
  
  @Column(name = "anatomic_site_source_value", insertable = false, updatable = false)
  private String anatomicSiteSourceValue;
  
  public Optional<String> getAnatomicSiteSourceValue() {
    return Optional.of(this.anatomicSiteSourceValue);
  }
  
  @Column(name = "unit_source_value", insertable = false, updatable = false)
  private String unitSourceValue;
  
  public Optional<String> getUnitSourceValue() {
    return Optional.of(this.unitSourceValue);
  }
  
  @Column(name = "specimen_source_value", insertable = false, updatable = false)
  private String specimenSourceValue;
  
  public Optional<String> getSpecimenSourceValue() {
    return Optional.of(this.specimenSourceValue);
  }
  
  @Column(name = "specimen_source_id", insertable = false, updatable = false)
  private String specimenSourceId;
  
  public Optional<String> getSpecimenSourceId() {
    return Optional.of(this.specimenSourceId);
  }
  
  @Column(name = "disease_status_concept_id", insertable = false, updatable = false)
  private Integer diseaseStatusConceptId;
  
  public Optional<Integer> getDiseaseStatusConceptId() {
    return Optional.of(this.diseaseStatusConceptId);
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
    return Optional.of(this.anatomicSiteConceptId);
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
    return Optional.of(this.unitConceptId);
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
    return Optional.of(this.quantity);
  }
  
  @Column(name = "specimen_type_concept_id", insertable = false, updatable = false)
  private Integer specimenTypeConceptId;
  
  public Optional<Integer> getSpecimenTypeConceptId() {
    return Optional.of(this.specimenTypeConceptId);
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
    return Optional.of(this.specimenConceptId);
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
    return Optional.of(this.personId);
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
    return Optional.of(this.specimenId);
  }
  
  @Override
  public String toString() {
    return "Specimen{id=" + this.specimenId + "}";
  }
  
  
}
