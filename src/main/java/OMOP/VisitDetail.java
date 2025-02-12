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
@Table(name = "visit_detail", schema = "cds_cdm")
public class VisitDetail {

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
  @Column(name = "parent_visit_detail_id", insertable = false, updatable = false)
  private Integer parentVisitDetailId;
  
  public Optional<Integer> getParentVisitDetailId() {
    return Optional.of(this.parentVisitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_visit_detail_id")
  private VisitDetail parentVisitDetail;
  
  public Optional<VisitDetail> getParentVisitDetail() {
    return Optional.of(this.parentVisitDetail);
  }
  @Column(name = "preceding_visit_detail_id", insertable = false, updatable = false)
  private Integer precedingVisitDetailId;
  
  public Optional<Integer> getPrecedingVisitDetailId() {
    return Optional.of(this.precedingVisitDetailId);
  }
  
  @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "preceding_visit_detail_id")
  private VisitDetail precedingVisitDetail;
  
  public Optional<VisitDetail> getPrecedingVisitDetail() {
    return Optional.of(this.precedingVisitDetail);
  }
  @Column(name = "discharged_to_concept_id", insertable = false, updatable = false)
  private Integer dischargedToConceptId;
  
  public Optional<Integer> getDischargedToConceptId() {
    return Optional.of(this.dischargedToConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "discharged_to_concept_id")
  private Concept dischargedToConcept;
  
  public Optional<Concept> getDischargedToConcept() {
    return Optional.of(this.dischargedToConcept);
  }
  @Column(name = "discharged_to_source_value", insertable = false, updatable = false)
  private String dischargedToSourceValue;
  
  public Optional<String> getDischargedToSourceValue() {
    return Optional.of(this.dischargedToSourceValue);
  }
  
  @Column(name = "admitted_from_source_value", insertable = false, updatable = false)
  private String admittedFromSourceValue;
  
  public Optional<String> getAdmittedFromSourceValue() {
    return Optional.of(this.admittedFromSourceValue);
  }
  
  @Column(name = "admitted_from_concept_id", insertable = false, updatable = false)
  private Integer admittedFromConceptId;
  
  public Optional<Integer> getAdmittedFromConceptId() {
    return Optional.of(this.admittedFromConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "admitted_from_concept_id")
  private Concept admittedFromConcept;
  
  public Optional<Concept> getAdmittedFromConcept() {
    return Optional.of(this.admittedFromConcept);
  }
  @Column(name = "visit_detail_source_concept_id", insertable = false, updatable = false)
  private Integer visitDetailSourceConceptId;
  
  public Optional<Integer> getVisitDetailSourceConceptId() {
    return Optional.of(this.visitDetailSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_source_concept_id")
  private Concept visitDetailSourceConcept;
  
  public Optional<Concept> getVisitDetailSourceConcept() {
    return Optional.of(this.visitDetailSourceConcept);
  }
  @Column(name = "visit_detail_source_value", insertable = false, updatable = false)
  private String visitDetailSourceValue;
  
  public Optional<String> getVisitDetailSourceValue() {
    return Optional.of(this.visitDetailSourceValue);
  }
  
  @Column(name = "care_site_id", insertable = false, updatable = false)
  private Integer careSiteId;
  
  public Optional<Integer> getCareSiteId() {
    return Optional.of(this.careSiteId);
  }
  
  @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "care_site_id")
  private CareSite careSite;
  
  public Optional<CareSite> getCareSite() {
    return Optional.of(this.careSite);
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
  @Column(name = "visit_detail_type_concept_id", insertable = false, updatable = false)
  private Integer visitDetailTypeConceptId;
  
  public Optional<Integer> getVisitDetailTypeConceptId() {
    return Optional.of(this.visitDetailTypeConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_type_concept_id")
  private Concept visitDetailTypeConcept;
  
  public Optional<Concept> getVisitDetailTypeConcept() {
    return Optional.of(this.visitDetailTypeConcept);
  }
  @Column(name = "visit_detail_concept_id", insertable = false, updatable = false)
  private Integer visitDetailConceptId;
  
  public Optional<Integer> getVisitDetailConceptId() {
    return Optional.of(this.visitDetailConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_detail_concept_id")
  private Concept visitDetailConcept;
  
  public Optional<Concept> getVisitDetailConcept() {
    return Optional.of(this.visitDetailConcept);
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
  @Column(name = "visit_detail_id", insertable = false, updatable = false)
  private Integer visitDetailId;
  
  public Optional<Integer> getVisitDetailId() {
    return Optional.of(this.visitDetailId);
  }
  
  @Override
  public String toString() {
    return "VisitDetail{id=" + this.visitDetailId + "}";
  }
  
  
}
