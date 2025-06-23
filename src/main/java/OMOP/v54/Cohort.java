package OMOP.v54;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.DateTime;
import org.opencds.cqf.cql.engine.runtime.Date;

@Entity
@Table(name = "cohort", schema = "cds_cdm")
public class Cohort {

    @Column(name = "cohort_definition_id", insertable = false,
            updatable = false, nullable = false)
    private Integer cohortDefinitionId;
    
    public Integer getCohortDefinitionId() {
        return this.cohortDefinitionId;
    }

    @Column(name = "cohort_end_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime cohortEndDate;
    
    public Date getCohortEndDate() {
        return new Date(this.cohortEndDate.toLocalDate());
    }

    @Column(name = "cohort_start_date", insertable = false, updatable = false,
            nullable = false)
    private ZonedDateTime cohortStartDate;
    
    public Date getCohortStartDate() {
        return new Date(this.cohortStartDate.toLocalDate());
    }

    @Column(name = "subject_id", insertable = false, updatable = false,
            nullable = false)
    private Integer subjectId;
    
    public Integer getSubjectId() {
        return this.subjectId;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Cohort{");
        result.append("}");
        return result.toString();
    }


}
