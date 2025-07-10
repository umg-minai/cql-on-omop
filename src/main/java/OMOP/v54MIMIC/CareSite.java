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
@Table(name = "care_site", schema = "cds_cdm")
public class CareSite {

    @Id
    @Column(name = "care_site_id", insertable = false, updatable = false,
            nullable = false)
    private Long careSiteId;
    
    public Long getCareSiteId() {
        return this.careSiteId;
    }

    @Column(name = "care_site_name", insertable = false, updatable = false,
            nullable = true)
    private String careSiteName;
    
    public Optional<String> getCareSiteName() {
        if (this.careSiteName != null) {
            return Optional.of(this.careSiteName);
        } else {
            return Optional.empty();
        }
    }

    @Column(name = "care_site_source_value", insertable = false,
            updatable = false, nullable = true)
    private String careSiteSourceValue;
    
    public Optional<String> getCareSiteSourceValue() {
        if (this.careSiteSourceValue != null) {
            return Optional.of(this.careSiteSourceValue);
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

    @Column(name = "place_of_service_concept_id", insertable = false,
            updatable = false, nullable = true)
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

    @Column(name = "place_of_service_source_value", insertable = false,
            updatable = false, nullable = true)
    private String placeOfServiceSourceValue;
    
    public Optional<String> getPlaceOfServiceSourceValue() {
        if (this.placeOfServiceSourceValue != null) {
            return Optional.of(this.placeOfServiceSourceValue);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("CareSite{");
        result.append("id=");
        result.append(this.careSiteId);
        result.append("}");
        return result.toString();
    }


}
