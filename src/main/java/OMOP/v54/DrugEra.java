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
@Table(name = "drug_era", schema = "cds_cdm")
public class DrugEra {

  @Column(name = "gap_days", insertable = false, updatable = false)
  private Integer gapDays;
  
  public Optional<Integer> getGapDays() {
    if (this.gapDays != null) {
      return Optional.of(this.gapDays);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_exposure_count", insertable = false, updatable = false)
  private Integer drugExposureCount;
  
  public Optional<Integer> getDrugExposureCount() {
    if (this.drugExposureCount != null) {
      return Optional.of(this.drugExposureCount);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_era_end_date", insertable = false, updatable = false)
  private ZonedDateTime drugEraEndDate;
  
  public Optional<Date> getDrugEraEndDate() {
    if (this.drugEraEndDate != null) {
      return Optional.of(new Date(this.drugEraEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_era_start_date", insertable = false, updatable = false)
  private ZonedDateTime drugEraStartDate;
  
  public Optional<Date> getDrugEraStartDate() {
    if (this.drugEraStartDate != null) {
      return Optional.of(new Date(this.drugEraStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
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
    return Optional.ofNullable(this.drugConcept);
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
    return Optional.ofNullable(this.person);
  }
  @Id
  @Column(name = "drug_era_id", insertable = false, updatable = false)
  private Integer drugEraId;
  
  public Optional<Integer> getDrugEraId() {
    if (this.drugEraId != null) {
      return Optional.of(this.drugEraId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("DrugEra{id=").append(this.drugEraId);
      result.append("}");
      return result.toString();
  }
  
}
