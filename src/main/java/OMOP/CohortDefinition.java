package OMOP;

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

@Entity
@Table(name = "cohort_definition", schema = "cds_cdm")
public class CohortDefinition {

  @Column(name = "subject_concept_id", insertable = false, updatable = false)
  private Integer subjectConceptId;
  
  public Optional<Integer> getSubjectConceptId() {
    return Optional.of(this.subjectConceptId);
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
    return Optional.of(this.cohortDefinitionSyntax);
  }
  
  @Column(name = "definition_type_concept_id", insertable = false, updatable = false)
  private Integer definitionTypeConceptId;
  
  public Optional<Integer> getDefinitionTypeConceptId() {
    return Optional.of(this.definitionTypeConceptId);
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
    return Optional.of(this.cohortDefinitionDescription);
  }
  
  @Column(name = "cohort_definition_name", insertable = false, updatable = false)
  private String cohortDefinitionName;
  
  public Optional<String> getCohortDefinitionName() {
    return Optional.of(this.cohortDefinitionName);
  }
  
  @Column(name = "cohort_definition_id", insertable = false, updatable = false)
  private Integer cohortDefinitionId;
  
  public Optional<Integer> getCohortDefinitionId() {
    return Optional.of(this.cohortDefinitionId);
  }
  
  
}
