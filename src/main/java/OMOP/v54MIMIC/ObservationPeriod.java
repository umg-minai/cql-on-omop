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
@Table(name = "observation_period", schema = "cds_cdm")
public class ObservationPeriod {

    @Column(name = "observation_period_end_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime observationPeriodEndDate;
    
    public Date getObservationPeriodEndDate() {
        return new Date(this.observationPeriodEndDate.toLocalDate());
    }

    @Id
    @Column(name = "observation_period_id", insertable = false,
            updatable = false, nullable = false)
    private Long observationPeriodId;
    
    public Long getObservationPeriodId() {
        return this.observationPeriodId;
    }

    @Column(name = "observation_period_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime observationPeriodStartDate;
    
    public Date getObservationPeriodStartDate() {
        return new Date(this.observationPeriodStartDate.toLocalDate());
    }

    @Column(name = "period_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer periodTypeConceptId;

    public Integer getPeriodTypeConceptId() {
        return this.periodTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "period_type_concept_id")
    private Concept periodTypeConcept;
    
    public Concept getPeriodTypeConcept() {
        return this.periodTypeConcept;
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
        result.append("ObservationPeriod{");
        result.append("id=");
        result.append(this.observationPeriodId);
        result.append("}");
        return result.toString();
    }


}
