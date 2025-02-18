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
@Table(name = "dose_era", schema = "cds_cdm")
public class DoseEra {

  @Column(name = "dose_era_end_date", insertable = false, updatable = false)
  private ZonedDateTime doseEraEndDate;
  
  public Optional<Date> getDoseEraEndDate() {
    if (this.doseEraEndDate != null) {
      return Optional.of(new Date(this.doseEraEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "dose_era_start_date", insertable = false, updatable = false)
  private ZonedDateTime doseEraStartDate;
  
  public Optional<Date> getDoseEraStartDate() {
    if (this.doseEraStartDate != null) {
      return Optional.of(new Date(this.doseEraStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "dose_value", insertable = false, updatable = false)
  private Float doseValue;
  
  public Optional<Float> getDoseValue() {
    if (this.doseValue != null) {
      return Optional.of(this.doseValue);
    } else {
      return Optional.empty();
    }
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
  @Column(name = "drug_concept_id", insertable = false, updatable = false)
  private Integer drugConceptId;
  
  public Optional<Integer> getDrugConceptId() {
    if (this.drugConceptId != null) {
      return Optional.of(this.drugConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.of(this.drugConcept);
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
  @Column(name = "dose_era_id", insertable = false, updatable = false)
  private Integer doseEraId;
  
  public Optional<Integer> getDoseEraId() {
    if (this.doseEraId != null) {
      return Optional.of(this.doseEraId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "DoseEra{id=" + this.doseEraId + "}";
  }
  
  
}
