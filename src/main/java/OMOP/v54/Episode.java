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
@Table(name = "episode", schema = "cds_cdm")
public class Episode {

  @Column(name = "episode_source_concept_id", insertable = false, updatable = false)
  private Integer episodeSourceConceptId;
  
  public Optional<Integer> getEpisodeSourceConceptId() {
    if (this.episodeSourceConceptId != null) {
      return Optional.of(this.episodeSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_source_concept_id")
  private Concept episodeSourceConcept;
  
  public Optional<Concept> getEpisodeSourceConcept() {
    return Optional.of(this.episodeSourceConcept);
  }
  @Column(name = "episode_source_value", insertable = false, updatable = false)
  private String episodeSourceValue;
  
  public Optional<String> getEpisodeSourceValue() {
    if (this.episodeSourceValue != null) {
      return Optional.of(this.episodeSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_type_concept_id", insertable = false, updatable = false)
  private Integer episodeTypeConceptId;
  
  public Optional<Integer> getEpisodeTypeConceptId() {
    if (this.episodeTypeConceptId != null) {
      return Optional.of(this.episodeTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_type_concept_id")
  private Concept episodeTypeConcept;
  
  public Optional<Concept> getEpisodeTypeConcept() {
    return Optional.of(this.episodeTypeConcept);
  }
  @Column(name = "episode_object_concept_id", insertable = false, updatable = false)
  private Integer episodeObjectConceptId;
  
  public Optional<Integer> getEpisodeObjectConceptId() {
    if (this.episodeObjectConceptId != null) {
      return Optional.of(this.episodeObjectConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_object_concept_id")
  private Concept episodeObjectConcept;
  
  public Optional<Concept> getEpisodeObjectConcept() {
    return Optional.of(this.episodeObjectConcept);
  }
  @Column(name = "episode_number", insertable = false, updatable = false)
  private Integer episodeNumber;
  
  public Optional<Integer> getEpisodeNumber() {
    if (this.episodeNumber != null) {
      return Optional.of(this.episodeNumber);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_parent_id", insertable = false, updatable = false)
  private Integer episodeParentId;
  
  public Optional<Integer> getEpisodeParentId() {
    if (this.episodeParentId != null) {
      return Optional.of(this.episodeParentId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime episodeEndDatetime;
  
  public Optional<DateTime> getEpisodeEndDatetime() {
    if (this.episodeEndDatetime != null) {
      return Optional.of(new DateTime(this.episodeEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_end_date", insertable = false, updatable = false)
  private ZonedDateTime episodeEndDate;
  
  public Optional<Date> getEpisodeEndDate() {
    if (this.episodeEndDate != null) {
      return Optional.of(new Date(this.episodeEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime episodeStartDatetime;
  
  public Optional<DateTime> getEpisodeStartDatetime() {
    if (this.episodeStartDatetime != null) {
      return Optional.of(new DateTime(this.episodeStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_start_date", insertable = false, updatable = false)
  private ZonedDateTime episodeStartDate;
  
  public Optional<Date> getEpisodeStartDate() {
    if (this.episodeStartDate != null) {
      return Optional.of(new Date(this.episodeStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_concept_id", insertable = false, updatable = false)
  private Integer episodeConceptId;
  
  public Optional<Integer> getEpisodeConceptId() {
    if (this.episodeConceptId != null) {
      return Optional.of(this.episodeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_concept_id")
  private Concept episodeConcept;
  
  public Optional<Concept> getEpisodeConcept() {
    return Optional.of(this.episodeConcept);
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
  @Column(name = "episode_id", insertable = false, updatable = false)
  private Integer episodeId;
  
  public Optional<Integer> getEpisodeId() {
    if (this.episodeId != null) {
      return Optional.of(this.episodeId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "Episode{id=" + this.episodeId + "}";
  }
  
  
}
