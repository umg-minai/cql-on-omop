// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
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
@Table(name = "specimen")
public class Specimen {

    @Column(name = "anatomic_site_concept_id", updatable = false,
            nullable = true)
    private Integer anatomicSiteConceptId;
    
    public Optional<Integer> getAnatomicSiteConceptId() {
        if (this.anatomicSiteConceptId != null) {
            return Optional.of(this.anatomicSiteConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "anatomic_site_concept_id", insertable = false,
                updatable = false)
    private Concept anatomicSiteConcept;
    
    public Optional<Concept> getAnatomicSiteConcept() {
        return Optional.ofNullable(this.anatomicSiteConcept);
    }

    public void setAnatomicSiteConcept(final Concept newValue) {
        if (newValue == null) {
            this.anatomicSiteConcept = null;
            this.anatomicSiteConceptId = null;
        } else {
            this.anatomicSiteConcept = newValue;
            this.anatomicSiteConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "anatomic_site_source_value", updatable = false,
            nullable = true)
    private String anatomicSiteSourceValue;
    
    public Optional<String> getAnatomicSiteSourceValue() {
        if (this.anatomicSiteSourceValue != null) {
            return Optional.of(this.anatomicSiteSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setAnatomicSiteSourceValue(final String newValue) {
        this.anatomicSiteSourceValue = newValue;
    }

    @Column(name = "disease_status_concept_id", updatable = false,
            nullable = true)
    private Integer diseaseStatusConceptId;
    
    public Optional<Integer> getDiseaseStatusConceptId() {
        if (this.diseaseStatusConceptId != null) {
            return Optional.of(this.diseaseStatusConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_status_concept_id", insertable = false,
                updatable = false)
    private Concept diseaseStatusConcept;
    
    public Optional<Concept> getDiseaseStatusConcept() {
        return Optional.ofNullable(this.diseaseStatusConcept);
    }

    public void setDiseaseStatusConcept(final Concept newValue) {
        if (newValue == null) {
            this.diseaseStatusConcept = null;
            this.diseaseStatusConceptId = null;
        } else {
            this.diseaseStatusConcept = newValue;
            this.diseaseStatusConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "disease_status_source_value", updatable = false,
            nullable = true)
    private String diseaseStatusSourceValue;
    
    public Optional<String> getDiseaseStatusSourceValue() {
        if (this.diseaseStatusSourceValue != null) {
            return Optional.of(this.diseaseStatusSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDiseaseStatusSourceValue(final String newValue) {
        this.diseaseStatusSourceValue = newValue;
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

    @Column(name = "quantity", updatable = false, nullable = true)
    private BigDecimal quantity;
    
    public Optional<BigDecimal> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
        } else {
            return Optional.empty();
        }
    }

    public void setQuantity(final BigDecimal newValue) {
        this.quantity = newValue;
    }

    @Column(name = "specimen_concept_id", updatable = false, nullable = false)
    private Integer specimenConceptId;
    
    public Integer getSpecimenConceptId() {
        return this.specimenConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specimen_concept_id", insertable = false,
                updatable = false)
    private Concept specimenConcept;
    
    public Concept getSpecimenConcept() {
        return this.specimenConcept;
    }

    public void setSpecimenConcept(final Concept newValue) {
        this.specimenConcept = newValue;
        this.specimenConceptId = newValue.getConceptId();
    }

    @Column(name = "specimen_date", updatable = false, nullable = false)
    private ZonedDateTime specimenDate;
    
    public Date getSpecimenDate() {
        return new Date(this.specimenDate.toLocalDate());
    }

    public void setSpecimenDate(final Date newValue) {
        this.specimenDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "specimen_datetime", updatable = false, nullable = true)
    private ZonedDateTime specimenDatetime;
    
    public Optional<DateTime> getSpecimenDatetime() {
        if (this.specimenDatetime != null) {
            return Optional.of(new DateTime(this.specimenDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setSpecimenDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.specimenDatetime = null;
        } else {
            this.specimenDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specimen_id", updatable = false, nullable = false)
    private Integer specimenId;
    
    public Integer getSpecimenId() {
        return this.specimenId;
    }

    @Column(name = "specimen_source_id", updatable = false, nullable = true)
    private String specimenSourceId;
    
    public Optional<String> getSpecimenSourceId() {
        if (this.specimenSourceId != null) {
            return Optional.of(this.specimenSourceId);
        } else {
            return Optional.empty();
        }
    }

    public void setSpecimenSourceId(final String newValue) {
        this.specimenSourceId = newValue;
    }

    @Column(name = "specimen_source_value", updatable = false, nullable = true)
    private String specimenSourceValue;
    
    public Optional<String> getSpecimenSourceValue() {
        if (this.specimenSourceValue != null) {
            return Optional.of(this.specimenSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setSpecimenSourceValue(final String newValue) {
        this.specimenSourceValue = newValue;
    }

    @Column(name = "specimen_type_concept_id", updatable = false,
            nullable = false)
    private Integer specimenTypeConceptId;
    
    public Integer getSpecimenTypeConceptId() {
        return this.specimenTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specimen_type_concept_id", insertable = false,
                updatable = false)
    private Concept specimenTypeConcept;
    
    public Concept getSpecimenTypeConcept() {
        return this.specimenTypeConcept;
    }

    public void setSpecimenTypeConcept(final Concept newValue) {
        this.specimenTypeConcept = newValue;
        this.specimenTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "unit_concept_id", updatable = false, nullable = true)
    private Integer unitConceptId;
    
    public Optional<Integer> getUnitConceptId() {
        if (this.unitConceptId != null) {
            return Optional.of(this.unitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_concept_id", insertable = false, updatable = false)
    private Concept unitConcept;
    
    public Optional<Concept> getUnitConcept() {
        return Optional.ofNullable(this.unitConcept);
    }

    public void setUnitConcept(final Concept newValue) {
        if (newValue == null) {
            this.unitConcept = null;
            this.unitConceptId = null;
        } else {
            this.unitConcept = newValue;
            this.unitConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "unit_source_value", updatable = false, nullable = true)
    private String unitSourceValue;
    
    public Optional<String> getUnitSourceValue() {
        if (this.unitSourceValue != null) {
            return Optional.of(this.unitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setUnitSourceValue(final String newValue) {
        this.unitSourceValue = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Specimen{");
        result.append("id=");
        result.append(this.specimenId);
        {
            result.append(", concept='");
            result.append(this.getSpecimenConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
