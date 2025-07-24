package OMOP.v53;

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
@Table(name = "drug_era", schema = "cds_cdm")
public class DrugEra {

    @Column(name = "drug_concept_id", updatable = false, nullable = false)
    private Integer drugConceptId;
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id", insertable = false, updatable = false)
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
    }

    public void setDrugConcept(final Concept newValue) {
        this.drugConcept = newValue;
        this.drugConceptId = newValue.getConceptId();
    }

    @Column(name = "drug_era_end_date", updatable = false, nullable = false)
    private ZonedDateTime drugEraEndDate;
    
    public Date getDrugEraEndDate() {
        return new Date(this.drugEraEndDate.toLocalDate());
    }

    public void setDrugEraEndDate(final Date newValue) {
        this.drugEraEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_era_id", updatable = false, nullable = false)
    private Integer drugEraId;
    
    public Integer getDrugEraId() {
        return this.drugEraId;
    }

    @Column(name = "drug_era_start_date", updatable = false, nullable = false)
    private ZonedDateTime drugEraStartDate;
    
    public Date getDrugEraStartDate() {
        return new Date(this.drugEraStartDate.toLocalDate());
    }

    public void setDrugEraStartDate(final Date newValue) {
        this.drugEraStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "drug_exposure_count", updatable = false, nullable = true)
    private Integer drugExposureCount;
    
    public Optional<Integer> getDrugExposureCount() {
        if (this.drugExposureCount != null) {
            return Optional.of(this.drugExposureCount);
        } else {
            return Optional.empty();
        }
    }

    public void setDrugExposureCount(final Integer newValue) {
        this.drugExposureCount = newValue;
    }

    @Column(name = "gap_days", updatable = false, nullable = true)
    private Integer gapDays;
    
    public Optional<Integer> getGapDays() {
        if (this.gapDays != null) {
            return Optional.of(this.gapDays);
        } else {
            return Optional.empty();
        }
    }

    public void setGapDays(final Integer newValue) {
        this.gapDays = newValue;
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
        result.append("DrugEra{");
        result.append("id=");
        result.append(this.drugEraId);
        result.append("}");
        return result.toString();
    }


}
