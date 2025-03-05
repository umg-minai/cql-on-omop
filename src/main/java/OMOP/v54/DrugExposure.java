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
@Table(name = "drug_exposure", schema = "cds_cdm")
public class DrugExposure {

  @Column(name = "dose_unit_source_value", insertable = false, updatable = false)
  private String doseUnitSourceValue;
  
  public Optional<String> getDoseUnitSourceValue() {
    if (this.doseUnitSourceValue != null) {
      return Optional.of(this.doseUnitSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "route_source_value", insertable = false, updatable = false)
  private String routeSourceValue;
  
  public Optional<String> getRouteSourceValue() {
    if (this.routeSourceValue != null) {
      return Optional.of(this.routeSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_source_concept_id", insertable = false, updatable = false)
  private Integer drugSourceConceptId;
  
  public Optional<Integer> getDrugSourceConceptId() {
    if (this.drugSourceConceptId != null) {
      return Optional.of(this.drugSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_source_concept_id")
  private Concept drugSourceConcept;
  
  public Optional<Concept> getDrugSourceConcept() {
    return Optional.ofNullable(this.drugSourceConcept);
  }
  @Column(name = "drug_source_value", insertable = false, updatable = false)
  private String drugSourceValue;
  
  public Optional<String> getDrugSourceValue() {
    if (this.drugSourceValue != null) {
      return Optional.of(this.drugSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    if (this.visitDetailId != null) {
      return Optional.of(this.visitDetailId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.ofNullable(this.visitDetail);
  }
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
  @Column(name = "lot_number", insertable = false, updatable = false)
  private String lotNumber;
  
  public Optional<String> getLotNumber() {
    if (this.lotNumber != null) {
      return Optional.of(this.lotNumber);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "route_concept_id", insertable = false, updatable = false)
  private Integer routeConceptId;
  
  public Optional<Integer> getRouteConceptId() {
    if (this.routeConceptId != null) {
      return Optional.of(this.routeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "route_concept_id")
  private Concept routeConcept;
  
  public Optional<Concept> getRouteConcept() {
    return Optional.ofNullable(this.routeConcept);
  }
  @Column(name = "sig", insertable = false, updatable = false)
  private String sig;
  
  public Optional<String> getSig() {
    if (this.sig != null) {
      return Optional.of(this.sig);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "days_supply", insertable = false, updatable = false)
  private Integer daysSupply;
  
  public Optional<Integer> getDaysSupply() {
    if (this.daysSupply != null) {
      return Optional.of(this.daysSupply);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "quantity", insertable = false, updatable = false)
  private Float quantity;
  
  public Optional<Float> getQuantity() {
    if (this.quantity != null) {
      return Optional.of(this.quantity);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "refills", insertable = false, updatable = false)
  private Integer refills;
  
  public Optional<Integer> getRefills() {
    if (this.refills != null) {
      return Optional.of(this.refills);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "stop_reason", insertable = false, updatable = false)
  private String stopReason;
  
  public Optional<String> getStopReason() {
    if (this.stopReason != null) {
      return Optional.of(this.stopReason);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_type_concept_id", insertable = false, updatable = false)
  private Integer drugTypeConceptId;
  
  public Optional<Integer> getDrugTypeConceptId() {
    if (this.drugTypeConceptId != null) {
      return Optional.of(this.drugTypeConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_type_concept_id")
  private Concept drugTypeConcept;
  
  public Optional<Concept> getDrugTypeConcept() {
    return Optional.ofNullable(this.drugTypeConcept);
  }
  @Column(name = "verbatim_end_date", insertable = false, updatable = false)
  private ZonedDateTime verbatimEndDate;
  
  public Optional<Date> getVerbatimEndDate() {
    if (this.verbatimEndDate != null) {
      return Optional.of(new Date(this.verbatimEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_exposure_end_datetime", insertable = false, updatable = false)
  private ZonedDateTime drugExposureEndDatetime;
  
  public Optional<DateTime> getDrugExposureEndDatetime() {
    if (this.drugExposureEndDatetime != null) {
      return Optional.of(new DateTime(this.drugExposureEndDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_exposure_end_date", insertable = false, updatable = false)
  private ZonedDateTime drugExposureEndDate;
  
  public Optional<Date> getDrugExposureEndDate() {
    if (this.drugExposureEndDate != null) {
      return Optional.of(new Date(this.drugExposureEndDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_exposure_start_datetime", insertable = false, updatable = false)
  private ZonedDateTime drugExposureStartDatetime;
  
  public Optional<DateTime> getDrugExposureStartDatetime() {
    if (this.drugExposureStartDatetime != null) {
      return Optional.of(new DateTime(this.drugExposureStartDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_exposure_start_date", insertable = false, updatable = false)
  private ZonedDateTime drugExposureStartDate;
  
  public Optional<Date> getDrugExposureStartDate() {
    if (this.drugExposureStartDate != null) {
      return Optional.of(new Date(this.drugExposureStartDate.toLocalDate()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "drug_concept_id", insertable = false, updatable = false)
  private Integer drugConceptId;
  
  public Optional<Integer> getDrugConceptId() {
    if (this.drugConceptId != null) {
      return Optional.of(this.drugConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.ofNullable(this.drugConcept);
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
  @Column(name = "drug_exposure_id", insertable = false, updatable = false)
  private Integer drugExposureId;
  
  public Optional<Integer> getDrugExposureId() {
    if (this.drugExposureId != null) {
      return Optional.of(this.drugExposureId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("DrugExposure{id=").append(this.drugExposureId);
    result.append("}");
    return result.toString();
}
  
}
