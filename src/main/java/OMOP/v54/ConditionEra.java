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
@Table(name = "condition_era", schema = "cds_cdm")
public class ConditionEra {

  @Column(name = "condition_occurrence_count", insertable = false, updatable = false)
  private Integer conditionOccurrenceCount;
  
  public Optional<Integer> getConditionOccurrenceCount() {
    if (this.conditionOccurrenceCount != null) {
      return Optional.of(this.conditionOccurrenceCount);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_era_end_date", insertable = false, updatable = false)
  private ZonedDateTime conditionEraEndDate;
  
  public Optional<Date> getConditionEraEndDate() {
    if (this.conditionEraEndDate != null) {
      return Optional.of(new Date(this.conditionEraEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_era_start_date", insertable = false, updatable = false)
  private ZonedDateTime conditionEraStartDate;
  
  public Optional<Date> getConditionEraStartDate() {
    if (this.conditionEraStartDate != null) {
      return Optional.of(new Date(this.conditionEraStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "condition_concept_id", insertable = false, updatable = false)
  private Integer conditionConceptId;
  
  public Optional<Integer> getConditionConceptId() {
    if (this.conditionConceptId != null) {
      return Optional.of(this.conditionConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "condition_concept_id")
  private Concept conditionConcept;
  
  public Optional<Concept> getConditionConcept() {
    return Optional.ofNullable(this.conditionConcept);
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
  @Column(name = "condition_era_id", insertable = false, updatable = false)
  private Integer conditionEraId;
  
  public Optional<Integer> getConditionEraId() {
    if (this.conditionEraId != null) {
      return Optional.of(this.conditionEraId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("ConditionEra{id=").append(this.conditionEraId);
      result.append("}");
      return result.toString();
  }
  
}
