package OMOP.v53;

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
@Table(name = "relationship", schema = "cds_cdm")
public class Relationship {

    @Column(name = "defines_ancestry", insertable = false, updatable = false,
            nullable = false)
    private String definesAncestry;
    
    public String getDefinesAncestry() {
        return this.definesAncestry;
    }

    @Column(name = "is_hierarchical", insertable = false, updatable = false,
            nullable = false)
    private String isHierarchical;
    
    public String getIsHierarchical() {
        return this.isHierarchical;
    }

    @Column(name = "relationship_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Integer relationshipConceptId;
    
    public Integer getRelationshipConceptId() {
        return this.relationshipConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_concept_id")
    private Concept relationshipConcept;
    
    public Concept getRelationshipConcept() {
        return this.relationshipConcept;
    }

    @Id
    @Column(name = "relationship_id", insertable = false, updatable = false,
            nullable = false)
    private String relationshipId;
    
    public String getRelationshipId() {
        return this.relationshipId;
    }

    @Column(name = "relationship_name", insertable = false, updatable = false,
            nullable = false)
    private String relationshipName;
    
    public String getRelationshipName() {
        return this.relationshipName;
    }

    @Column(name = "reverse_relationship_id", insertable = false,
            updatable = false, nullable = false)
    private String reverseRelationshipId;
    
    public String getReverseRelationshipId() {
        return this.reverseRelationshipId;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Relationship{");
        result.append("id=");
        result.append(this.relationshipId);
        {
            result.append(", concept='");
            result.append(this.getRelationshipConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
