// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
package OMOP.v54;

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
@Table(name = "domain")
public class Domain {

    @Column(name = "domain_concept_id", updatable = false, nullable = false)
    private Integer domainConceptId;
    
    public Integer getDomainConceptId() {
        return this.domainConceptId;
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setDomainConceptId(final Integer newValue) {
        this.domainConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_concept_id", insertable = false,
                updatable = false)
    private Concept domainConcept;
    
    public Concept getDomainConcept() {
        return this.domainConcept;
    }

    public void setDomainConcept(final Concept newValue) {
        this.domainConcept = newValue;
        // We allow explicitly settings the (ostensibly required) field to null
        // and the associated foreign key to 0 so that users can create broken
        // references when they absolutely must.
        this.domainConceptId = (newValue != null) ? newValue.getConceptId() : 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id", updatable = false, nullable = false)
    private String domainId;
    
    public String getDomainId() {
        return this.domainId;
    }

    @Column(name = "domain_name", updatable = false, nullable = false)
    private String domainName;
    
    public String getDomainName() {
        return this.domainName;
    }

    public void setDomainName(final String newValue) {
        this.domainName = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Domain{");
        result.append("id=");
        result.append(this.domainId);
        {
            result.append(", concept='");
            if (this.getDomainConcept() != null) {
                result.append(this.getDomainConcept().getConceptName());

            } else {
                result.append("«broken relation»");

            }result.append("'");

        }
        result.append("}");
        return result.toString();
    }


}
