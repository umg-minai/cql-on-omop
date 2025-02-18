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
@Table(name = "death", schema = "cds_cdm")
public class Death {

  @Column(name = "cause_source_concept_id", insertable = false, updatable = false)
  private Integer causeSourceConceptId;
  
  public Optional<Integer> getCauseSourceConceptId() {
    if (this.causeSourceConceptId != null) {
      return Optional.of(this.causeSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cause_source_concept_id")
  private Concept causeSourceConcept;
  
  public Optional<Concept> getCauseSourceConcept() {
    return Optional.of(this.causeSourceConcept);
  }
  @Column(name = "cause_source_value", insertable = false, updatable = false)
  private String causeSourceValue;
  
  public Optional<String> getCauseSourceValue() {
    if (this.causeSourceValue != null) {
      return Optional.of(this.causeSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cause_concept_id", insertable = false, updatable = false)
  private Integer causeConceptId;
  
  public Optional<Integer> getCauseConceptId() {
    if (this.causeConceptId != null) {
      return Optional.of(this.causeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "cause_concept_id")
  private Concept causeConcept;
  
  public Optional<Concept> getCauseConcept() {
    return Optional.of(this.causeConcept);
  }
  @Column(name = "death_type_concept_id", insertable = false, updatable = false)
  private Integer deathTypeConceptId;
  
  public Optional<Integer> getDeathTypeConceptId() {
    if (this.deathTypeConceptId != null) {
      return Optional.of(this.deathTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "death_type_concept_id")
  private Concept deathTypeConcept;
  
  public Optional<Concept> getDeathTypeConcept() {
    return Optional.of(this.deathTypeConcept);
  }
  @Column(name = "death_datetime", insertable = false, updatable = false)
  private ZonedDateTime deathDatetime;
  
  public Optional<DateTime> getDeathDatetime() {
    if (this.deathDatetime != null) {
      return Optional.of(new DateTime(this.deathDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "death_date", insertable = false, updatable = false)
  private ZonedDateTime deathDate;
  
  public Optional<Date> getDeathDate() {
    if (this.deathDate != null) {
      return Optional.of(new Date(this.deathDate.toLocalDate()));
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
  
}
