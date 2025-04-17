package OMOP.v54;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Join;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "concept", schema = "cds_cdm")
public class Concept {

  @Column(name = "invalid_reason", insertable = false, updatable = false)
  private String invalidReason;
  
  public Optional<String> getInvalidReason() {
    if (this.invalidReason != null) {
      return Optional.of(this.invalidReason);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_end_date", insertable = false, updatable = false)
  private ZonedDateTime validEndDate;
  
  public Optional<Date> getValidEndDate() {
    if (this.validEndDate != null) {
      return Optional.of(new Date(this.validEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "valid_start_date", insertable = false, updatable = false)
  private ZonedDateTime validStartDate;
  
  public Optional<Date> getValidStartDate() {
    if (this.validStartDate != null) {
      return Optional.of(new Date(this.validStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "concept_code", insertable = false, updatable = false)
  private String conceptCode;
  
  public Optional<String> getConceptCode() {
    if (this.conceptCode != null) {
      return Optional.of(this.conceptCode);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "standard_concept", insertable = false, updatable = false)
  private String standardConcept;
  
  public Optional<String> getStandardConcept() {
    if (this.standardConcept != null) {
      return Optional.of(this.standardConcept);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "concept_class_id", insertable = false, updatable = false)
  private String conceptClassId;
  
  public Optional<String> getConceptClassId() {
    if (this.conceptClassId != null) {
      return Optional.of(this.conceptClassId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = ConceptClass.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "concept_class_id")
  private ConceptClass conceptClass;
  
  public Optional<ConceptClass> getConceptClass() {
    return Optional.ofNullable(this.conceptClass);
  }
  @Column(name = "vocabulary_id", insertable = false, updatable = false)
  private String vocabularyId;
  
  public Optional<String> getVocabularyId() {
    if (this.vocabularyId != null) {
      return Optional.of(this.vocabularyId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Vocabulary.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "vocabulary_id")
  private Vocabulary vocabulary;
  
  public Optional<Vocabulary> getVocabulary() {
    return Optional.ofNullable(this.vocabulary);
  }
  @Column(name = "domain_id", insertable = false, updatable = false)
  private String domainId;
  
  public Optional<String> getDomainId() {
    if (this.domainId != null) {
      return Optional.of(this.domainId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Domain.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "domain_id")
  private Domain domain;
  
  public Optional<Domain> getDomain() {
    return Optional.ofNullable(this.domain);
  }
  @Column(name = "concept_name", insertable = false, updatable = false)
  private String conceptName;
  
  public Optional<String> getConceptName() {
    if (this.conceptName != null) {
      return Optional.of(this.conceptName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "concept_id", insertable = false, updatable = false)
  private Integer conceptId;
  
  public Optional<Integer> getConceptId() {
    if (this.conceptId != null) {
      return Optional.of(this.conceptId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("Concept{id=").append(this.conceptId);
      result.append(", name='")
        .append(this.getConceptName().get())
        .append("'");
      result.append("}");
      return result.toString();
  }
  @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinTable(name="concept_ancestor", schema="cds_cdm",
    joinColumns = {
      @JoinColumn(name="descendant_concept_id")
    },
    inverseJoinColumns = {
      @JoinColumn(name="ancestor_concept_id")
    }
  )
  private List<Concept> ancestors;
  
  public List<Concept> getAncestors() {
    return this.ancestors;
  }
  
  @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinTable(name="concept_ancestor", schema="cds_cdm",
    joinColumns = {
      @JoinColumn(name="ancestor_concept_id")
    },
    inverseJoinColumns = {
      @JoinColumn(name="descendant_concept_id")
    }
  )
  private List<Concept> descendants;
  
  public List<Concept> getDescendants() {
    return this.descendants;
  }
  
  /*public List<Concept> getRelatedConcepts(final String relationship) {
    return List.of();
  }*/

  @ManyToMany(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinTable(name="concept_relationship", schema="cds_cdm",
          joinColumns = {
                  @JoinColumn(name="concept_id_1")
          },
          inverseJoinColumns = {
                  @JoinColumn(name="concept_id_2")
          }
  )
  private List<Concept> relatedConcepts;

  public List<Concept> getRelatedConcepts() { return this.relatedConcepts; }

  @ManyToMany(targetEntity = ConceptRelationship.class, fetch = FetchType.LAZY)
  @JoinTable(
          schema = "cds_cdm",
          name = "concept_relationship",
          joinColumns = { @JoinColumn(name = "concept_id_1", insertable = false, updatable = false) },
          inverseJoinColumns = {
                  @JoinColumn(name = "concept_id_1", insertable = false, updatable = false),
                  @JoinColumn(name = "concept_id_2", insertable = false, updatable = false) })
  private List<ConceptRelationship> relationships;

  public List<ConceptRelationship> getRelationships() {
    return this.relationships;
  }

  /*public List<Concept> getRelatedConcepts() {
    Hibernate.
    final var relationshipId = "Is a"; // relationship.getRelationshipId().orElseThrow()

    EntityManager e = null;
    final var clazz = Relationship.class;
    final var criteriaBuilder = e.getCriteriaBuilder();
    final var criteriaQuery = criteriaBuilder.createQuery(Concept.class);
    final var root = criteriaQuery.getRoots().stream().findFirst().orElseThrow();

    Join<Concept, Relationship> join = root.join();
    join.on(criteriaBuilder.equal(join.get("relationship_id"), relationshipId));


    final var query = e.createQuery(
            criteriaQuery.select(criteriaQuery.from(Concept.class))
    );
    return query.getResultList();
  }*/

}
