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
@Table(name = "provider", schema = "cds_cdm")
public class Provider {

  @Column(name = "gender_source_concept_id", insertable = false, updatable = false)
  private Integer genderSourceConceptId;
  
  public Optional<Integer> getGenderSourceConceptId() {
    return Optional.of(this.genderSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_source_concept_id")
  private Concept genderSourceConcept;
  
  public Optional<Concept> getGenderSourceConcept() {
    return Optional.of(this.genderSourceConcept);
  }
  @Column(name = "gender_source_value", insertable = false, updatable = false)
  private String genderSourceValue;
  
  public Optional<String> getGenderSourceValue() {
    return Optional.of(this.genderSourceValue);
  }
  
  @Column(name = "specialty_source_concept_id", insertable = false, updatable = false)
  private Integer specialtySourceConceptId;
  
  public Optional<Integer> getSpecialtySourceConceptId() {
    return Optional.of(this.specialtySourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specialty_source_concept_id")
  private Concept specialtySourceConcept;
  
  public Optional<Concept> getSpecialtySourceConcept() {
    return Optional.of(this.specialtySourceConcept);
  }
  @Column(name = "specialty_source_value", insertable = false, updatable = false)
  private String specialtySourceValue;
  
  public Optional<String> getSpecialtySourceValue() {
    return Optional.of(this.specialtySourceValue);
  }
  
  @Column(name = "provider_source_value", insertable = false, updatable = false)
  private String providerSourceValue;
  
  public Optional<String> getProviderSourceValue() {
    return Optional.of(this.providerSourceValue);
  }
  
  @Column(name = "gender_concept_id", insertable = false, updatable = false)
  private Integer genderConceptId;
  
  public Optional<Integer> getGenderConceptId() {
    return Optional.of(this.genderConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_concept_id")
  private Concept genderConcept;
  
  public Optional<Concept> getGenderConcept() {
    return Optional.of(this.genderConcept);
  }
  @Column(name = "year_of_birth", insertable = false, updatable = false)
  private Integer yearOfBirth;
  
  public Optional<Integer> getYearOfBirth() {
    return Optional.of(this.yearOfBirth);
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
  @Column(name = "specialty_concept_id", insertable = false, updatable = false)
  private Integer specialtyConceptId;
  
  public Optional<Integer> getSpecialtyConceptId() {
    return Optional.of(this.specialtyConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specialty_concept_id")
  private Concept specialtyConcept;
  
  public Optional<Concept> getSpecialtyConcept() {
    return Optional.of(this.specialtyConcept);
  }
  @Column(name = "dea", insertable = false, updatable = false)
  private String dea;
  
  public Optional<String> getDea() {
    return Optional.of(this.dea);
  }
  
  @Column(name = "npi", insertable = false, updatable = false)
  private String npi;
  
  public Optional<String> getNpi() {
    return Optional.of(this.npi);
  }
  
  @Column(name = "provider_name", insertable = false, updatable = false)
  private String providerName;
  
  public Optional<String> getProviderName() {
    return Optional.of(this.providerName);
  }
  
  @Id
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    return Optional.of(this.providerId);
  }
  
  @Override
  public String toString() {
    return "Provider{id=" + this.providerId + "}";
  }
  
  
}
