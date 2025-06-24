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
@Table(name = "domain", schema = "cds_cdm")
public class Domain {

    @Column(name = "domain_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Integer domainConceptId;
    
    public Integer getDomainConceptId() {
        return this.domainConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id")
    private Concept domainConcept;
    
    public Concept getDomainConcept() {
        return this.domainConcept;
    }

    @Id
    @Column(name = "domain_id", insertable = false, updatable = false,
            nullable = false)
    private String domainId;
    
    public String getDomainId() {
        return this.domainId;
    }

    @Column(name = "domain_name", insertable = false, updatable = false,
            nullable = false)
    private String domainName;
    
    public String getDomainName() {
        return this.domainName;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Domain{");
        result.append("id=");
        result.append(this.domainId);
        {
            result.append(", concept='");
            result.append(this.getDomainConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
