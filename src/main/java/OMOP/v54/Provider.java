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
@Table(name = "provider", schema = "cds_cdm")
public class Provider {

  @Column(name = "gender_source_concept_id", insertable = false, updatable = false)
  private Integer genderSourceConceptId;
  
  public Optional<Integer> getGenderSourceConceptId() {
    if (this.genderSourceConceptId != null) {
      return Optional.of(this.genderSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_source_concept_id")
  private Concept genderSourceConcept;
  
  public Optional<Concept> getGenderSourceConcept() {
    return Optional.ofNullable(this.genderSourceConcept);
  }
  @Column(name = "gender_source_value", insertable = false, updatable = false)
  private String genderSourceValue;
  
  public Optional<String> getGenderSourceValue() {
    if (this.genderSourceValue != null) {
      return Optional.of(this.genderSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "specialty_source_concept_id", insertable = false, updatable = false)
  private Integer specialtySourceConceptId;
  
  public Optional<Integer> getSpecialtySourceConceptId() {
    if (this.specialtySourceConceptId != null) {
      return Optional.of(this.specialtySourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specialty_source_concept_id")
  private Concept specialtySourceConcept;
  
  public Optional<Concept> getSpecialtySourceConcept() {
    return Optional.ofNullable(this.specialtySourceConcept);
  }
  @Column(name = "specialty_source_value", insertable = false, updatable = false)
  private String specialtySourceValue;
  
  public Optional<String> getSpecialtySourceValue() {
    if (this.specialtySourceValue != null) {
      return Optional.of(this.specialtySourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "provider_source_value", insertable = false, updatable = false)
  private String providerSourceValue;
  
  public Optional<String> getProviderSourceValue() {
    if (this.providerSourceValue != null) {
      return Optional.of(this.providerSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "gender_concept_id", insertable = false, updatable = false)
  private Integer genderConceptId;
  
  public Optional<Integer> getGenderConceptId() {
    if (this.genderConceptId != null) {
      return Optional.of(this.genderConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "gender_concept_id")
  private Concept genderConcept;
  
  public Optional<Concept> getGenderConcept() {
    return Optional.ofNullable(this.genderConcept);
  }
  @Column(name = "year_of_birth", insertable = false, updatable = false)
  private Integer yearOfBirth;
  
  public Optional<Integer> getYearOfBirth() {
    if (this.yearOfBirth != null) {
      return Optional.of(this.yearOfBirth);
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
  @Column(name = "specialty_concept_id", insertable = false, updatable = false)
  private Integer specialtyConceptId;
  
  public Optional<Integer> getSpecialtyConceptId() {
    if (this.specialtyConceptId != null) {
      return Optional.of(this.specialtyConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "specialty_concept_id")
  private Concept specialtyConcept;
  
  public Optional<Concept> getSpecialtyConcept() {
    return Optional.ofNullable(this.specialtyConcept);
  }
  @Column(name = "dea", insertable = false, updatable = false)
  private String dea;
  
  public Optional<String> getDea() {
    if (this.dea != null) {
      return Optional.of(this.dea);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "npi", insertable = false, updatable = false)
  private String npi;
  
  public Optional<String> getNpi() {
    if (this.npi != null) {
      return Optional.of(this.npi);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "provider_name", insertable = false, updatable = false)
  private String providerName;
  
  public Optional<String> getProviderName() {
    if (this.providerName != null) {
      return Optional.of(this.providerName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "provider_id", insertable = false, updatable = false)
  private Integer providerId;
  
  public Optional<Integer> getProviderId() {
    if (this.providerId != null) {
      return Optional.of(this.providerId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Provider{id=").append(this.providerId);
    result.append("}");
    return result.toString();
}
  
}
