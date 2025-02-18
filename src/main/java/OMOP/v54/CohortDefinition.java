package OMOP.v54;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "cohort_definition", schema = "cds_cdm")
public class CohortDefinition {

  @Column(name = "cohort_initiation_date", insertable = false, updatable = false)
  private ZonedDateTime cohortInitiationDate;
  
  public Optional<Date> getCohortInitiationDate() {
    if (this.cohortInitiationDate != null) {
      return Optional.of(new Date(this.cohortInitiationDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "subject_concept_id", insertable = false, updatable = false)
  private Integer subjectConceptId;
  
  public Optional<Integer> getSubjectConceptId() {
    if (this.subjectConceptId != null) {
      return Optional.of(this.subjectConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_concept_id")
  private Concept subjectConcept;
  
  public Optional<Concept> getSubjectConcept() {
    return Optional.of(this.subjectConcept);
  }
  @Column(name = "cohort_definition_syntax", insertable = false, updatable = false)
  private String cohortDefinitionSyntax;
  
  public Optional<String> getCohortDefinitionSyntax() {
    if (this.cohortDefinitionSyntax != null) {
      return Optional.of(this.cohortDefinitionSyntax);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "definition_type_concept_id", insertable = false, updatable = false)
  private Integer definitionTypeConceptId;
  
  public Optional<Integer> getDefinitionTypeConceptId() {
    if (this.definitionTypeConceptId != null) {
      return Optional.of(this.definitionTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "definition_type_concept_id")
  private Concept definitionTypeConcept;
  
  public Optional<Concept> getDefinitionTypeConcept() {
    return Optional.of(this.definitionTypeConcept);
  }
  @Column(name = "cohort_definition_description", insertable = false, updatable = false)
  private String cohortDefinitionDescription;
  
  public Optional<String> getCohortDefinitionDescription() {
    if (this.cohortDefinitionDescription != null) {
      return Optional.of(this.cohortDefinitionDescription);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cohort_definition_name", insertable = false, updatable = false)
  private String cohortDefinitionName;
  
  public Optional<String> getCohortDefinitionName() {
    if (this.cohortDefinitionName != null) {
      return Optional.of(this.cohortDefinitionName);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cohort_definition_id", insertable = false, updatable = false)
  private Integer cohortDefinitionId;
  
  public Optional<Integer> getCohortDefinitionId() {
    if (this.cohortDefinitionId != null) {
      return Optional.of(this.cohortDefinitionId);
    } else {
      return Optional.empty();
    }
  }
  
  
}
