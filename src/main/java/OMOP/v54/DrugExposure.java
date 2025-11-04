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
@Table(name = "drug_exposure")
public class DrugExposure {

    @Column(name = "days_supply", updatable = false, nullable = true)
    private Integer daysSupply;
    
    public Optional<Integer> getDaysSupply() {
        if (this.daysSupply != null) {
            return Optional.of(this.daysSupply);
        } else {
            return Optional.empty();
        }
    }

    public void setDaysSupply(final Integer newValue) {
        this.daysSupply = newValue;
    }

    @Column(name = "dose_unit_source_value", updatable = false, nullable = true)
    private String doseUnitSourceValue;
    
    public Optional<String> getDoseUnitSourceValue() {
        if (this.doseUnitSourceValue != null) {
            return Optional.of(this.doseUnitSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDoseUnitSourceValue(final String newValue) {
        this.doseUnitSourceValue = newValue;
    }

    @Column(name = "drug_concept_id", updatable = false, nullable = false)
    private Integer drugConceptId;
    
    public Integer getDrugConceptId() {
        return this.drugConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_concept_id", insertable = false, updatable = false)
    private Concept drugConcept;
    
    public Concept getDrugConcept() {
        return this.drugConcept;
    }

    public void setDrugConcept(final Concept newValue) {
        this.drugConcept = newValue;
        this.drugConceptId = newValue.getConceptId();
    }

    @Column(name = "drug_exposure_end_date", updatable = false,
            nullable = false)
    private ZonedDateTime drugExposureEndDate;
    
    public Date getDrugExposureEndDate() {
        return new Date(this.drugExposureEndDate.toLocalDate());
    }

    public void setDrugExposureEndDate(final Date newValue) {
        this.drugExposureEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "drug_exposure_end_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime drugExposureEndDatetime;
    
    public Optional<DateTime> getDrugExposureEndDatetime() {
        if (this.drugExposureEndDatetime != null) {
            return Optional.of(new DateTime(this.drugExposureEndDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setDrugExposureEndDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.drugExposureEndDatetime = null;
        } else {
            this.drugExposureEndDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_exposure_id", updatable = false, nullable = false)
    private Integer drugExposureId;
    
    public Integer getDrugExposureId() {
        return this.drugExposureId;
    }

    @Column(name = "drug_exposure_start_date", updatable = false,
            nullable = false)
    private ZonedDateTime drugExposureStartDate;
    
    public Date getDrugExposureStartDate() {
        return new Date(this.drugExposureStartDate.toLocalDate());
    }

    public void setDrugExposureStartDate(final Date newValue) {
        this.drugExposureStartDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
    }

    @Column(name = "drug_exposure_start_datetime", updatable = false,
            nullable = true)
    private ZonedDateTime drugExposureStartDatetime;
    
    public Optional<DateTime> getDrugExposureStartDatetime() {
        if (this.drugExposureStartDatetime != null) {
            return Optional.of(new DateTime(this.drugExposureStartDatetime.toOffsetDateTime()));
        } else {
            return Optional.empty();
        }
    }

    public void setDrugExposureStartDatetime(final DateTime newValue) {
        if (newValue == null) {
            this.drugExposureStartDatetime = null;
        } else {
            this.drugExposureStartDatetime = newValue.getDateTime().toZonedDateTime();
        }
    }

    @Column(name = "drug_source_concept_id", updatable = false, nullable = true)
    private Integer drugSourceConceptId;
    
    public Optional<Integer> getDrugSourceConceptId() {
        if (this.drugSourceConceptId != null) {
            return Optional.of(this.drugSourceConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_source_concept_id", insertable = false,
                updatable = false)
    private Concept drugSourceConcept;
    
    public Optional<Concept> getDrugSourceConcept() {
        return Optional.ofNullable(this.drugSourceConcept);
    }

    public void setDrugSourceConcept(final Concept newValue) {
        if (newValue == null) {
            this.drugSourceConcept = null;
            this.drugSourceConceptId = null;
        } else {
            this.drugSourceConcept = newValue;
            this.drugSourceConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "drug_source_value", updatable = false, nullable = true)
    private String drugSourceValue;
    
    public Optional<String> getDrugSourceValue() {
        if (this.drugSourceValue != null) {
            return Optional.of(this.drugSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setDrugSourceValue(final String newValue) {
        this.drugSourceValue = newValue;
    }

    @Column(name = "drug_type_concept_id", updatable = false, nullable = false)
    private Integer drugTypeConceptId;
    
    public Integer getDrugTypeConceptId() {
        return this.drugTypeConceptId;
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_type_concept_id", insertable = false,
                updatable = false)
    private Concept drugTypeConcept;
    
    public Concept getDrugTypeConcept() {
        return this.drugTypeConcept;
    }

    public void setDrugTypeConcept(final Concept newValue) {
        this.drugTypeConcept = newValue;
        this.drugTypeConceptId = newValue.getConceptId();
    }

    @Column(name = "lot_number", updatable = false, nullable = true)
    private String lotNumber;
    
    public Optional<String> getLotNumber() {
        if (this.lotNumber != null) {
            return Optional.of(this.lotNumber);
        } else {
            return Optional.empty();
        }
    }

    public void setLotNumber(final String newValue) {
        this.lotNumber = newValue;
    }

    @Column(name = "person_id", updatable = false, nullable = false)
    private Integer personId;
    
    public Integer getPersonId() {
        return this.personId;
    }

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;
    
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(final Person newValue) {
        this.person = newValue;
        this.personId = newValue.getPersonId();
    }

    @Column(name = "provider_id", updatable = false, nullable = true)
    private Integer providerId;
    
    public Optional<Integer> getProviderId() {
        if (this.providerId != null) {
            return Optional.of(this.providerId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Provider.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", insertable = false, updatable = false)
    private Provider provider;
    
    public Optional<Provider> getProvider() {
        return Optional.ofNullable(this.provider);
    }

    public void setProvider(final Provider newValue) {
        if (newValue == null) {
            this.provider = null;
            this.providerId = null;
        } else {
            this.provider = newValue;
            this.providerId = newValue.getProviderId();
        }
    }

    @Column(name = "quantity", updatable = false, nullable = true)
    private BigDecimal quantity;
    
    public Optional<BigDecimal> getQuantity() {
        if (this.quantity != null) {
            return Optional.of(this.quantity);
        } else {
            return Optional.empty();
        }
    }

    public void setQuantity(final BigDecimal newValue) {
        this.quantity = newValue;
    }

    @Column(name = "refills", updatable = false, nullable = true)
    private Integer refills;
    
    public Optional<Integer> getRefills() {
        if (this.refills != null) {
            return Optional.of(this.refills);
        } else {
            return Optional.empty();
        }
    }

    public void setRefills(final Integer newValue) {
        this.refills = newValue;
    }

    @Column(name = "route_concept_id", updatable = false, nullable = true)
    private Integer routeConceptId;
    
    public Optional<Integer> getRouteConceptId() {
        if (this.routeConceptId != null) {
            return Optional.of(this.routeConceptId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = Concept.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "route_concept_id", insertable = false,
                updatable = false)
    private Concept routeConcept;
    
    public Optional<Concept> getRouteConcept() {
        return Optional.ofNullable(this.routeConcept);
    }

    public void setRouteConcept(final Concept newValue) {
        if (newValue == null) {
            this.routeConcept = null;
            this.routeConceptId = null;
        } else {
            this.routeConcept = newValue;
            this.routeConceptId = newValue.getConceptId();
        }
    }

    @Column(name = "route_source_value", updatable = false, nullable = true)
    private String routeSourceValue;
    
    public Optional<String> getRouteSourceValue() {
        if (this.routeSourceValue != null) {
            return Optional.of(this.routeSourceValue);
        } else {
            return Optional.empty();
        }
    }

    public void setRouteSourceValue(final String newValue) {
        this.routeSourceValue = newValue;
    }

    @Column(name = "sig", updatable = false, nullable = true)
    private String sig;
    
    public Optional<String> getSig() {
        if (this.sig != null) {
            return Optional.of(this.sig);
        } else {
            return Optional.empty();
        }
    }

    public void setSig(final String newValue) {
        this.sig = newValue;
    }

    @Column(name = "stop_reason", updatable = false, nullable = true)
    private String stopReason;
    
    public Optional<String> getStopReason() {
        if (this.stopReason != null) {
            return Optional.of(this.stopReason);
        } else {
            return Optional.empty();
        }
    }

    public void setStopReason(final String newValue) {
        this.stopReason = newValue;
    }

    @Column(name = "verbatim_end_date", updatable = false, nullable = true)
    private ZonedDateTime verbatimEndDate;
    
    public Optional<Date> getVerbatimEndDate() {
        if (this.verbatimEndDate != null) {
            return Optional.of(new Date(this.verbatimEndDate.toLocalDate()));
        } else {
            return Optional.empty();
        }
    }

    public void setVerbatimEndDate(final Date newValue) {
        if (newValue == null) {
            this.verbatimEndDate = null;
        } else {
            this.verbatimEndDate = newValue.getDate().atStartOfDay(ZoneId.systemDefault());
        }
    }

    @Column(name = "visit_detail_id", updatable = false, nullable = true)
    private Integer visitDetailId;
    
    public Optional<Integer> getVisitDetailId() {
        if (this.visitDetailId != null) {
            return Optional.of(this.visitDetailId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_detail_id", insertable = false, updatable = false)
    private VisitDetail visitDetail;
    
    public Optional<VisitDetail> getVisitDetail() {
        return Optional.ofNullable(this.visitDetail);
    }

    public void setVisitDetail(final VisitDetail newValue) {
        if (newValue == null) {
            this.visitDetail = null;
            this.visitDetailId = null;
        } else {
            this.visitDetail = newValue;
            this.visitDetailId = newValue.getVisitDetailId();
        }
    }

    @Column(name = "visit_occurrence_id", updatable = false, nullable = true)
    private Integer visitOccurrenceId;
    
    public Optional<Integer> getVisitOccurrenceId() {
        if (this.visitOccurrenceId != null) {
            return Optional.of(this.visitOccurrenceId);
        } else {
            return Optional.empty();
        }
    }

    @ManyToOne(targetEntity = VisitOccurrence.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_occurrence_id", insertable = false,
                updatable = false)
    private VisitOccurrence visitOccurrence;
    
    public Optional<VisitOccurrence> getVisitOccurrence() {
        return Optional.ofNullable(this.visitOccurrence);
    }

    public void setVisitOccurrence(final VisitOccurrence newValue) {
        if (newValue == null) {
            this.visitOccurrence = null;
            this.visitOccurrenceId = null;
        } else {
            this.visitOccurrence = newValue;
            this.visitOccurrenceId = newValue.getVisitOccurrenceId();
        }
    }

    @Override
    public String toString() {
        final var result = new StringBuilder();
        result.append("DrugExposure{");
        result.append("id=");
        result.append(this.drugExposureId);
        {
            result.append(", concept='");
            result.append(this.getDrugConcept().getConceptName());
            result.append("'");

        }result.append("}");
        return result.toString();
    }


}
