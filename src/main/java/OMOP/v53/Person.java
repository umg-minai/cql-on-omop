package OMOP.v53;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Column(name = "birth_datetime", updatable = false, nullable = true)
    private ZonedDateTime birthDatetime;
    
    public Optional<DateTime> getBirthDatetime() {
        if (this.birthDatetime != null) {
            return Optional.of(new DateTime(this.birthDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setBirthDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.birthDatetime = null;
        } else {
            this.birthDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "care_site_id", updatable = false, nullable = true)
    private Integer careSiteId;
    
    public Optional<Integer> getCareSiteId() {
        if (this.careSiteId != null) {
            return Optional.of(this.careSiteId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = CareSite.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "care_site_id", insertable = false, updatable = false)
    private CareSite careSite;
    
    public Optional<CareSite> getCareSite() {
        return Optional.ofNullable(this.careSite);
    }

    public void setCareSite(final CareSite newValue) {
        if (newValue == null) {
            this.careSite = null;
            this.careSiteId = null;
        } else {
            this.careSite = newValue;
            this.careSiteId = newValue.getCareSiteId();
        }
    }

    @Column(name = "day_of_birth", updatable = false, nullable = true)
    private Integer dayOfBirth;
    
    public Optional<Integer> getDayOfBirth() {
        if (this.dayOfBirth != null) {
            return Optional.of(this.dayOfBirth);
        } else {
            return Optional.empty();
        }
    }

    public void setDayOfBirth(final Integer newValue) {
        this.dayOfBirth = newValue;
    }

    @Column(name = "ethnicity_concept_id", updatable = false, nullable = false)
    private Integer ethnicityConceptId;
    
    public Integer getEthnicityConceptId() {
        return this.ethnicityConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_concept_id", insertable = false,
                updatable = false)
    private Concept ethnicityConcept;
    
    public Concept getEthnicityConcept() {
        return this.ethnicityConcept;
    }

    public void setEthnicityConcept(final Concept newValue) {
        this.ethnicityConcept = newValue;
        this.ethnicityConceptId = newValue.getConceptId();
    }

    @Column(name = "ethnicity_source_concept_id", updatable = false,
            nullable = true)
    private Integer ethnicitySourceConceptId;
    
    public Optional<Integer> getEthnicitySourceConceptId() {
        if (this.ethnicitySourceConceptId != null) {
            return Optional.of(this.ethnicitySourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_source_concept_id", insertable = false,
                updatable = false)
    private Concept ethnicitySourceConcept;
    
    public Optional<Concept> getEthnicitySourceConcept() {
        return Optional.ofNullable(this.ethnicitySourceConcept);
    }

    public void setEthnicitySourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.ethnicitySourceConcept = null;
            this.ethnicitySourceConceptId = null;
        } else {
            this.ethnicitySourceConcept = newValue;
            this.ethnicitySourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "ethnicity_source_value", updatable = false, nullable = true)
    private String ethnicitySourceValue;
    
    public Optional<String> getEthnicitySourceValue() {
        if (this.ethnicitySourceValue != null) {
            return Optional.of(this.ethnicitySourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setEthnicitySourceValue(final String newValue) {
        this.ethnicitySourceValue = newValue;
    }

    @Column(name = "gender_concept_id", updatable = false, nullable = false)
    private Integer genderConceptId;
    
    public Integer getGenderConceptId() {
        return this.genderConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_concept_id", insertable = false,
                updatable = false)
    private Concept genderConcept;
    
    public Concept getGenderConcept() {
        return this.genderConcept;
    }

    public void setGenderConcept(final Concept newValue) {
        this.genderConcept = newValue;
        this.genderConceptId = newValue.getConceptId();
    }

    @Column(name = "gender_source_concept_id", updatable = false,
            nullable = true)
    private Integer genderSourceConceptId;
    
    public Optional<Integer> getGenderSourceConceptId() {
        if (this.genderSourceConceptId != null) {
            return Optional.of(this.genderSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_source_concept_id", insertable = false,
                updatable = false)
    private Concept genderSourceConcept;
    
    public Optional<Concept> getGenderSourceConcept() {
        return Optional.ofNullable(this.genderSourceConcept);
    }

    public void setGenderSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.genderSourceConcept = null;
            this.genderSourceConceptId = null;
        } else {
            this.genderSourceConcept = newValue;
            this.genderSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "gender_source_value", updatable = false, nullable = true)
    private String genderSourceValue;
    
    public Optional<String> getGenderSourceValue() {
        if (this.genderSourceValue != null) {
            return Optional.of(this.genderSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setGenderSourceValue(final String newValue) {
        this.genderSourceValue = newValue;
    }

    @Column(name = "location_id", updatable = false, nullable = true)
    private Integer locationId;
    
    public Optional<Integer> getLocationId() {
        if (this.locationId != null) {
            return Optional.of(this.locationId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Location.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;
    
    public Optional<Location> getLocation() {
        return Optional.ofNullable(this.location);
    }

    public void setLocation(final Location newValue) {
        if (newValue == null) {
            this.location = null;
            this.locationId = null;
        } else {
            this.location = newValue;
            this.locationId = newValue.getLocationId();
        }
    }

    @Column(name = "month_of_birth", updatable = false, nullable = true)
    private Integer monthOfBirth;
    
    public Optional<Integer> getMonthOfBirth() {
        if (this.monthOfBirth != null) {
            return Optional.of(this.monthOfBirth);
        } else {
            return Optional.empty();
        }
    }

    public void setMonthOfBirth(final Integer newValue) {
        this.monthOfBirth = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @Column(name = "person_source_value", updatable = false, nullable = true)
    private String personSourceValue;
    
    public Optional<String> getPersonSourceValue() {
        if (this.personSourceValue != null) {
            return Optional.of(this.personSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setPersonSourceValue(final String newValue) {
        this.personSourceValue = newValue;
    }

    @Column(name = "provider_id", updatable = false, nullable = true)
    private Integer providerId;
    
    public Optional<Integer> getProviderId() {
        if (this.providerId != null) {
            return Optional.of(this.providerId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", insertable = false, updatable = false)
    private Provider provider;
    
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(this.provider);
    }

    public void setProvider(final Provider newValue) {
        if (newValue == null) {
            this.provider = null;
            this.providerId = null;
        } else {
            this.provider = newValue;
            this.providerId = newValue.getProviderId();
        }
    }

    @Column(name = "race_concept_id", updatable = false, nullable = false)
    private Integer raceConceptId;
    
    public Integer getRaceConceptId() {
        return this.raceConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "race_concept_id", insertable = false, updatable = false)
    private Concept raceConcept;
    
    public Concept getRaceConcept() {
        return this.raceConcept;
    }

    public void setRaceConcept(final Concept newValue) {
        this.raceConcept = newValue;
        this.raceConceptId = newValue.getConceptId();
    }

    @Column(name = "race_source_concept_id", updatable = false, nullable = true)
    private Integer raceSourceConceptId;
    
    public Optional<Integer> getRaceSourceConceptId() {
        if (this.raceSourceConceptId != null) {
            return Optional.of(this.raceSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "race_source_concept_id", insertable = false,
                updatable = false)
    private Concept raceSourceConcept;
    
    public Optional<Concept> getRaceSourceConcept() {
        return Optional.ofNullable(this.raceSourceConcept);
    }

    public void setRaceSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.raceSourceConcept = null;
            this.raceSourceConceptId = null;
        } else {
            this.raceSourceConcept = newValue;
            this.raceSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "race_source_value", updatable = false, nullable = true)
    private String raceSourceValue;
    
    public Optional<String> getRaceSourceValue() {
        if (this.raceSourceValue != null) {
            return Optional.of(this.raceSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setRaceSourceValue(final String newValue) {
        this.raceSourceValue = newValue;
    }

    @Column(name = "year_of_birth", updatable = false, nullable = false)
    private Integer yearOfBirth;
    
    public Integer getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(final Integer newValue) {
        this.yearOfBirth = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Person{");
        result.append("id=");
        result.append(this.personId);
        result.append("}");
        return result.toString();
    }


}
