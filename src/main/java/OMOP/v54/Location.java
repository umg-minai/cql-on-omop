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
@Table(name = "location", schema = "cds_cdm")
public class Location {

  @Column(name = "longitude", insertable = false, updatable = false)
  private Float longitude;
  
  public Optional<Float> getLongitude() {
    if (this.longitude != null) {
      return Optional.of(this.longitude);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "latitude", insertable = false, updatable = false)
  private Float latitude;
  
  public Optional<Float> getLatitude() {
    if (this.latitude != null) {
      return Optional.of(this.latitude);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "country_source_value", insertable = false, updatable = false)
  private String countrySourceValue;
  
  public Optional<String> getCountrySourceValue() {
    if (this.countrySourceValue != null) {
      return Optional.of(this.countrySourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "country_concept_id", insertable = false, updatable = false)
  private Integer countryConceptId;
  
  public Optional<Integer> getCountryConceptId() {
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
    return Optional.of(this.countryConcept);
  }
  @Column(name = "location_source_value", insertable = false, updatable = false)
  private String locationSourceValue;
  
  public Optional<String> getLocationSourceValue() {
    if (this.locationSourceValue != null) {
      return Optional.of(this.locationSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "county", insertable = false, updatable = false)
  private String county;
  
  public Optional<String> getCounty() {
    if (this.county != null) {
      return Optional.of(this.county);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "zip", insertable = false, updatable = false)
  private String zip;
  
  public Optional<String> getZip() {
    if (this.zip != null) {
      return Optional.of(this.zip);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "state", insertable = false, updatable = false)
  private String state;
  
  public Optional<String> getState() {
    if (this.state != null) {
      return Optional.of(this.state);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "city", insertable = false, updatable = false)
  private String city;
  
  public Optional<String> getCity() {
    if (this.city != null) {
      return Optional.of(this.city);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "address_2", insertable = false, updatable = false)
  private String address2;
  
  public Optional<String> getAddress2() {
    if (this.address2 != null) {
      return Optional.of(this.address2);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "address_1", insertable = false, updatable = false)
  private String address1;
  
  public Optional<String> getAddress1() {
    if (this.address1 != null) {
      return Optional.of(this.address1);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "location_id", insertable = false, updatable = false)
  private Integer locationId;
  
  public Optional<Integer> getLocationId() {
    if (this.locationId != null) {
      return Optional.of(this.locationId);
    } else {
      return Optional.empty();
    }
  }
  
  @Override
  public String toString() {
    return "Location{id=" + this.locationId + "}";
  }
  
  
}
