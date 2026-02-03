// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
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
@Table(name = "dose_era")
public class DoseEra {

    @Column(name = "dose_era_end_date", updatable = false, nullable = false)
    private ZonedDateTime doseEraEndDate;
    
    public Date getDoseEraEndDate() {
        return new Date(this.doseEraEndDate.toLocalDate());
    }

    public void setDoseEraEndDate(final Date newValue) {
        this.doseEraEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dose_era_id", updatable = false, nullable = false)
    private Integer doseEraId;
    
    public Integer getDoseEraId() {
        return this.doseEraId;
    }

    @Column(name = "dose_era_start_date", updatable = false, nullable = false)
    private ZonedDateTime doseEraStartDate;
    
    public Date getDoseEraStartDate() {
        return new Date(this.doseEraStartDate.toLocalDate());
    }

    public void setDoseEraStartDate(final Date newValue) {
        this.doseEraStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "dose_value", updatable = false, nullable = false)
    private BigDecimal doseValue;
    
    public BigDecimal getDoseValue() {
        return this.doseValue;
    }

    public void setDoseValue(final BigDecimal newValue) {
        this.doseValue = newValue;
    }

    @Column(name = "drug_concept_id", updatable = false, nullable = false)
    private Integer drugConceptId;
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDrugConceptId(final Integer newValue) {
        this.drugConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id", insertable = false, updatable = false)
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
    }

    public void setDrugConcept(final Concept newValue) {
        this.drugConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.drugConceptId = (newValue != null) ? newValue.getConceptId() : 0;
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
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.personId = (newValue != null) ? newValue.getPersonId() : 0;
    }

    @Column(name = "unit_concept_id", updatable = false, nullable = false)
    private Integer unitConceptId;
    
    public Integer getUnitConceptId() {
        return this.unitConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setUnitConceptId(final Integer newValue) {
        this.unitConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_concept_id", insertable = false, updatable = false)
    private Concept unitConcept;
    
    public Concept getUnitConcept() {
        return this.unitConcept;
    }

    public void setUnitConcept(final Concept newValue) {
        this.unitConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.unitConceptId = (newValue != null) ? newValue.getConceptId() : 0;
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
