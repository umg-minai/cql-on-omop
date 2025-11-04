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
@Table(name = "metadata", schema = "cds_cdm")
public class Metadata {

    @Column(name = "metadata_concept_id", updatable = false, nullable = false)
    private Integer metadataConceptId;
    
    public Integer getMetadataConceptId() {
        return this.metadataConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_concept_id", insertable = false,
                updatable = false)
    private Concept metadataConcept;
    
    public Concept getMetadataConcept() {
        return this.metadataConcept;
    }

    public void setMetadataConcept(final Concept newValue) {
        this.metadataConcept = newValue;
        this.metadataConceptId = newValue.getConceptId();
    }

    @Column(name = "metadata_date", updatable = false, nullable = true)
    private ZonedDateTime metadataDate;
    
    public Optional<Date> getMetadataDate() {
        if (this.metadataDate != null) {
            return Optional.of(new Date(this.metadataDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setMetadataDate(final Date newValue) {
        if (newValue == null) {
            this.metadataDate = null;
        } else {
            this.metadataDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    @Column(name = "metadata_datetime", updatable = false, nullable = true)
    private ZonedDateTime metadataDatetime;
    
    public Optional<DateTime> getMetadataDatetime() {
        if (this.metadataDatetime != null) {
            return Optional.of(new DateTime(this.metadataDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setMetadataDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.metadataDatetime = null;
        } else {
            this.metadataDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metadata_id", updatable = false, nullable = false)
    private Long metadataId;
    
    public Long getMetadataId() {
        return this.metadataId;
    }

    @Column(name = "metadata_type_concept_id", updatable = false,
            nullable = false)
    private Integer metadataTypeConceptId;
    
    public Integer getMetadataTypeConceptId() {
        return this.metadataTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_type_concept_id", insertable = false,
                updatable = false)
    private Concept metadataTypeConcept;
    
    public Concept getMetadataTypeConcept() {
        return this.metadataTypeConcept;
    }

    public void setMetadataTypeConcept(final Concept newValue) {
        this.metadataTypeConcept = newValue;
        this.metadataTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "name", updatable = false, nullable = false)
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(final String newValue) {
        this.name = newValue;
    }

    @Column(name = "value_as_concept_id", updatable = false, nullable = true)
    private Integer valueAsConceptId;
    
    public Optional<Integer> getValueAsConceptId() {
        if (this.valueAsConceptId != null) {
            return Optional.of(this.valueAsConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "value_as_concept_id", insertable = false,
                updatable = false)
    private Concept valueAsConcept;
    
    public Optional<Concept> getValueAsConcept() {
        return Optional.ofNullable(this.valueAsConcept);
    }

    public void setValueAsConcept(final Concept newValue) {
        if (newValue == null) {
            this.valueAsConcept = null;
            this.valueAsConceptId = null;
        } else {
            this.valueAsConcept = newValue;
            this.valueAsConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "value_as_number", updatable = false, nullable = true)
    private BigDecimal valueAsNumber;
    
    public Optional<BigDecimal> getValueAsNumber() {
        if (this.valueAsNumber != null) {
            return Optional.of(this.valueAsNumber);
        } else {
            return Optional.empty();
        }
    }

    public void setValueAsNumber(final BigDecimal newValue) {
        this.valueAsNumber = newValue;
    }

    @Column(name = "value_as_string", updatable = false, nullable = true)
    private String valueAsString;
    
    public Optional<String> getValueAsString() {
        if (this.valueAsString != null) {
            return Optional.of(this.valueAsString);
        } else {
            return Optional.empty();
        }
    }

    public void setValueAsString(final String newValue) {
        this.valueAsString = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Metadata{");
        result.append("id=");
        result.append(this.metadataId);
        {
            result.append(", concept='");
            result.append(this.getMetadataConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
