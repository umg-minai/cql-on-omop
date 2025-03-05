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
@Table(name = "person", schema = "cds_cdm")
public class Person {

  @Column(name = "ethnicity_source_concept_id", insertable = false, updatable = false)
  private Integer ethnicitySourceConceptId;
  
  public Optional<Integer> getEthnicitySourceConceptId() {
    if (this.ethnicitySourceConceptId != null) {
      return Optional.of(this.ethnicitySourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ethnicity_source_concept_id")
  private Concept ethnicitySourceConcept;
  
  public Optional<Concept> getEthnicitySourceConcept() {
    return Optional.ofNullable(this.ethnicitySourceConcept);
  }
  @Column(name = "ethnicity_source_value", insertable = false, updatable = false)
  private String ethnicitySourceValue;
  
  public Optional<String> getEthnicitySourceValue() {
    if (this.ethnicitySourceValue != null) {
      return Optional.of(this.ethnicitySourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "race_source_concept_id", insertable = false, updatable = false)
  private Integer raceSourceConceptId;
  
  public Optional<Integer> getRaceSourceConceptId() {
    if (this.raceSourceConceptId != null) {
      return Optional.of(this.raceSourceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "race_source_concept_id")
  private Concept raceSourceConcept;
  
  public Optional<Concept> getRaceSourceConcept() {
    return Optional.ofNullable(this.raceSourceConcept);
  }
  @Column(name = "race_source_value", insertable = false, updatable = false)
  private String raceSourceValue;
  
  public Optional<String> getRaceSourceValue() {
    if (this.raceSourceValue != null) {
      return Optional.of(this.raceSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
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
  
  @Column(name = "person_source_value", insertable = false, updatable = false)
  private String personSourceValue;
  
  public Optional<String> getPersonSourceValue() {
    if (this.personSourceValue != null) {
      return Optional.of(this.personSourceValue);
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
  @Column(name = "location_id", insertable = false, updatable = false)
  private Integer locationId;
  
  public Optional<Integer> getLocationId() {
    if (this.locationId != null) {
      return Optional.of(this.locationId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;
  
  public Optional<Location> getLocation() {
    return Optional.ofNullable(this.location);
  }
  @Column(name = "ethnicity_concept_id", insertable = false, updatable = false)
  private Integer ethnicityConceptId;
  
  public Optional<Integer> getEthnicityConceptId() {
    if (this.ethnicityConceptId != null) {
      return Optional.of(this.ethnicityConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ethnicity_concept_id")
  private Concept ethnicityConcept;
  
  public Optional<Concept> getEthnicityConcept() {
    return Optional.ofNullable(this.ethnicityConcept);
  }
  @Column(name = "race_concept_id", insertable = false, updatable = false)
  private Integer raceConceptId;
  
  public Optional<Integer> getRaceConceptId() {
    if (this.raceConceptId != null) {
      return Optional.of(this.raceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "race_concept_id")
  private Concept raceConcept;
  
  public Optional<Concept> getRaceConcept() {
    return Optional.ofNullable(this.raceConcept);
  }
  @Column(name = "birth_datetime", insertable = false, updatable = false)
  private ZonedDateTime birthDatetime;
  
  public Optional<DateTime> getBirthDatetime() {
    if (this.birthDatetime != null) {
      return Optional.of(new DateTime(this.birthDatetime.toOffsetDateTime()));
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "day_of_birth", insertable = false, updatable = false)
  private Integer dayOfBirth;
  
  public Optional<Integer> getDayOfBirth() {
    if (this.dayOfBirth != null) {
      return Optional.of(this.dayOfBirth);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "month_of_birth", insertable = false, updatable = false)
  private Integer monthOfBirth;
  
  public Optional<Integer> getMonthOfBirth() {
    if (this.monthOfBirth != null) {
      return Optional.of(this.monthOfBirth);
    } else {
      return Optional.empty();
    }
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
  @Id
  @Column(name = "person_id", insertable = false, updatable = false)
  private Integer personId;
  
  public Optional<Integer> getPersonId() {
    if (this.personId != null) {
      return Optional.of(this.personId);
    } else {
      return Optional.empty();
    }
  }
  
@Override
public String toString() {
    final var result = new StringBuilder();
    result.append("Person{id=").append(this.personId);
    result.append("}");
    return result.toString();
}
  
}
