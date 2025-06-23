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
@Table(name = "drug_era", schema = "cds_cdm")
public class DrugEra {

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

    @Column(name = "drug_era_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime drugEraEndDate;
    
    public Date getDrugEraEndDate() {
        return new Date(this.drugEraEndDate.toLocalDate());
    }

    @Id
    @Column(name = "drug_era_id", insertable = false, updatable = false,
            nullable = false)
    private Integer drugEraId;
    
    public Integer getDrugEraId() {
        return this.drugEraId;
    }

    @Column(name = "drug_era_start_date", insertable = false,
            updatable = false, nullable = false)
    private ZonedDateTime drugEraStartDate;
    
    public Date getDrugEraStartDate() {
        return new Date(this.drugEraStartDate.toLocalDate());
    }

    @Column(name = "drug_exposure_count", insertable = false,
            updatable = false, nullable = true)
    private Integer drugExposureCount;
    
    public Optional<Integer> getDrugExposureCount() {
        if (this.drugExposureCount != null) {
            return Optional.of(this.drugExposureCount);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "gap_days", insertable = false, updatable = false,
            nullable = true)
    private Integer gapDays;
    
    public Optional<Integer> getGapDays() {
        if (this.gapDays != null) {
            return Optional.of(this.gapDays);
        } else {
            return Optional.empty();
        }
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
        result.append("DrugEra{");
        result.append("id=");
        result.append(this.drugEraId);
        result.append("}");
        return result.toString();
    }


}
