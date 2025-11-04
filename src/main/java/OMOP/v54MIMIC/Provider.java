package OMOP.v54MIMIC;

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
@Table(name = "provider", schema = "cds_cdm")
public class Provider {

    @Column(name = "care_site_id", updatable = false, nullable = true)
    private Long careSiteId;
    
    public Optional<Long> getCareSiteId() {
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

    @Column(name = "dea", updatable = false, nullable = true)
    private String dea;
    
    public Optional<String> getDea() {
        if (this.dea != null) {
            return Optional.of(this.dea);
        } else {
            return Optional.empty();
        }
    }

    public void setDea(final String newValue) {
        this.dea = newValue;
    }

    @Column(name = "gender_concept_id", updatable = false, nullable = true)
    private Integer genderConceptId;
    
    public Optional<Integer> getGenderConceptId() {
        if (this.genderConceptId != null) {
            return Optional.of(this.genderConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_concept_id", insertable = false,
                updatable = false)
    private Concept genderConcept;
    
    public Optional<Concept> getGenderConcept() {
        return Optional.ofNullable(this.genderConcept);
    }

    public void setGenderConcept(final Concept newValue) {
        if (newValue == null) {
            this.genderConcept = null;
            this.genderConceptId = null;
        } else {
            this.genderConcept = newValue;
            this.genderConceptId = newValue.getConceptId();
        }
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

    @Column(name = "npi", updatable = false, nullable = true)
    private String npi;
    
    public Optional<String> getNpi() {
        if (this.npi != null) {
            return Optional.of(this.npi);
        } else {
            return Optional.empty();
        }
    }

    public void setNpi(final String newValue) {
        this.npi = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id", updatable = false, nullable = false)
    private Long providerId;
    
    public Long getProviderId() {
        return this.providerId;
    }

    @Column(name = "provider_name", updatable = false, nullable = true)
    private String providerName;
    
    public Optional<String> getProviderName() {
        if (this.providerName != null) {
            return Optional.of(this.providerName);
        } else {
            return Optional.empty();
        }
    }

    public void setProviderName(final String newValue) {
        this.providerName = newValue;
    }

    @Column(name = "provider_source_value", updatable = false, nullable = true)
    private String providerSourceValue;
    
    public Optional<String> getProviderSourceValue() {
        if (this.providerSourceValue != null) {
            return Optional.of(this.providerSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setProviderSourceValue(final String newValue) {
        this.providerSourceValue = newValue;
    }

    @Column(name = "specialty_concept_id", updatable = false, nullable = true)
    private Integer specialtyConceptId;
    
    public Optional<Integer> getSpecialtyConceptId() {
        if (this.specialtyConceptId != null) {
            return Optional.of(this.specialtyConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_concept_id", insertable = false,
                updatable = false)
    private Concept specialtyConcept;
    
    public Optional<Concept> getSpecialtyConcept() {
        return Optional.ofNullable(this.specialtyConcept);
    }

    public void setSpecialtyConcept(final Concept newValue) {
        if (newValue == null) {
            this.specialtyConcept = null;
            this.specialtyConceptId = null;
        } else {
            this.specialtyConcept = newValue;
            this.specialtyConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "specialty_source_concept_id", updatable = false,
            nullable = true)
    private Integer specialtySourceConceptId;
    
    public Optional<Integer> getSpecialtySourceConceptId() {
        if (this.specialtySourceConceptId != null) {
            return Optional.of(this.specialtySourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_source_concept_id", insertable = false,
                updatable = false)
    private Concept specialtySourceConcept;
    
    public Optional<Concept> getSpecialtySourceConcept() {
        return Optional.ofNullable(this.specialtySourceConcept);
    }

    public void setSpecialtySourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.specialtySourceConcept = null;
            this.specialtySourceConceptId = null;
        } else {
            this.specialtySourceConcept = newValue;
            this.specialtySourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "specialty_source_value", updatable = false, nullable = true)
    private String specialtySourceValue;
    
    public Optional<String> getSpecialtySourceValue() {
        if (this.specialtySourceValue != null) {
            return Optional.of(this.specialtySourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setSpecialtySourceValue(final String newValue) {
        this.specialtySourceValue = newValue;
    }

    @Column(name = "year_of_birth", updatable = false, nullable = true)
    private Integer yearOfBirth;
    
    public Optional<Integer> getYearOfBirth() {
        if (this.yearOfBirth != null) {
            return Optional.of(this.yearOfBirth);
        } else {
            return Optional.empty();
        }
    }

    public void setYearOfBirth(final Integer newValue) {
        this.yearOfBirth = newValue;
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Provider{");
        result.append("id=");
        result.append(this.providerId);
        result.append("}");
        return result.toString();
    }


}
