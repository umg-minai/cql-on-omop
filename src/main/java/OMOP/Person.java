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
@Table(name = "person", schema = "cds_cdm")
public class Person {

  @Column(name = "ethnicity_source_concept_id", insertable = false, updatable = false)
  private Integer ethnicitySourceConceptId;
  
  public Optional<Integer> getEthnicitySourceConceptId() {
    return Optional.of(this.ethnicitySourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ethnicity_source_concept_id")
  private Concept ethnicitySourceConcept;
  
  public Optional<Concept> getEthnicitySourceConcept() {
    return Optional.of(this.ethnicitySourceConcept);
  }
  @Column(name = "ethnicity_source_value", insertable = false, updatable = false)
  private String ethnicitySourceValue;
  
  public Optional<String> getEthnicitySourceValue() {
    return Optional.of(this.ethnicitySourceValue);
  }
  
  @Column(name = "race_source_concept_id", insertable = false, updatable = false)
  private Integer raceSourceConceptId;
  
  public Optional<Integer> getRaceSourceConceptId() {
    return Optional.of(this.raceSourceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "race_source_concept_id")
  private Concept raceSourceConcept;
  
  public Optional<Concept> getRaceSourceConcept() {
    return Optional.of(this.raceSourceConcept);
  }
  @Column(name = "race_source_value", insertable = false, updatable = false)
  private String raceSourceValue;
  
  public Optional<String> getRaceSourceValue() {
    return Optional.of(this.raceSourceValue);
  }
  
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
  
  @Column(name = "person_source_value", insertable = false, updatable = false)
  private String personSourceValue;
  
  public Optional<String> getPersonSourceValue() {
    return Optional.of(this.personSourceValue);
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
  @Column(name = "location_id", insertable = false, updatable = false)
  private Integer locationId;
  
  public Optional<Integer> getLocationId() {
    return Optional.of(this.locationId);
  }
  
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;
  
  public Optional<Location> getLocation() {
    return Optional.of(this.location);
  }
  @Column(name = "ethnicity_concept_id", insertable = false, updatable = false)
  private Integer ethnicityConceptId;
  
  public Optional<Integer> getEthnicityConceptId() {
    return Optional.of(this.ethnicityConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ethnicity_concept_id")
  private Concept ethnicityConcept;
  
  public Optional<Concept> getEthnicityConcept() {
    return Optional.of(this.ethnicityConcept);
  }
  @Column(name = "race_concept_id", insertable = false, updatable = false)
  private Integer raceConceptId;
  
  public Optional<Integer> getRaceConceptId() {
    return Optional.of(this.raceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "race_concept_id")
  private Concept raceConcept;
  
  public Optional<Concept> getRaceConcept() {
    return Optional.of(this.raceConcept);
  }
  @Column(name = "day_of_birth", insertable = false, updatable = false)
  private Integer dayOfBirth;
  
  public Optional<Integer> getDayOfBirth() {
    return Optional.of(this.dayOfBirth);
  }
  
  @Column(name = "month_of_birth", insertable = false, updatable = false)
  private Integer monthOfBirth;
  
  public Optional<Integer> getMonthOfBirth() {
    return Optional.of(this.monthOfBirth);
  }
  
  @Column(name = "year_of_birth", insertable = false, updatable = false)
  private Integer yearOfBirth;
  
  public Optional<Integer> getYearOfBirth() {
    return Optional.of(this.yearOfBirth);
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
  @Id
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    return Optional.of(this.personId);
  }
  
  @Override
  public String toString() {
    return "Person{id=" + this.personId + "}";
  }
  
  
}
