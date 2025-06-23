package OMOP.v54;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "episode", schema = "cds_cdm")
public class Episode {

    @Column(name = "episode_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer episodeConceptId;
    
    public Integer getEpisodeConceptId() {
        return this.episodeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_concept_id")
    private Concept episodeConcept;
    
    public Concept getEpisodeConcept() {
        return this.episodeConcept;
    }

    @Column(name = "episode_end_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime episodeEndDate;
    
    public Optional<Date> getEpisodeEndDate() {
        if (this.episodeEndDate != null) {
            return Optional.of(new Date(this.episodeEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "episode_end_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime episodeEndDatetime;
    
    public Optional<DateTime> getEpisodeEndDatetime() {
        if (this.episodeEndDatetime != null) {
            return Optional.of(new DateTime(this.episodeEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "episode_id", insertable = false, updatable = false,
            nullable = false)
    private Integer episodeId;
    
    public Integer getEpisodeId() {
        return this.episodeId;
    }

    @Column(name = "episode_number", insertable = false, updatable = false,
            nullable = true)
    private Integer episodeNumber;
    
    public Optional<Integer> getEpisodeNumber() {
        if (this.episodeNumber != null) {
            return Optional.of(this.episodeNumber);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "episode_object_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer episodeObjectConceptId;
    
    public Integer getEpisodeObjectConceptId() {
        return this.episodeObjectConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_object_concept_id")
    private Concept episodeObjectConcept;
    
    public Concept getEpisodeObjectConcept() {
        return this.episodeObjectConcept;
    }

    @Column(name = "episode_parent_id", insertable = false, updatable = false,
            nullable = true)
    private Integer episodeParentId;
    
    public Optional<Integer> getEpisodeParentId() {
        if (this.episodeParentId != null) {
            return Optional.of(this.episodeParentId);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "episode_source_concept_id", insertable = false,
            updatable = false, nullable = true)
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
        return Optional.ofNullable(this.episodeSourceConcept);
    }

    @Column(name = "episode_source_value", insertable = false,
            updatable = false, nullable = true)
    private String episodeSourceValue;
    
    public Optional<String> getEpisodeSourceValue() {
        if (this.episodeSourceValue != null) {
            return Optional.of(this.episodeSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "episode_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime episodeStartDate;
    
    public Date getEpisodeStartDate() {
        return new Date(this.episodeStartDate.toLocalDate());
    }

    @Column(name = "episode_start_datetime", insertable = false,
            updatable = false, nullable = true)
    private ZonedDateTime episodeStartDatetime;
    
    public Optional<DateTime> getEpisodeStartDatetime() {
        if (this.episodeStartDatetime != null) {
            return Optional.of(new DateTime(this.episodeStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "episode_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer episodeTypeConceptId;
    
    public Integer getEpisodeTypeConceptId() {
        return this.episodeTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_type_concept_id")
    private Concept episodeTypeConcept;
    
    public Concept getEpisodeTypeConcept() {
        return this.episodeTypeConcept;
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Episode{");
        result.append("id=");
        result.append(this.episodeId);
        {
            result.append(", concept='");
            result.append(this.getEpisodeConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
