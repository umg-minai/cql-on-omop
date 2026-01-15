// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
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
@Table(name = "condition_era")
public class ConditionEra {

    @Column(name = "condition_concept_id", updatable = false, nullable = false)
    private Integer conditionConceptId;
    
    public Integer getConditionConceptId() {
        return this.conditionConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_concept_id", insertable = false,
                updatable = false)
    private Concept conditionConcept;
    
    public Concept getConditionConcept() {
        return this.conditionConcept;
    }

    public void setConditionConcept(final Concept newValue) {
        this.conditionConcept = newValue;
        this.conditionConceptId = newValue.getConceptId();
    }

    @Column(name = "condition_era_end_date", updatable = false,
            nullable = false)
    private ZonedDateTime conditionEraEndDate;
    
    public Date getConditionEraEndDate() {
        return new Date(this.conditionEraEndDate.toLocalDate());
    }

    public void setConditionEraEndDate(final Date newValue) {
        this.conditionEraEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_era_id", updatable = false, nullable = false)
    private Long conditionEraId;
    
    public Long getConditionEraId() {
        return this.conditionEraId;
    }

    @Column(name = "condition_era_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime conditionEraStartDate;
    
    public Date getConditionEraStartDate() {
        return new Date(this.conditionEraStartDate.toLocalDate());
    }

    public void setConditionEraStartDate(final Date newValue) {
        this.conditionEraStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "condition_occurrence_count", updatable = false,
            nullable = true)
    private Integer conditionOccurrenceCount;
    
    public Optional<Integer> getConditionOccurrenceCount() {
        if (this.conditionOccurrenceCount != null) {
            return Optional.of(this.conditionOccurrenceCount);
        } else {
            return Optional.empty();
        }
    }

    public void setConditionOccurrenceCount(final Integer newValue) {
        this.conditionOccurrenceCount = newValue;
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Long personId;
    
    public Long getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        this.personId = newValue.getPersonId();
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
