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
@Table(name = "episode_event", schema = "cds_cdm")
public class EpisodeEvent {

  @Column(name = "episode_event_field_concept_id", insertable = false, updatable = false)
  private Integer episodeEventFieldConceptId;
  
  public Optional<Integer> getEpisodeEventFieldConceptId() {
    if (this.episodeEventFieldConceptId != null) {
      return Optional.of(this.episodeEventFieldConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_event_field_concept_id")
  private Concept episodeEventFieldConcept;
  
  public Optional<Concept> getEpisodeEventFieldConcept() {
    return Optional.ofNullable(this.episodeEventFieldConcept);
  }
  @Column(name = "event_id", insertable = false, updatable = false)
  private Integer eventId;
  
  public Optional<Integer> getEventId() {
    if (this.eventId != null) {
      return Optional.of(this.eventId);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "episode_id", insertable = false, updatable = false)
  private Integer episodeId;
  
  public Optional<Integer> getEpisodeId() {
    if (this.episodeId != null) {
      return Optional.of(this.episodeId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Episode.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "episode_id")
  private Episode episode;
  
  public Optional<Episode> getEpisode() {
    return Optional.ofNullable(this.episode);
  }
  
  
}
