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
@Table(name = "cohort", schema = "cds_cdm")
public class Cohort {

  @Column(name = "subject_id", insertable = false, updatable = false)
  private Integer subjectId;
  
  public Optional<Integer> getSubjectId() {
    return Optional.of(this.subjectId);
  }
  
  @Column(name = "cohort_definition_id", insertable = false, updatable = false)
  private Integer cohortDefinitionId;
  
  public Optional<Integer> getCohortDefinitionId() {
    return Optional.of(this.cohortDefinitionId);
  }
  
  
}
