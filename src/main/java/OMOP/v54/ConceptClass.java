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
@Table(name = "concept_class", schema = "cds_cdm")
public class ConceptClass {

    @Column(name = "concept_class_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer conceptClassConceptId;
    
    public Integer getConceptClassConceptId() {
        return this.conceptClassConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_class_concept_id")
    private Concept conceptClassConcept;
    
    public Concept getConceptClassConcept() {
        return this.conceptClassConcept;
    }

    @Id
    @Column(name = "concept_class_id", insertable = false, updatable = false,
            nullable = false)
    private String conceptClassId;
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }

    @Column(name = "concept_class_name", insertable = false, updatable = false,
            nullable = false)
    private String conceptClassName;
    
    public String getConceptClassName() {
        return this.conceptClassName;
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
