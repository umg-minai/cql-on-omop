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
@Table(name = "attribute_definition", schema = "cds_cdm")
public class AttributeDefinition {

    @Embeddable
    private static class CompoundId {

        @Column(name = "attribute_type_concept_id", insertable = false,
                updatable = false, nullable = false)
        private Integer attributeTypeConceptId;

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            } else {
                if (other instanceof CompoundId otherInstance) {
                    return (other.getClass() == this.getClass()
                            && Objects.equals(this.attributeTypeConceptId, otherInstance.attributeTypeConceptId));
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.attributeTypeConceptId);
        }

        @Override
        public String toString() {
            final var result = new StringBuilder();
            result.append("CompoundId{");
            result.append("attributeTypeConceptId=");
            result.append(this.attributeTypeConceptId);
            result.append("}");
            return result.toString();
        }


    }

    @EmbeddedId
    private CompoundId compoundId = new CompoundId();

    @Column(name = "attribute_definition_id", insertable = false,
            updatable = false, nullable = false)
    private Integer attributeDefinitionId;
    
    public Integer getAttributeDefinitionId() {
        return this.attributeDefinitionId;
    }

    public void setAttributeDefinitionId(final Integer newValue) {
        this.attributeDefinitionId = newValue;
    }

    @Column(name = "attribute_description", insertable = false,
            updatable = false, nullable = true)
    private String attributeDescription;
    
    public Optional<String> getAttributeDescription() {
        if (this.attributeDescription != null) {
            return Optional.of(this.attributeDescription);
        } else {
            return Optional.empty();
        }
    }

    public void setAttributeDescription(final String newValue) {
        this.attributeDescription = newValue;
    }

    @Column(name = "attribute_name", insertable = false, updatable = false,
            nullable = false)
    private String attributeName;
    
    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(final String newValue) {
        this.attributeName = newValue;
    }

    @Column(name = "attribute_syntax", insertable = false, updatable = false,
            nullable = true)
    private String attributeSyntax;
    
    public Optional<String> getAttributeSyntax() {
        if (this.attributeSyntax != null) {
            return Optional.of(this.attributeSyntax);
        } else {
            return Optional.empty();
        }
    }

    public void setAttributeSyntax(final String newValue) {
        this.attributeSyntax = newValue;
    }

    public Integer getAttributeTypeConceptId() {
        return this.compoundId.attributeTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_type_concept_id")
    @MapsId("attributeTypeConceptId")
    private Concept attributeTypeConcept;
    
    public Concept getAttributeTypeConcept() {
        return this.attributeTypeConcept;
    }

    public void setAttributeTypeConcept(final Concept newValue) {
        this.attributeTypeConcept = newValue;
        this.compoundId.attributeTypeConceptId = newValue.getConceptId();
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("AttributeDefinition{");
        result.append("id=");
        result.append(this.compoundId);
        result.append("}");
        return result.toString();
    }


}
