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
@Table(name = "metadata", schema = "cds_cdm")
public class Metadata {

    @Column(name = "metadata_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long metadataConceptId;
    
    public Long getMetadataConceptId() {
        return this.metadataConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_concept_id")
    private Concept metadataConcept;
    
    public Concept getMetadataConcept() {
        return this.metadataConcept;
    }

    @Column(name = "metadata_date", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime metadataDate;
    
    public Optional<Date> getMetadataDate() {
        if (this.metadataDate != null) {
            return Optional.of(new Date(this.metadataDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "metadata_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime metadataDatetime;
    
    public Optional<DateTime> getMetadataDatetime() {
        if (this.metadataDatetime != null) {
            return Optional.of(new DateTime(this.metadataDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "metadata_id", insertable = false, updatable = false,
            nullable = false)
    private Long metadataId;
    
    public Long getMetadataId() {
        return this.metadataId;
    }

    @Column(name = "metadata_type_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long metadataTypeConceptId;
    
    public Long getMetadataTypeConceptId() {
        return this.metadataTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_type_concept_id")
    private Concept metadataTypeConcept;
    
    public Concept getMetadataTypeConcept() {
        return this.metadataTypeConcept;
    }

    @Column(name = "name", insertable = false, updatable = false,
            nullable = false)
    private String name;
    
    public String getName() {
        return this.name;
    }

    @Column(name = "value_as_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long valueAsConceptId;
    
    public Optional<Long> getValueAsConceptId() {
        if (this.valueAsConceptId != null) {
            return Optional.of(this.valueAsConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "value_as_concept_id")
    private Concept valueAsConcept;
    
    public Optional<Concept> getValueAsConcept() {
        return Optional.ofNullable(this.valueAsConcept);
    }

    @Column(name = "value_as_number", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal valueAsNumber;
    
    public Optional<BigDecimal> getValueAsNumber() {
        if (this.valueAsNumber != null) {
            return Optional.of(this.valueAsNumber);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "value_as_string", insertable = false, updatable = false,
            nullable = true)
    private String valueAsString;
    
    public Optional<String> getValueAsString() {
        if (this.valueAsString != null) {
            return Optional.of(this.valueAsString);
        } else {
            return Optional.empty();
        }
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
