package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "visit_occurrence", schema = "cds_cdm")
public class VisitOccurrence {

  @Column(name = "preceding_visit_occurrence_id", insertable = false, updatable = false)
  private Integer precedingVisitOccurrenceId;
  
  public Optional<Integer> getPrecedingVisitOccurrenceId() {
    if (this.precedingVisitOccurrenceId != null) {
      return Optional.of(this.precedingVisitOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "preceding_visit_occurrence_id")
  private VisitOccurrence precedingVisitOccurrence;
  
  public Optional<VisitOccurrence> getPrecedingVisitOccurrence() {
    return Optional.ofNullable(this.precedingVisitOccurrence);
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
  @Column(name = "visit_source_concept_id", insertable = false, updatable = false)
  private Integer visitSourceConceptId;
  
  public Optional<Integer> getVisitSourceConceptId() {
    if (this.visitSourceConceptId != null) {
      return Optional.of(this.visitSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_source_concept_id")
  private Concept visitSourceConcept;
  
  public Optional<Concept> getVisitSourceConcept() {
    return Optional.ofNullable(this.visitSourceConcept);
  }
  @Column(name = "visit_source_value", insertable = false, updatable = false)
  private String visitSourceValue;
  
  public Optional<String> getVisitSourceValue() {
    if (this.visitSourceValue != null) {
      return Optional.of(this.visitSourceValue);
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
  @Column(name = "visit_type_concept_id", insertable = false, updatable = false)
  private Integer visitTypeConceptId;
  
  public Optional<Integer> getVisitTypeConceptId() {
    if (this.visitTypeConceptId != null) {
      return Optional.of(this.visitTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_type_concept_id")
  private Concept visitTypeConcept;
  
  public Optional<Concept> getVisitTypeConcept() {
    return Optional.ofNullable(this.visitTypeConcept);
  }
  @Column(name = "visit_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime visitEndDatetime;
  
  public Optional<DateTime> getVisitEndDatetime() {
    if (this.visitEndDatetime != null) {
      return Optional.of(new DateTime(this.visitEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_end_date", insertable = false, updatable = false)
  private ZonedDateTime visitEndDate;
  
  public Optional<Date> getVisitEndDate() {
    if (this.visitEndDate != null) {
      return Optional.of(new Date(this.visitEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime visitStartDatetime;
  
  public Optional<DateTime> getVisitStartDatetime() {
    if (this.visitStartDatetime != null) {
      return Optional.of(new DateTime(this.visitStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_start_date", insertable = false, updatable = false)
  private ZonedDateTime visitStartDate;
  
  public Optional<Date> getVisitStartDate() {
    if (this.visitStartDate != null) {
      return Optional.of(new Date(this.visitStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_concept_id", insertable = false, updatable = false)
  private Integer visitConceptId;
  
  public Optional<Integer> getVisitConceptId() {
    if (this.visitConceptId != null) {
      return Optional.of(this.visitConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_concept_id")
  private Concept visitConcept;
  
  public Optional<Concept> getVisitConcept() {
    return Optional.ofNullable(this.visitConcept);
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
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    if (this.visitOccurrenceId != null) {
      return Optional.of(this.visitOccurrenceId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("VisitOccurrence{id=").append(this.visitOccurrenceId);
      this.getVisitConcept().ifPresent(concept -> {
        result.append(", concept='")
        .append(concept.getConceptName().get())
        .append("'");
      });
      result.append("}");
      return result.toString();
  }
  
}
