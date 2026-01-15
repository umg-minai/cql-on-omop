// This file has been generated from a description of the OMOP CDM v5.4 - do
// not edit
package OMOP.v54;

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
@Table(name = "location")
public class Location {

    @Column(name = "address_1", updatable = false, nullable = true)
    private String address1;
    
    public Optional<String> getAddress1() {
        if (this.address1 != null) {
            return Optional.of(this.address1);
        } else {
            return Optional.empty();
        }
    }

    public void setAddress1(final String newValue) {
        this.address1 = newValue;
    }

    @Column(name = "address_2", updatable = false, nullable = true)
    private String address2;
    
    public Optional<String> getAddress2() {
        if (this.address2 != null) {
            return Optional.of(this.address2);
        } else {
            return Optional.empty();
        }
    }

    public void setAddress2(final String newValue) {
        this.address2 = newValue;
    }

    @Column(name = "city", updatable = false, nullable = true)
    private String city;
    
    public Optional<String> getCity() {
        if (this.city != null) {
            return Optional.of(this.city);
        } else {
            return Optional.empty();
        }
    }

    public void setCity(final String newValue) {
        this.city = newValue;
    }

    @Column(name = "country_concept_id", updatable = false, nullable = true)
    private Integer countryConceptId;
    
    public Optional<Integer> getCountryConceptId() {
        if (this.countryConceptId != null) {
            return Optional.of(this.countryConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_concept_id", insertable = false,
                updatable = false)
    private Concept countryConcept;
    
    public Optional<Concept> getCountryConcept() {
        return Optional.ofNullable(this.countryConcept);
    }

    public void setCountryConcept(final Concept newValue) {
        if (newValue == null) {
            this.countryConcept = null;
            this.countryConceptId = null;
        } else {
            this.countryConcept = newValue;
            this.countryConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "country_source_value", updatable = false, nullable = true)
    private String countrySourceValue;
    
    public Optional<String> getCountrySourceValue() {
        if (this.countrySourceValue != null) {
            return Optional.of(this.countrySourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setCountrySourceValue(final String newValue) {
        this.countrySourceValue = newValue;
    }

    @Column(name = "county", updatable = false, nullable = true)
    private String county;
    
    public Optional<String> getCounty() {
        if (this.county != null) {
            return Optional.of(this.county);
        } else {
            return Optional.empty();
        }
    }

    public void setCounty(final String newValue) {
        this.county = newValue;
    }

    @Column(name = "latitude", updatable = false, nullable = true)
    private BigDecimal latitude;
    
    public Optional<BigDecimal> getLatitude() {
        if (this.latitude != null) {
            return Optional.of(this.latitude);
        } else {
            return Optional.empty();
        }
    }

    public void setLatitude(final BigDecimal newValue) {
        this.latitude = newValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", updatable = false, nullable = false)
    private Integer locationId;
    
    public Integer getLocationId() {
        return this.locationId;
    }

    @Column(name = "location_source_value", updatable = false, nullable = true)
    private String locationSourceValue;
    
    public Optional<String> getLocationSourceValue() {
        if (this.locationSourceValue != null) {
            return Optional.of(this.locationSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setLocationSourceValue(final String newValue) {
        this.locationSourceValue = newValue;
    }

    @Column(name = "longitude", updatable = false, nullable = true)
    private BigDecimal longitude;
    
    public Optional<BigDecimal> getLongitude() {
        if (this.longitude != null) {
            return Optional.of(this.longitude);
        } else {
            return Optional.empty();
        }
    }

    public void setLongitude(final BigDecimal newValue) {
        this.longitude = newValue;
    }

    @Column(name = "state", updatable = false, nullable = true)
    private String state;
    
    public Optional<String> getState() {
        if (this.state != null) {
            return Optional.of(this.state);
        } else {
            return Optional.empty();
        }
    }

    public void setState(final String newValue) {
        this.state = newValue;
    }

    @Column(name = "zip", updatable = false, nullable = true)
    private String zip;
    
    public Optional<String> getZip() {
        if (this.zip != null) {
            return Optional.of(this.zip);
        } else {
            return Optional.empty();
        }
    }

    public void setZip(final String newValue) {
        this.zip = newValue;
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
