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
@Table(name = "metadata")
public class Metadata {

    @Embeddable
    private static class CompoundId {

        @Column(name = "metadata_concept_id", updatable = false,
                nullable = false)
        private Integer metadataConceptId;

        @Column(name = "metadata_type_concept_id", updatable = false,
                nullable = false)
        private Integer metadataTypeConceptId;

        @Column(name = "value_as_concept_id", updatable = false,
                nullable = true)
        private Integer valueAsConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return ((other.getClass() == this.getClass())
                            && (Objects.equals(this.metadataConceptId, otherInstance.metadataConceptId))
                            && (Objects.equals(this.metadataTypeConceptId, otherInstance.metadataTypeConceptId))
                            && (Objects.equals(this.valueAsConceptId, otherInstance.valueAsConceptId)));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.metadataConceptId, this.metadataTypeConceptId, this.valueAsConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("metadataConceptId=");
            result.append(this.metadataConceptId);
            result.append(", ");
            result.append("metadataTypeConceptId=");
            result.append(this.metadataTypeConceptId);
            result.append(", ");
            result.append("valueAsConceptId=");
            result.append(this.valueAsConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    public Integer getMetadataConceptId() {
        return this.compoundId.metadataConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_concept_id", insertable = false,
                updatable = false)
    @MapsId("metadataConceptId")
    private Concept metadataConcept;
    
    public Concept getMetadataConcept() {
        return this.metadataConcept;
    }

    public void setMetadataConcept(final Concept newValue) {
        this.metadataConcept = newValue;
        this.compoundId.metadataConceptId = newValue.getConceptId();
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

    public Integer getMetadataTypeConceptId() {
        return this.compoundId.metadataTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "metadata_type_concept_id", insertable = false,
                updatable = false)
    @MapsId("metadataTypeConceptId")
    private Concept metadataTypeConcept;
    
    public Concept getMetadataTypeConcept() {
        return this.metadataTypeConcept;
    }

    public void setMetadataTypeConcept(final Concept newValue) {
        this.metadataTypeConcept = newValue;
        this.compoundId.metadataTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "name", updatable = false, nullable = false)
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(final String newValue) {
        this.name = newValue;
    }

    public Optional<Integer> getValueAsConceptId() {
        if (this.compoundId.valueAsConceptId != null) {
            return Optional.of(this.compoundId.valueAsConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "value_as_concept_id", insertable = false,
                updatable = false)
    @MapsId("valueAsConceptId")
    private Concept valueAsConcept;
    
    public Optional<Concept> getValueAsConcept() {
        return Optional.ofNullable(this.valueAsConcept);
    }

    public void setValueAsConcept(final Concept newValue) {
        if (newValue == null) {
            this.valueAsConcept = null;
            this.compoundId.valueAsConceptId = null;
        } else {
            this.valueAsConcept = newValue;
            this.compoundId.valueAsConceptId = newValue.getConceptId();
        }
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
        result.append(this.compoundId);
        {
            result.append(", concept='");
            result.append(this.getMetadataConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
