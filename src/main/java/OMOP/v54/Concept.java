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
@Table(name = "concept", schema = "cds_cdm")
public class Concept {

    @Column(name = "concept_class_id", updatable = false, nullable = false)
    private String conceptClassId;
    
    public String getConceptClassId() {
        return this.conceptClassId;
    }

    @ManyToOne(targetEntity = ConceptClass.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "concept_class_id", insertable = false,
                updatable = false)
    private ConceptClass conceptClass;
    
    public ConceptClass getConceptClass() {
        return this.conceptClass;
    }

    public void setConceptClass(final ConceptClass newValue) {
        this.conceptClass = newValue;
        this.conceptClassId = newValue.getConceptClassId();
    }

    @Column(name = "concept_code", updatable = false, nullable = false)
    private String conceptCode;
    
    public String getConceptCode() {
        return this.conceptCode;
    }

    public void setConceptCode(final String newValue) {
        this.conceptCode = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concept_id", updatable = false, nullable = false)
    private Integer conceptId;
    
    public Integer getConceptId() {
        return this.conceptId;
    }

    @Column(name = "concept_name", updatable = false, nullable = false)
    private String conceptName;
    
    public String getConceptName() {
        return this.conceptName;
    }

    public void setConceptName(final String newValue) {
        this.conceptName = newValue;
    }

    @Column(name = "domain_id", updatable = false, nullable = false)
    private String domainId;
    
    public String getDomainId() {
        return this.domainId;
    }

    @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", insertable = false, updatable = false)
    private Domain domain;
    
    public Domain getDomain() {
        return this.domain;
    }

    public void setDomain(final Domain newValue) {
        this.domain = newValue;
        this.domainId = newValue.getDomainId();
    }

    @Column(name = "invalid_reason", updatable = false, nullable = true)
    private String invalidReason;
    
    public Optional<String> getInvalidReason() {
        if (this.invalidReason != null) {
            return Optional.of(this.invalidReason);
        } else {
            return Optional.empty();
        }
    }

    public void setInvalidReason(final String newValue) {
        this.invalidReason = newValue;
    }

    @Column(name = "standard_concept", updatable = false, nullable = true)
    private String standardConcept;
    
    public Optional<String> getStandardConcept() {
        if (this.standardConcept != null) {
            return Optional.of(this.standardConcept);
        } else {
            return Optional.empty();
        }
    }

    public void setStandardConcept(final String newValue) {
        this.standardConcept = newValue;
    }

    @Column(name = "valid_end_date", updatable = false, nullable = false)
    private ZonedDateTime validEndDate;
    
    public Date getValidEndDate() {
        return new Date(this.validEndDate.toLocalDate());
    }

    public void setValidEndDate(final Date newValue) {
        this.validEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "valid_start_date", updatable = false, nullable = false)
    private ZonedDateTime validStartDate;
    
    public Date getValidStartDate() {
        return new Date(this.validStartDate.toLocalDate());
    }

    public void setValidStartDate(final Date newValue) {
        this.validStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "vocabulary_id", updatable = false, nullable = false)
    private String vocabularyId;
    
    public String getVocabularyId() {
        return this.vocabularyId;
    }

    @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "vocabulary_id", insertable = false, updatable = false)
    private Vocabulary vocabulary;
    
    public Vocabulary getVocabulary() {
        return this.vocabulary;
    }

    public void setVocabulary(final Vocabulary newValue) {
        this.vocabulary = newValue;
        this.vocabularyId = newValue.getVocabularyId();
    }

    @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "cds_cdm", name = "concept_ancestor", joinColumns = {
                   @JoinColumn(name = "descendant_concept_id",
                               insertable = false, updatable = false)

               }, inverseJoinColumns = {
                   @JoinColumn(name = "ancestor_concept_id",
                               insertable = false, updatable = false)

               })
    private Set<Concept> ancestors;

    public Set<Concept> getAncestors() {
        return this.ancestors;
    }

    @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "cds_cdm", name = "concept_ancestor", joinColumns = {
                   @JoinColumn(name = "ancestor_concept_id",
                               insertable = false, updatable = false)

               }, inverseJoinColumns = {
                   @JoinColumn(name = "descendant_concept_id",
                               insertable = false, updatable = false)

               })
    private Set<Concept> descendants;

    public Set<Concept> getDescendants() {
        return this.descendants;
    }

    @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinTable(schema = "cds_cdm", name = "concept_relationship",
               joinColumns = {
                   @JoinColumn(name = "concept_id_1", insertable = false,
                               updatable = false)

               }, inverseJoinColumns = {
                   @JoinColumn(name = "concept_id_2", insertable = false,
                               updatable = false)

               })
    private Set<Concept> relatedConcepts;

    public Set<Concept> getRelatedConcepts() {
        return this.relatedConcepts;
    }

    @ManyToMany(targetEntity = ConceptRelationship.class,
                fetch = FetchType.LAZY)
    @JoinTable(schema = "cds_cdm", name = "concept_relationship",
               joinColumns = {
                   @JoinColumn(name = "concept_id_1", insertable = false,
                               updatable = false)

               }, inverseJoinColumns = {
                   @JoinColumn(name = "concept_id_1", insertable = false,
                               updatable = false)
                   , @JoinColumn(name = "concept_id_2", insertable = false,
                                 updatable = false)
                   , @JoinColumn(name = "relationship_id", insertable = false,
                                 updatable = false)

               })
    private Set<ConceptRelationship> relationships;

    public Set<ConceptRelationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Concept{");
        result.append("id=");
        result.append(this.conceptId);
        result.append(", name='");
        result.append(this.getConceptName());
        result.append("'");
        result.append("}");
        return result.toString();
    }


}
