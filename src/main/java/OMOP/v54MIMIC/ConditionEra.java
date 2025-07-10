package OMOP.v54MIMIC;

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
@Table(name = "condition_era", schema = "cds_cdm")
public class ConditionEra {

    @Column(name = "condition_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer conditionConceptId;

    public Integer getConditionConceptId() {
        return this.conditionConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_concept_id")
    private Concept conditionConcept;
    
    public Concept getConditionConcept() {
        return this.conditionConcept;
    }

    @Column(name = "condition_era_end_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime conditionEraEndDate;
    
    public Date getConditionEraEndDate() {
        return new Date(this.conditionEraEndDate.toLocalDate());
    }

    @Id
    @Column(name = "condition_era_id", insertable = false, updatable = false,
            nullable = false)
    private Long conditionEraId;
    
    public Long getConditionEraId() {
        return this.conditionEraId;
    }

    @Column(name = "condition_era_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime conditionEraStartDate;
    
    public Date getConditionEraStartDate() {
        return new Date(this.conditionEraStartDate.toLocalDate());
    }

    @Column(name = "condition_occurrence_count", insertable = false,
            updatable = false, nullable = true)
    private Integer conditionOccurrenceCount;

    public Optional<Integer> getConditionOccurrenceCount() {
        if (this.conditionOccurrenceCount != null) {
            return Optional.of(this.conditionOccurrenceCount);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Long personId;
    
    public Long getPersonId() {
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
        result.append("ConditionEra{");
        result.append("id=");
        result.append(this.conditionEraId);
        result.append("}");
        return result.toString();
    }


}
