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
@Table(name = "cohort", schema = "cds_cdm")
public class Cohort {

  @Column(name = "cohort_end_date", insertable = false, updatable = false)
  private ZonedDateTime cohortEndDate;
  
  public Optional<Date> getCohortEndDate() {
    if (this.cohortEndDate != null) {
      return Optional.of(new Date(this.cohortEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "cohort_start_date", insertable = false, updatable = false)
  private ZonedDateTime cohortStartDate;
  
  public Optional<Date> getCohortStartDate() {
    if (this.cohortStartDate != null) {
      return Optional.of(new Date(this.cohortStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "subject_id", insertable = false, updatable = false)
  private Integer subjectId;
  
  public Optional<Integer> getSubjectId() {
    if (this.subjectId != null) {
      return Optional.of(this.subjectId);
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
