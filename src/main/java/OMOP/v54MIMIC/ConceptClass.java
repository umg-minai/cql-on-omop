// This file has been generated from a description of the OMOP CDM v5.4.MIMIC -
// do not edit
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
@Table(name = "concept_class")
public class ConceptClass {

    @Column(name = "concept_class_concept_id", updatable = false,
            nullable = false)
    private Integer conceptClassConceptId;
    
    public Integer getConceptClassConceptId() {
        return this.conceptClassConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_class_concept_id", insertable = false,
                updatable = false)
    private Concept conceptClassConcept;
    
    public Concept getConceptClassConcept() {
        return this.conceptClassConcept;
    }

    public void setConceptClassConcept(final Concept newValue) {
        this.conceptClassConcept = newValue;
        this.conceptClassConceptId = newValue.getConceptId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concept_class_id", updatable = false, nullable = false)
    private String conceptClassId;
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }

    @Column(name = "concept_class_name", updatable = false, nullable = false)
    private String conceptClassName;
    
    public String getConceptClassName() {
        return this.conceptClassName;
    }

    public void setConceptClassName(final String newValue) {
        this.conceptClassName = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("ConceptClass{");
        result.append("id=");
        result.append(this.conceptClassId);
        {
            result.append(", concept='");
            result.append(this.getConceptClassConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
