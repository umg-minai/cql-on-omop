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
@Table(name = "episode", schema = "cds_cdm")
public class Episode {

  @Column(name = "episode_source_concept_id", insertable = false, updatable = false)
  private Integer episodeSourceConceptId;
  
  public Optional<Integer> getEpisodeSourceConceptId() {
    return Optional.of(this.episodeSourceConceptId);
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
    return Optional.of(this.episodeSourceValue);
  }
  
  @Column(name = "episode_type_concept_id", insertable = false, updatable = false)
  private Integer episodeTypeConceptId;
  
  public Optional<Integer> getEpisodeTypeConceptId() {
    return Optional.of(this.episodeTypeConceptId);
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
    return Optional.of(this.episodeObjectConceptId);
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
    return Optional.of(this.episodeNumber);
  }
  
  @Column(name = "episode_parent_id", insertable = false, updatable = false)
  private Integer episodeParentId;
  
  public Optional<Integer> getEpisodeParentId() {
    return Optional.of(this.episodeParentId);
  }
  
  @Column(name = "episode_concept_id", insertable = false, updatable = false)
  private Integer episodeConceptId;
  
  public Optional<Integer> getEpisodeConceptId() {
    return Optional.of(this.episodeConceptId);
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
    return Optional.of(this.personId);
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
    return Optional.of(this.episodeId);
  }
  
  @Override
  public String toString() {
    return "Episode{id=" + this.episodeId + "}";
  }
  
  
}
