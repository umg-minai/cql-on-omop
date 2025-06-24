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
@Table(name = "location", schema = "cds_cdm")
public class Location {

    @Column(name = "address_1", insertable = false, updatable = false,
            nullable = true)
    private String address1;
    
    public Optional<String> getAddress1() {
        if (this.address1 != null) {
            return Optional.of(this.address1);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "address_2", insertable = false, updatable = false,
            nullable = true)
    private String address2;
    
    public Optional<String> getAddress2() {
        if (this.address2 != null) {
            return Optional.of(this.address2);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "city", insertable = false, updatable = false,
            nullable = true)
    private String city;
    
    public Optional<String> getCity() {
        if (this.city != null) {
            return Optional.of(this.city);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "country_concept_id", insertable = false, updatable = false,
            nullable = true)
    private Long countryConceptId;
    
    public Optional<Long> getCountryConceptId() {
        if (this.countryConceptId != null) {
            return Optional.of(this.countryConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_concept_id")
    private Concept countryConcept;
    
    public Optional<Concept> getCountryConcept() {
        return Optional.ofNullable(this.countryConcept);
    }

    @Column(name = "country_source_value", insertable = false,
            updatable = false, nullable = true)
    private String countrySourceValue;
    
    public Optional<String> getCountrySourceValue() {
        if (this.countrySourceValue != null) {
            return Optional.of(this.countrySourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "county", insertable = false, updatable = false,
            nullable = true)
    private String county;
    
    public Optional<String> getCounty() {
        if (this.county != null) {
            return Optional.of(this.county);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "latitude", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal latitude;
    
    public Optional<BigDecimal> getLatitude() {
        if (this.latitude != null) {
            return Optional.of(this.latitude);
        } else {
            return Optional.empty();
        }
    }

    @Id
    @Column(name = "location_id", insertable = false, updatable = false,
            nullable = false)
    private Long locationId;
    
    public Long getLocationId() {
        return this.locationId;
    }

    @Column(name = "location_source_value", insertable = false,
            updatable = false, nullable = true)
    private String locationSourceValue;
    
    public Optional<String> getLocationSourceValue() {
        if (this.locationSourceValue != null) {
            return Optional.of(this.locationSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "longitude", insertable = false, updatable = false,
            nullable = true)
    private BigDecimal longitude;
    
    public Optional<BigDecimal> getLongitude() {
        if (this.longitude != null) {
            return Optional.of(this.longitude);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "state", insertable = false, updatable = false,
            nullable = true)
    private String state;
    
    public Optional<String> getState() {
        if (this.state != null) {
            return Optional.of(this.state);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "zip", insertable = false, updatable = false,
            nullable = true)
    private String zip;
    
    public Optional<String> getZip() {
        if (this.zip != null) {
            return Optional.of(this.zip);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("Location{");
        result.append("id=");
        result.append(this.locationId);
        result.append("}");
        return result.toString();
    }


}
