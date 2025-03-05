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
@Table(name = "visit_detail", schema = "cds_cdm")
public class VisitDetail {

  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    if (this.visitOccurrenceId != null) {
      return Optional.of(this.visitOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.ofNullable(this.visitOccurrence);
  }
  @Column(name = "parent_visit_detail_id", insertable = false, updatable = false)
  private Integer parentVisitDetailId;
  
  public Optional<Integer> getParentVisitDetailId() {
    if (this.parentVisitDetailId != null) {
      return Optional.of(this.parentVisitDetailId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_visit_detail_id")
  private VisitDetail parentVisitDetail;
  
  public Optional<VisitDetail> getParentVisitDetail() {
    return Optional.ofNullable(this.parentVisitDetail);
  }
  @Column(name = "preceding_visit_detail_id", insertable = false, updatable = false)
  private Integer precedingVisitDetailId;
  
  public Optional<Integer> getPrecedingVisitDetailId() {
    if (this.precedingVisitDetailId != null) {
      return Optional.of(this.precedingVisitDetailId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "preceding_visit_detail_id")
  private VisitDetail precedingVisitDetail;
  
  public Optional<VisitDetail> getPrecedingVisitDetail() {
    return Optional.ofNullable(this.precedingVisitDetail);
  }
  @Column(name = "discharged_to_concept_id", insertable = false, updatable = false)
  private Integer dischargedToConceptId;
  
  public Optional<Integer> getDischargedToConceptId() {
    if (this.dischargedToConceptId != null) {
      return Optional.of(this.dischargedToConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "discharged_to_concept_id")
  private Concept dischargedToConcept;
  
  public Optional<Concept> getDischargedToConcept() {
    return Optional.ofNullable(this.dischargedToConcept);
  }
  @Column(name = "discharged_to_source_value", insertable = false, updatable = false)
  private String dischargedToSourceValue;
  
  public Optional<String> getDischargedToSourceValue() {
    if (this.dischargedToSourceValue != null) {
      return Optional.of(this.dischargedToSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "admitted_from_source_value", insertable = false, updatable = false)
  private String admittedFromSourceValue;
  
  public Optional<String> getAdmittedFromSourceValue() {
    if (this.admittedFromSourceValue != null) {
      return Optional.of(this.admittedFromSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "admitted_from_concept_id", insertable = false, updatable = false)
  private Integer admittedFromConceptId;
  
  public Optional<Integer> getAdmittedFromConceptId() {
    if (this.admittedFromConceptId != null) {
      return Optional.of(this.admittedFromConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "admitted_from_concept_id")
  private Concept admittedFromConcept;
  
  public Optional<Concept> getAdmittedFromConcept() {
    return Optional.ofNullable(this.admittedFromConcept);
  }
  @Column(name = "visit_detail_source_concept_id", insertable = false, updatable = false)
  private Integer visitDetailSourceConceptId;
  
  public Optional<Integer> getVisitDetailSourceConceptId() {
    if (this.visitDetailSourceConceptId != null) {
      return Optional.of(this.visitDetailSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_source_concept_id")
  private Concept visitDetailSourceConcept;
  
  public Optional<Concept> getVisitDetailSourceConcept() {
    return Optional.ofNullable(this.visitDetailSourceConcept);
  }
  @Column(name = "visit_detail_source_value", insertable = false, updatable = false)
  private String visitDetailSourceValue;
  
  public Optional<String> getVisitDetailSourceValue() {
    if (this.visitDetailSourceValue != null) {
      return Optional.of(this.visitDetailSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "care_site_id", insertable = false, updatable = false)
  private Integer careSiteId;
  
  public Optional<Integer> getCareSiteId() {
    if (this.careSiteId != null) {
      return Optional.of(this.careSiteId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "care_site_id")
  private CareSite careSite;
  
  public Optional<CareSite> getCareSite() {
    return Optional.ofNullable(this.careSite);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    if (this.providerId != null) {
      return Optional.of(this.providerId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.ofNullable(this.provider);
  }
  @Column(name = "visit_detail_type_concept_id", insertable = false, updatable = false)
  private Integer visitDetailTypeConceptId;
  
  public Optional<Integer> getVisitDetailTypeConceptId() {
    if (this.visitDetailTypeConceptId != null) {
      return Optional.of(this.visitDetailTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_type_concept_id")
  private Concept visitDetailTypeConcept;
  
  public Optional<Concept> getVisitDetailTypeConcept() {
    return Optional.ofNullable(this.visitDetailTypeConcept);
  }
  @Column(name = "visit_detail_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime visitDetailEndDatetime;
  
  public Optional<DateTime> getVisitDetailEndDatetime() {
    if (this.visitDetailEndDatetime != null) {
      return Optional.of(new DateTime(this.visitDetailEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_end_date", insertable = false, updatable = false)
  private ZonedDateTime visitDetailEndDate;
  
  public Optional<Date> getVisitDetailEndDate() {
    if (this.visitDetailEndDate != null) {
      return Optional.of(new Date(this.visitDetailEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime visitDetailStartDatetime;
  
  public Optional<DateTime> getVisitDetailStartDatetime() {
    if (this.visitDetailStartDatetime != null) {
      return Optional.of(new DateTime(this.visitDetailStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_start_date", insertable = false, updatable = false)
  private ZonedDateTime visitDetailStartDate;
  
  public Optional<Date> getVisitDetailStartDate() {
    if (this.visitDetailStartDate != null) {
      return Optional.of(new Date(this.visitDetailStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_concept_id", insertable = false, updatable = false)
  private Integer visitDetailConceptId;
  
  public Optional<Integer> getVisitDetailConceptId() {
    if (this.visitDetailConceptId != null) {
      return Optional.of(this.visitDetailConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_concept_id")
  private Concept visitDetailConcept;
  
  public Optional<Concept> getVisitDetailConcept() {
    return Optional.ofNullable(this.visitDetailConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.ofNullable(this.person);
  }
  @Id
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    if (this.visitDetailId != null) {
      return Optional.of(this.visitDetailId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("VisitDetail{id=").append(this.visitDetailId);
    this.getVisitDetailConcept().ifPresent(concept -> {
      result.append(", concept='")
      .append(concept.getConceptName().get())
      .append("'");
    });
    result.append("}");
    return result.toString();
}
  
}
