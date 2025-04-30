package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "death", schema = "cds_cdm")
public class Death {

  @Embeddable
  private static class CompoundId {

    @Column(name = "person_id")
    public Integer personId;

    @Override
    public boolean equals(final Object other) {
      if (this == other) {
        return true;
      } else {
        if (other instanceof CompoundId otherInstance) {
          return (other.getClass() == this.getClass()
                  && Objects.equals(this.personId, otherInstance.personId));
        } else {
          return false;
        }
      }
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.personId);
    }

    @Override
    public String toString() {
      final var result = new StringBuilder();
      result.append("CompoundId{");
      result.append("personId=");
      result.append(this.personId);
      result.append("}");
      return result.toString();
    }


  }

  @EmbeddedId
  private CompoundId embeddedPersonId;

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
    return Optional.ofNullable(this.causeSourceConcept);
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
    return Optional.ofNullable(this.causeConcept);
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
    return Optional.ofNullable(this.deathTypeConcept);
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

  //@Column(name = "person_id", insertable = false, updatable = false)
  //private Integer personId;

  public Optional<Integer> getPersonId() {
    /*if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }*/
    return Optional.of(this.embeddedPersonId.personId);
  }

  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @MapsId("personId")
  @JoinColumn(name = "person_id")
  private Person person;

  public Optional<Person> getPerson() {
    return Optional.ofNullable(this.person);
  }


}
