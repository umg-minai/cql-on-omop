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
@Table(name = "specimen", schema = "cds_cdm")
public class Specimen {

    @Column(name = "anatomic_site_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer anatomicSiteConceptId;

    public Optional<Integer> getAnatomicSiteConceptId() {
        if (this.anatomicSiteConceptId != null) {
            return Optional.of(this.anatomicSiteConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "anatomic_site_concept_id")
    private Concept anatomicSiteConcept;
    
    public Optional<Concept> getAnatomicSiteConcept() {
        return Optional.ofNullable(this.anatomicSiteConcept);
    }

    @Column(name = "anatomic_site_source_value", insertable = false,
            updatable = false, nullable = true)
    private String anatomicSiteSourceValue;
    
    public Optional<String> getAnatomicSiteSourceValue() {
        if (this.anatomicSiteSourceValue != null) {
            return Optional.of(this.anatomicSiteSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "disease_status_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Integer diseaseStatusConceptId;

    public Optional<Integer> getDiseaseStatusConceptId() {
        if (this.diseaseStatusConceptId != null) {
            return Optional.of(this.diseaseStatusConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_status_concept_id")
    private Concept diseaseStatusConcept;
    
    public Optional<Concept> getDiseaseStatusConcept() {
        return Optional.ofNullable(this.diseaseStatusConcept);
    }

    @Column(name = "disease_status_source_value", insertable = false,
            updatable = false, nullable = true)
    private String diseaseStatusSourceValue;
    
    public Optional<String> getDiseaseStatusSourceValue() {
        if (this.diseaseStatusSourceValue != null) {
            return Optional.of(this.diseaseStatusSourceValue);
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

    @Column(name = "quantity", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal quantity;
    
    public Optional<BigDecimal> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "specimen_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer specimenConceptId;

    public Integer getSpecimenConceptId() {
        return this.specimenConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specimen_concept_id")
    private Concept specimenConcept;
    
    public Concept getSpecimenConcept() {
        return this.specimenConcept;
    }

    @Column(name = "specimen_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime specimenDate;
    
    public Date getSpecimenDate() {
        return new Date(this.specimenDate.toLocalDate());
    }

    @Column(name = "specimen_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime specimenDatetime;
    
    public Optional<DateTime> getSpecimenDatetime() {
        if (this.specimenDatetime != null) {
            return Optional.of(new DateTime(this.specimenDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "specimen_id", insertable = false, updatable = false,
            nullable = false)
    private Long specimenId;
    
    public Long getSpecimenId() {
        return this.specimenId;
    }

    @Column(name = "specimen_source_id", insertable = false, updatable = false,
            nullable = true)
    private String specimenSourceId;
    
    public Optional<String> getSpecimenSourceId() {
        if (this.specimenSourceId != null) {
            return Optional.of(this.specimenSourceId);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "specimen_source_value", insertable = false,
            updatable = false, nullable = true)
    private String specimenSourceValue;
    
    public Optional<String> getSpecimenSourceValue() {
        if (this.specimenSourceValue != null) {
            return Optional.of(this.specimenSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "specimen_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer specimenTypeConceptId;

    public Integer getSpecimenTypeConceptId() {
        return this.specimenTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specimen_type_concept_id")
    private Concept specimenTypeConcept;
    
    public Concept getSpecimenTypeConcept() {
        return this.specimenTypeConcept;
    }

    @Column(name = "unit_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Integer unitConceptId;

    public Optional<Integer> getUnitConceptId() {
        if (this.unitConceptId != null) {
            return Optional.of(this.unitConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_concept_id")
    private Concept unitConcept;
    
    public Optional<Concept> getUnitConcept() {
        return Optional.ofNullable(this.unitConcept);
    }

    @Column(name = "unit_source_value", insertable = false, updatable = false,
            nullable = true)
    private String unitSourceValue;
    
    public Optional<String> getUnitSourceValue() {
        if (this.unitSourceValue != null) {
            return Optional.of(this.unitSourceValue);
        } else {
            return Optional.empty();
        }
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
