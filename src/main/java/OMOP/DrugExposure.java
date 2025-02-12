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
@Table(name = "drug_exposure", schema = "cds_cdm")
public class DrugExposure {

  @Column(name = "dose_unit_source_value", insertable = false, updatable = false)
  private String doseUnitSourceValue;
  
  public Optional<String> getDoseUnitSourceValue() {
    return Optional.of(this.doseUnitSourceValue);
  }
  
  @Column(name = "route_source_value", insertable = false, updatable = false)
  private String routeSourceValue;
  
  public Optional<String> getRouteSourceValue() {
    return Optional.of(this.routeSourceValue);
  }
  
  @Column(name = "drug_source_concept_id", insertable = false, updatable = false)
  private Integer drugSourceConceptId;
  
  public Optional<Integer> getDrugSourceConceptId() {
    return Optional.of(this.drugSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_source_concept_id")
  private Concept drugSourceConcept;
  
  public Optional<Concept> getDrugSourceConcept() {
    return Optional.of(this.drugSourceConcept);
  }
  @Column(name = "drug_source_value", insertable = false, updatable = false)
  private String drugSourceValue;
  
  public Optional<String> getDrugSourceValue() {
    return Optional.of(this.drugSourceValue);
  }
  
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    return Optional.of(this.visitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_id")
  private VisitDetail visitDetail;
  
  public Optional<VisitDetail> getVisitDetail() {
    return Optional.of(this.visitDetail);
  }
  @Column(name = "visit_occurrence_id", insertable = false, updatable = false)
  private Integer visitOccurrenceId;
  
  public Optional<Integer> getVisitOccurrenceId() {
    return Optional.of(this.visitOccurrenceId);
  }
  
  @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_occurrence_id")
  private VisitOccurrence visitOccurrence;
  
  public Optional<VisitOccurrence> getVisitOccurrence() {
    return Optional.of(this.visitOccurrence);
  }
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    return Optional.of(this.providerId);
  }
  
  @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "provider_id")
  private Provider provider;
  
  public Optional<Provider> getProvider() {
    return Optional.of(this.provider);
  }
  @Column(name = "lot_number", insertable = false, updatable = false)
  private String lotNumber;
  
  public Optional<String> getLotNumber() {
    return Optional.of(this.lotNumber);
  }
  
  @Column(name = "route_concept_id", insertable = false, updatable = false)
  private Integer routeConceptId;
  
  public Optional<Integer> getRouteConceptId() {
    return Optional.of(this.routeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "route_concept_id")
  private Concept routeConcept;
  
  public Optional<Concept> getRouteConcept() {
    return Optional.of(this.routeConcept);
  }
  @Column(name = "sig", insertable = false, updatable = false)
  private String sig;
  
  public Optional<String> getSig() {
    return Optional.of(this.sig);
  }
  
  @Column(name = "days_supply", insertable = false, updatable = false)
  private Integer daysSupply;
  
  public Optional<Integer> getDaysSupply() {
    return Optional.of(this.daysSupply);
  }
  
  @Column(name = "quantity", insertable = false, updatable = false)
  private Float quantity;
  
  public Optional<Float> getQuantity() {
    return Optional.of(this.quantity);
  }
  
  @Column(name = "refills", insertable = false, updatable = false)
  private Integer refills;
  
  public Optional<Integer> getRefills() {
    return Optional.of(this.refills);
  }
  
  @Column(name = "stop_reason", insertable = false, updatable = false)
  private String stopReason;
  
  public Optional<String> getStopReason() {
    return Optional.of(this.stopReason);
  }
  
  @Column(name = "drug_type_concept_id", insertable = false, updatable = false)
  private Integer drugTypeConceptId;
  
  public Optional<Integer> getDrugTypeConceptId() {
    return Optional.of(this.drugTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_type_concept_id")
  private Concept drugTypeConcept;
  
  public Optional<Concept> getDrugTypeConcept() {
    return Optional.of(this.drugTypeConcept);
  }
  @Column(name = "drug_concept_id", insertable = false, updatable = false)
  private Integer drugConceptId;
  
  public Optional<Integer> getDrugConceptId() {
    return Optional.of(this.drugConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "drug_concept_id")
  private Concept drugConcept;
  
  public Optional<Concept> getDrugConcept() {
    return Optional.of(this.drugConcept);
  }
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    return Optional.of(this.personId);
  }
  
  @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;
  
  public Optional<Person> getPerson() {
    return Optional.of(this.person);
  }
  @Id
  @Column(name = "drug_exposure_id", insertable = false, updatable = false)
  private Integer drugExposureId;
  
  public Optional<Integer> getDrugExposureId() {
    return Optional.of(this.drugExposureId);
  }
  
  @Override
  public String toString() {
    return "DrugExposure{id=" + this.drugExposureId + "}";
  }
  
  
}
