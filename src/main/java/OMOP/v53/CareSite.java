// This file has been generated from a description of the OMOP CDM v5.3 - do
// not edit
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
@Table(name = "care_site")
public class CareSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "care_site_id", updatable = false, nullable = false)
    private Integer careSiteId;
    
    public Integer getCareSiteId() {
        return this.careSiteId;
    }

    @Column(name = "care_site_name", updatable = false, nullable = true)
    private String careSiteName;
    
    public Optional<String> getCareSiteName() {
        if (this.careSiteName != null) {
            return Optional.of(this.careSiteName);
        } else {
            return Optional.empty();
        }
    }

    public void setCareSiteName(final String newValue) {
        this.careSiteName = newValue;
    }

    @Column(name = "care_site_source_value", updatable = false, nullable = true)
    private String careSiteSourceValue;
    
    public Optional<String> getCareSiteSourceValue() {
        if (this.careSiteSourceValue != null) {
            return Optional.of(this.careSiteSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setCareSiteSourceValue(final String newValue) {
        this.careSiteSourceValue = newValue;
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

    @Column(name = "place_of_service_concept_id", updatable = false,
            nullable = true)
    private Integer placeOfServiceConceptId;
    
    public Optional<Integer> getPlaceOfServiceConceptId() {
        if (this.placeOfServiceConceptId != null) {
            return Optional.of(this.placeOfServiceConceptId);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Warning: This setter can be used to create dangling references to
     * (non-existing) concepts.
     */
    public void setPlaceOfServiceConceptId(final Integer newValue) {
        this.placeOfServiceConceptId = newValue;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "place_of_service_concept_id", insertable = false,
                updatable = false)
    private Concept placeOfServiceConcept;
    
    public Optional<Concept> getPlaceOfServiceConcept() {
        return Optional.ofNullable(this.placeOfServiceConcept);
    }

    public void setPlaceOfServiceConcept(final Concept newValue) {
        if (newValue == null) {
            this.placeOfServiceConcept = null;
            this.placeOfServiceConceptId = null;
        } else {
            this.placeOfServiceConcept = newValue;
            this.placeOfServiceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "place_of_service_source_value", updatable = false,
            nullable = true)
    private String placeOfServiceSourceValue;
    
    public Optional<String> getPlaceOfServiceSourceValue() {
        if (this.placeOfServiceSourceValue != null) {
            return Optional.of(this.placeOfServiceSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setPlaceOfServiceSourceValue(final String newValue) {
        this.placeOfServiceSourceValue = newValue;
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
