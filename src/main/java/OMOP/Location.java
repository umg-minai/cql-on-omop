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
@Table(name = "location", schema = "cds_cdm")
public class Location {

  @Column(name = "longitude", insertable = false, updatable = false)
  private Float longitude;
  
  public Optional<Float> getLongitude() {
    return Optional.of(this.longitude);
  }
  
  @Column(name = "latitude", insertable = false, updatable = false)
  private Float latitude;
  
  public Optional<Float> getLatitude() {
    return Optional.of(this.latitude);
  }
  
  @Column(name = "country_source_value", insertable = false, updatable = false)
  private String countrySourceValue;
  
  public Optional<String> getCountrySourceValue() {
    return Optional.of(this.countrySourceValue);
  }
  
  @Column(name = "country_concept_id", insertable = false, updatable = false)
  private Integer countryConceptId;
  
  public Optional<Integer> getCountryConceptId() {
    return Optional.of(this.countryConceptId);
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
    return Optional.of(this.locationSourceValue);
  }
  
  @Column(name = "county", insertable = false, updatable = false)
  private String county;
  
  public Optional<String> getCounty() {
    return Optional.of(this.county);
  }
  
  @Column(name = "zip", insertable = false, updatable = false)
  private String zip;
  
  public Optional<String> getZip() {
    return Optional.of(this.zip);
  }
  
  @Column(name = "state", insertable = false, updatable = false)
  private String state;
  
  public Optional<String> getState() {
    return Optional.of(this.state);
  }
  
  @Column(name = "city", insertable = false, updatable = false)
  private String city;
  
  public Optional<String> getCity() {
    return Optional.of(this.city);
  }
  
  @Column(name = "address_2", insertable = false, updatable = false)
  private String address2;
  
  public Optional<String> getAddress2() {
    return Optional.of(this.address2);
  }
  
  @Column(name = "address_1", insertable = false, updatable = false)
  private String address1;
  
  public Optional<String> getAddress1() {
    return Optional.of(this.address1);
  }
  
  @Id
  @Column(name = "location_id", insertable = false, updatable = false)
  private Integer locationId;
  
  public Optional<Integer> getLocationId() {
    return Optional.of(this.locationId);
  }
  
  @Override
  public String toString() {
    return "Location{id=" + this.locationId + "}";
  }
  
  
}
