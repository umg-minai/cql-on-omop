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
@Table(name = "death")
public class Death {

    @Embeddable
    private static class CompoundId {

        @Column(name = "person_id", updatable = false, nullable = false)
        private Long personId;

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
    private CompoundId compoundId = new CompoundId();

    @Column(name = "cause_concept_id", updatable = false, nullable = true)
    private Integer causeConceptId;
    
    public Optional<Integer> getCauseConceptId() {
        if (this.causeConceptId != null) {
            return Optional.of(this.causeConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_concept_id", insertable = false,
                updatable = false)
    private Concept causeConcept;
    
    public Optional<Concept> getCauseConcept() {
        return Optional.ofNullable(this.causeConcept);
    }

    public void setCauseConcept(final Concept newValue) {
        if (newValue == null) {
            this.causeConcept = null;
            this.causeConceptId = null;
        } else {
            this.causeConcept = newValue;
            this.causeConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "cause_source_concept_id", updatable = false,
            nullable = true)
    private Integer causeSourceConceptId;
    
    public Optional<Integer> getCauseSourceConceptId() {
        if (this.causeSourceConceptId != null) {
            return Optional.of(this.causeSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_source_concept_id", insertable = false,
                updatable = false)
    private Concept causeSourceConcept;
    
    public Optional<Concept> getCauseSourceConcept() {
        return Optional.ofNullable(this.causeSourceConcept);
    }

    public void setCauseSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.causeSourceConcept = null;
            this.causeSourceConceptId = null;
        } else {
            this.causeSourceConcept = newValue;
            this.causeSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "cause_source_value", updatable = false, nullable = true)
    private String causeSourceValue;
    
    public Optional<String> getCauseSourceValue() {
        if (this.causeSourceValue != null) {
            return Optional.of(this.causeSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setCauseSourceValue(final String newValue) {
        this.causeSourceValue = newValue;
    }

    @Column(name = "death_date", updatable = false, nullable = false)
    private ZonedDateTime deathDate;
    
    public Date getDeathDate() {
        return new Date(this.deathDate.toLocalDate());
    }

    public void setDeathDate(final Date newValue) {
        this.deathDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "death_datetime", updatable = false, nullable = true)
    private ZonedDateTime deathDatetime;
    
    public Optional<DateTime> getDeathDatetime() {
        if (this.deathDatetime != null) {
            return Optional.of(new DateTime(this.deathDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setDeathDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.deathDatetime = null;
        } else {
            this.deathDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "death_type_concept_id", updatable = false, nullable = true)
    private Integer deathTypeConceptId;
    
    public Optional<Integer> getDeathTypeConceptId() {
        if (this.deathTypeConceptId != null) {
            return Optional.of(this.deathTypeConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "death_type_concept_id", insertable = false,
                updatable = false)
    private Concept deathTypeConcept;
    
    public Optional<Concept> getDeathTypeConcept() {
        return Optional.ofNullable(this.deathTypeConcept);
    }

    public void setDeathTypeConcept(final Concept newValue) {
        if (newValue == null) {
            this.deathTypeConcept = null;
            this.deathTypeConceptId = null;
        } else {
            this.deathTypeConcept = newValue;
            this.deathTypeConceptId = newValue.getConceptId();
        }
    }

    public Long getPersonId() {
        return this.compoundId.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    @MapsId("personId")
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        this.compoundId.personId = newValue.getPersonId();
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Death{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
