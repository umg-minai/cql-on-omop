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
@Table(name = "dose_era", schema = "cds_cdm")
public class DoseEra {

    @Column(name = "dose_era_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime doseEraEndDate;
    
    public Date getDoseEraEndDate() {
        return new Date(this.doseEraEndDate.toLocalDate());
    }

    @Id
    @Column(name = "dose_era_id", insertable = false, updatable = false,
            nullable = false)
    private Long doseEraId;
    
    public Long getDoseEraId() {
        return this.doseEraId;
    }

    @Column(name = "dose_era_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime doseEraStartDate;
    
    public Date getDoseEraStartDate() {
        return new Date(this.doseEraStartDate.toLocalDate());
    }

    @Column(name = "dose_value", insertable = false, updatable = false,
            nullable = false)
    private BigDecimal doseValue;
    
    public BigDecimal getDoseValue() {
        return this.doseValue;
    }

    @Column(name = "drug_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer drugConceptId;

    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id")
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
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

    @Column(name = "unit_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer unitConceptId;

    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_concept_id")
    private Concept unitConcept;
    
    public Concept getUnitConcept() {
        return this.unitConcept;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("DoseEra{");
        result.append("id=");
        result.append(this.doseEraId);
        result.append("}");
        return result.toString();
    }


}
