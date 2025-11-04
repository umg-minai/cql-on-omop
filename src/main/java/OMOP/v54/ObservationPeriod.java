package OMOP.v54;

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
@Table(name = "observation_period")
public class ObservationPeriod {

    @Column(name = "observation_period_end_date", updatable = false,
            nullable = false)
    private ZonedDateTime observationPeriodEndDate;
    
    public Date getObservationPeriodEndDate() {
        return new Date(this.observationPeriodEndDate.toLocalDate());
    }

    public void setObservationPeriodEndDate(final Date newValue) {
        this.observationPeriodEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observation_period_id", updatable = false, nullable = false)
    private Integer observationPeriodId;
    
    public Integer getObservationPeriodId() {
        return this.observationPeriodId;
    }

    @Column(name = "observation_period_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime observationPeriodStartDate;
    
    public Date getObservationPeriodStartDate() {
        return new Date(this.observationPeriodStartDate.toLocalDate());
    }

    public void setObservationPeriodStartDate(final Date newValue) {
        this.observationPeriodStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "period_type_concept_id", updatable = false,
            nullable = false)
    private Integer periodTypeConceptId;
    
    public Integer getPeriodTypeConceptId() {
        return this.periodTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "period_type_concept_id", insertable = false,
                updatable = false)
    private Concept periodTypeConcept;
    
    public Concept getPeriodTypeConcept() {
        return this.periodTypeConcept;
    }

    public void setPeriodTypeConcept(final Concept newValue) {
        this.periodTypeConcept = newValue;
        this.periodTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
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
        result.append("ObservationPeriod{");
        result.append("id=");
        result.append(this.observationPeriodId);
        result.append("}");
        return result.toString();
    }


}
