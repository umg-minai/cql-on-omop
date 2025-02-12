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
@Table(name = "care_site", schema = "cds_cdm")
public class CareSite {

  @Column(name = "place_of_service_source_value", insertable = false, updatable = false)
  private String placeOfServiceSourceValue;
  
  public Optional<String> getPlaceOfServiceSourceValue() {
    return Optional.of(this.placeOfServiceSourceValue);
  }
  
  @Column(name = "care_site_source_value", insertable = false, updatable = false)
  private String careSiteSourceValue;
  
  public Optional<String> getCareSiteSourceValue() {
    return Optional.of(this.careSiteSourceValue);
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
  @Column(name = "place_of_service_concept_id", insertable = false, updatable = false)
  private Integer placeOfServiceConceptId;
  
  public Optional<Integer> getPlaceOfServiceConceptId() {
    return Optional.of(this.placeOfServiceConceptId);
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "place_of_service_concept_id")
  private Concept placeOfServiceConcept;
  
  public Optional<Concept> getPlaceOfServiceConcept() {
    return Optional.of(this.placeOfServiceConcept);
  }
  @Column(name = "care_site_name", insertable = false, updatable = false)
  private String careSiteName;
  
  public Optional<String> getCareSiteName() {
    return Optional.of(this.careSiteName);
  }
  
  @Id
  @Column(name = "care_site_id", insertable = false, updatable = false)
  private Integer careSiteId;
  
  public Optional<Integer> getCareSiteId() {
    return Optional.of(this.careSiteId);
  }
  
  @Override
  public String toString() {
    return "CareSite{id=" + this.careSiteId + "}";
  }
  
  
}
