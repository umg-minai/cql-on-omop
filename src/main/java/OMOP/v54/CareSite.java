package OMOP.v54;

import java.math.BigDecimal;
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
@Table(name = "care_site", schema = "cds_cdm")
public class CareSite {

  @Column(name = "place_of_service_source_value", insertable = false, updatable = false)
  private String placeOfServiceSourceValue;
  
  public Optional<String> getPlaceOfServiceSourceValue() {
    if (this.placeOfServiceSourceValue != null) {
      return Optional.of(this.placeOfServiceSourceValue);
    } else {
      return Optional.empty();
    }
  }
  
  @Column(name = "care_site_source_value", insertable = false, updatable = false)
  private String careSiteSourceValue;
  
  public Optional<String> getCareSiteSourceValue() {
    if (this.careSiteSourceValue != null) {
      return Optional.of(this.careSiteSourceValue);
    } else {
      return Optional.empty();
    }
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
  @Column(name = "place_of_service_concept_id", insertable = false, updatable = false)
  private Integer placeOfServiceConceptId;
  
  public Optional<Integer> getPlaceOfServiceConceptId() {
    if (this.placeOfServiceConceptId != null) {
      return Optional.of(this.placeOfServiceConceptId);
    } else {
      return Optional.empty();
    }
  }
  
  @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "place_of_service_concept_id")
  private Concept placeOfServiceConcept;
  
  public Optional<Concept> getPlaceOfServiceConcept() {
    return Optional.ofNullable(this.placeOfServiceConcept);
  }
  @Column(name = "care_site_name", insertable = false, updatable = false)
  private String careSiteName;
  
  public Optional<String> getCareSiteName() {
    if (this.careSiteName != null) {
      return Optional.of(this.careSiteName);
    } else {
      return Optional.empty();
    }
  }
  
  @Id
  @Column(name = "care_site_id", insertable = false, updatable = false)
  private Integer careSiteId;
  
  public Optional<Integer> getCareSiteId() {
    if (this.careSiteId != null) {
      return Optional.of(this.careSiteId);
    } else {
      return Optional.empty();
    }
  }
  
  
  @Override
  public String toString() {
      final var result = new StringBuilder();
      result.append("CareSite{id=").append(this.careSiteId);
      result.append("}");
      return result.toString();
  }
  
}
