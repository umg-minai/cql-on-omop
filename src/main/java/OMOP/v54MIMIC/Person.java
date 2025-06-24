package OMOP.v54MIMIC;

import jakarta.persistence.*;
import org.opencds.cqf.cql.engine.runtime.Date;
import org.opencds.cqf.cql.engine.runtime.DateTime;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "person", schema = "cds_cdm")
public class Person {

    @Column(name = "birth_datetime", insertable = false, updatable = false,
            nullable = true)
    private ZonedDateTime birthDatetime;
    
    public Optional<DateTime> getBirthDatetime() {
        if (this.birthDatetime != null) {
            return Optional.of(new DateTime(this.birthDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "care_site_id", insertable = false, updatable = false,
            nullable = true)
    private Long careSiteId;
    
    public Optional<Long> getCareSiteId() {
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

    @Column(name = "day_of_birth", insertable = false, updatable = false,
            nullable = true)
    private Long dayOfBirth;
    
    public Optional<Long> getDayOfBirth() {
        if (this.dayOfBirth != null) {
            return Optional.of(this.dayOfBirth);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "ethnicity_concept_id", insertable = false,
            updatable = false, nullable = false)
    private Long ethnicityConceptId;
    
    public Long getEthnicityConceptId() {
        return this.ethnicityConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ethnicity_concept_id")
    private Concept ethnicityConcept;
    
    public Concept getEthnicityConcept() {
        return this.ethnicityConcept;
    }

    @Column(name = "ethnicity_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long ethnicitySourceConceptId;
    
    public Optional<Long> getEthnicitySourceConceptId() {
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

    @Column(name = "ethnicity_source_value", insertable = false,
            updatable = false, nullable = true)
    private String ethnicitySourceValue;
    
    public Optional<String> getEthnicitySourceValue() {
        if (this.ethnicitySourceValue != null) {
            return Optional.of(this.ethnicitySourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "gender_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Long genderConceptId;
    
    public Long getGenderConceptId() {
        return this.genderConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_concept_id")
    private Concept genderConcept;
    
    public Concept getGenderConcept() {
        return this.genderConcept;
    }

    @Column(name = "gender_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long genderSourceConceptId;
    
    public Optional<Long> getGenderSourceConceptId() {
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

    @Column(name = "gender_source_value", insertable = false,
            updatable = false, nullable = true)
    private String genderSourceValue;
    
    public Optional<String> getGenderSourceValue() {
        if (this.genderSourceValue != null) {
            return Optional.of(this.genderSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "location_id", insertable = false, updatable = false,
            nullable = true)
    private Long locationId;
    
    public Optional<Long> getLocationId() {
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

    @Column(name = "month_of_birth", insertable = false, updatable = false,
            nullable = true)
    private Long monthOfBirth;
    
    public Optional<Long> getMonthOfBirth() {
        if (this.monthOfBirth != null) {
            return Optional.of(this.monthOfBirth);
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "person_id", insertable = false, updatable = false,
            nullable = false)
    private Long personId;
    
    public Long getPersonId() {
        return this.personId;
    }

    @Column(name = "person_source_value", insertable = false,
            updatable = false, nullable = true)
    private String personSourceValue;
    
    public Optional<String> getPersonSourceValue() {
        if (this.personSourceValue != null) {
            return Optional.of(this.personSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "provider_id", insertable = false, updatable = false,
            nullable = true)
    private Long providerId;
    
    public Optional<Long> getProviderId() {
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

    @Column(name = "race_concept_id", insertable = false, updatable = false,
            nullable = false)
    private Long raceConceptId;
    
    public Long getRaceConceptId() {
        return this.raceConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "race_concept_id")
    private Concept raceConcept;
    
    public Concept getRaceConcept() {
        return this.raceConcept;
    }

    @Column(name = "race_source_concept_id", insertable = false,
            updatable = false, nullable = true)
    private Long raceSourceConceptId;
    
    public Optional<Long> getRaceSourceConceptId() {
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

    @Column(name = "race_source_value", insertable = false, updatable = false,
            nullable = true)
    private String raceSourceValue;
    
    public Optional<String> getRaceSourceValue() {
        if (this.raceSourceValue != null) {
            return Optional.of(this.raceSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "year_of_birth", insertable = false, updatable = false,
            nullable = false)
    private Long yearOfBirth;
    
    public Long getYearOfBirth() {
        return this.yearOfBirth;
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
