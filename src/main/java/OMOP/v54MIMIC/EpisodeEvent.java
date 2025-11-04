package OMOP.v54MIMIC;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "episode_event")
public class EpisodeEvent {

    @Embeddable
    private static class CompoundId {

        @Column(name = "episode_event_field_concept_id", updatable = false,
                nullable = false)
        private Integer episodeEventFieldConceptId;

        @Column(name = "episode_id", updatable = false, nullable = false)
        private Long episodeId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.episodeEventFieldConceptId, otherInstance.episodeEventFieldConceptId)
                            && Objects.equals(this.episodeId, otherInstance.episodeId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.episodeEventFieldConceptId, this.episodeId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("episodeEventFieldConceptId=");
            result.append(this.episodeEventFieldConceptId);
            result.append(", ");
            result.append("episodeId=");
            result.append(this.episodeId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getEpisodeEventFieldConceptId() {
        return this.compoundId.episodeEventFieldConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_event_field_concept_id", insertable = false,
                updatable = false)
    @MapsId("episodeEventFieldConceptId")
    private Concept episodeEventFieldConcept;
    
    public Concept getEpisodeEventFieldConcept() {
        return this.episodeEventFieldConcept;
    }

    public void setEpisodeEventFieldConcept(final Concept newValue) {
        this.episodeEventFieldConcept = newValue;
        this.compoundId.episodeEventFieldConceptId = newValue.getConceptId();
    }

    public Long getEpisodeId() {
        return this.compoundId.episodeId;
    }

    @ManyToOne(targetEntity = Episode.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id", insertable = false, updatable = false)
    @MapsId("episodeId")
    private Episode episode;
    
    public Episode getEpisode() {
        return this.episode;
    }

    public void setEpisode(final Episode newValue) {
        this.episode = newValue;
        this.compoundId.episodeId = newValue.getEpisodeId();
    }

    @Column(name = "event_id", updatable = false, nullable = false)
    private Long eventId;
    
    public Long getEventId() {
        return this.eventId;
    }

    public void setEventId(final Long newValue) {
        this.eventId = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("EpisodeEvent{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
