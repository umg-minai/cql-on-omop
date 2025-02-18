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
@Table(name = "observation_period", schema = "cds_cdm")
public class ObservationPeriod {

  @Column(name = "period_type_concept_id", insertable = false, updatable = false)
  private Integer periodTypeConceptId;
  
  public Optional<Integer> getPeriodTypeConceptId() {
    if (this.periodTypeConceptId != null) {
      return Optional.of(this.periodTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "period_type_concept_id")
  private Concept periodTypeConcept;
  
  public Optional<Concept> getPeriodTypeConcept() {
    return Optional.of(this.periodTypeConcept);
  }
  @Column(name = "observation_period_end_date", insertable = false, updatable = false)
  private ZonedDateTime observationPeriodEndDate;
  
  public Optional<Date> getObservationPeriodEndDate() {
    if (this.observationPeriodEndDate != null) {
      return Optional.of(new Date(this.observationPeriodEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "observation_period_start_date", insertable = false, updatable = false)
  private ZonedDateTime observationPeriodStartDate;
  
  public Optional<Date> getObservationPeriodStartDate() {
    if (this.observationPeriodStartDate != null) {
      return Optional.of(new Date(this.observationPeriodStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
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
  @Column(name = "observation_period_id", insertable = false, updatable = false)
  private Integer observationPeriodId;
  
  public Optional<Integer> getObservationPeriodId() {
    if (this.observationPeriodId != null) {
      return Optional.of(this.observationPeriodId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "ObservationPeriod{id=" + this.observationPeriodId + "}";
  }
  
  
}
