// Name            Sepsis M.1 - Tidal Volume
// Title           Tidal volume for ventilated patients with ARDS
// Version         1.0
// Package Version v1.5.2
// URL             https://www.netzwerk-universitaetsmedizin.de/fhir/codex-celida/guideline/sepsis/recommendation/ventilation-plan-ards-tidal-volume
// Description     Wir empfehlen die Beatmung von Patienten mit ARDS
//                 mit einem VT ≤ 6 ml/kg Standard-Körpergewicht (KG).

//library "sepsis-m.1-tidal-volume" version '1.0.0'

parameter ObservationWindow Interval<DateTime>

parameter ThresholdTolerance Quantity default 0.001 'mL/kg'

// TODO(jmoringe): use the actual URI
codesystem Gender: 'Gender'

// Terminology
// The OMOPSV (OMOP Standard Vocabulary) codesystem is built-in and
// uses the OMOP concept id as code.

code "Gender Female": '8532' from OMOPSV
code "Gender Male":   '8507' from OMOPSV

code "Body Height": '3036277' from OMOPSV

code "Artificial ventilation": '4230167' from OMOPSV

code "Acute respiratory distress syndrome": '4195694' from OMOPSV

code "Tidal Volume on Ventilator": '21490854' from OMOPSV

// Utility functions
// Skip these when reading this library for the first time.

define function PointsToIntervals(Intervals List<Tuple{time DateTime, good Boolean}>,
                                  EndTime   DateTime):
  (
    // Add sentinal after last proper element
    (flatten({Intervals, {null}} as List<List<Tuple{time DateTime, good Boolean}>>)) e
               aggregate r
               starting ({previous: null , intervals: {}}):
      if e is null then // sentinel
        {
          previous:  r.previous, // unused since we are done
          intervals: flatten({
                       r.intervals,
                       { {
                         time: Interval[
                                 start of (r.previous as Tuple{time Interval<DateTime>, good Boolean}).time,
                                 EndTime
                               ],
                         good: (r.previous as Tuple{time Interval<DateTime>, good Boolean}).good
                       } }
                     })
        }
      else if r.previous is null then
        { previous: {time: Interval[e.time, e.time], good: e.good}, intervals: r.intervals }
      else if ((r.previous as Tuple{time Interval<DateTime>, good Boolean}).good = e.good) then
        {
          previous: {
            time: Interval[start of (r.previous as Tuple{time Interval<DateTime>, good Boolean}).time, e.time],
            good: e.good
          },
          intervals: r.intervals
        }
      else
        {
          previous:  {time: Interval[e.time, e.time], good: e.good},
          intervals: if start of (r.previous as Tuple{time Interval<DateTime>, good Boolean}).time < e.time then
                       flatten({
                         r.intervals,
                         { {
                           time: Interval[start of (r.previous as Tuple{time Interval<DateTime>, good Boolean}).time, e.time],
                           good: (r.previous as Tuple{time Interval<DateTime>, good Boolean}).good
                         } }
                       })
                     else
                       r.intervals
        }
  ).intervals

define function IntersectIntervals(Left List<Interval<DateTime>>,
                                   Right List<Interval<DateTime>>):
  from (Left) l, (Right) r
    where (l overlaps r)
    return all l intersect r

// For comparison with execution-engine
define function IntersectIntervalsPlusOne(Left List<Interval<DateTime>>,
                                          Right List<Interval<DateTime>>):
  from (Left) l, (Right) r
    where (l overlaps r)
    return all Interval[Max({start of l, start of r}),
                        Min({end of l, end of r}) + 1 second] // 1 s offset is a hack

// For comparison with execution-engine
define function IntersectIntervalsMaybePlusOne(Left List<Interval<DateTime>>,
                                               Right List<Interval<DateTime>>):
  from (Left) l, (Right) r
    where (l overlaps r)
    return all (if end of l = end of r then
                  Interval[Max({start of l, start of r}), end of r + 1 second] // 1 s offset is a hack
                else
                  l intersect r)

define function IntersectWithObservationWindow(AnInterval Interval<DateTime>):
  if ObservationWindow is null then
    AnInterval
  else
    AnInterval intersect ObservationWindow

define function RestrictToObservationWindow(Intervals List<Interval<DateTime>>):
  if ObservationWindow is null then
    Intervals
  else
    (Intervals) i
      where (start of i before end of ObservationWindow)
            and (end of i after start of ObservationWindow)
      return all IntersectWithObservationWindow(i)

// Convert a list of intervals with DateTime boundaries into a list of
// intervals with Date boundaries such that the returned range covers the
// original range.
define function DayCoverage(Intervals List<Interval<DateTime>>):
  expand (Intervals i return Interval[date from start of i,
                                      date from end of i])
    per day

define function DayCoverageAsDateTime(Intervals List<Interval<DateTime>>):
  (DayCoverage(Intervals)) i
    return all Interval[convert start of i         to DateTime,
                        convert (end of i) + 1 day to DateTime)

context Patient

// Base

define "Visit Occurrences":
  ([VisitOccurrence] o sort by visitStartDatetime) o
    return OMOPHelpers.ToInterval(o)

define function WithinBase(PointInTime DateTime):
  AnyTrue(("Visit Occurrences") o return PointInTime in o)

define function WithinBase(TimeInterval Interval<DateTime>):
  AnyTrue(("Visit Occurrences") o return TimeInterval overlaps o)

// Population

define "Condition Acute respiratory distress syndrome":
  RestrictToObservationWindow(
    // The database has rows in the condition_occurrence table for
    // which the time intervals overlap even though the condition
    // concept is identical
    collapse(
      [ConditionOccurrence: "Acute respiratory distress syndrome"] o
        // The database has a few rows in the condition_occurrence table
        // in which the start date is after the end date.
        where o.conditionStartDatetime <= o.conditionEndDatetime
        return all Interval[o.conditionStartDatetime, o.conditionEndDatetime]
    )
  )

define "Procedure Artificial ventilation":
  RestrictToObservationWindow(
    [ProcedureOccurrence: "Artificial ventilation"] o
      return all Interval[o.procedureDatetime, o.procedureEndDatetime]
  )

define Population:
  IntersectIntervalsMaybePlusOne(
    IntersectIntervalsPlusOne("Condition Acute respiratory distress syndrome",
                              "Procedure Artificial ventilation"),
    "Visit Occurrences")

// Intervention

define function LengthInCentimeters(Length Quantity):
  (convert Length to 'cm').value

define MinimumBodyWeightValue: 0.0001

define function IdealBodyWeight(PatientGender Code,
                                PatientHeight Quantity):
  if PatientHeight is null then
    // If the Body Height Measurement does not have a numeric value,
    // use the MinimumBodyweightvalue instead.
    Quantity{ value: MinimumBodyWeightValue, unit: 'kg' }
  else
    Quantity{
      // Since PatientHeight maybe present but invalid (such as 0) or
      // too small for the formula (such as 100), we ensure that the
      // overall value is at least MinimumBodyWeightValue.
      value: Max({
               case
               when PatientGender ~ "Gender Female" then
                 // PatientHeight can (theoretically) be expressed in
                 // any length unit. We convert to centimeters since
                 // that is what the formula below expects. Then, we
                 // attach the unit 'kg' to the computed result.
                 45.5 + 0.91 * (LengthInCentimeters(PatientHeight) - 152.4)
               when PatientGender ~ "Gender Male" then
                 50.0 + 0.91 * (LengthInCentimeters(PatientHeight) - 152.4)
               else
                 47.75 + 0.91 * (LengthInCentimeters(PatientHeight) - 152.4)
               end,
               MinimumBodyWeightValue
             }),
      unit: 'kg'
    }

define function "Ideal Body Weight for Height"(HeightMeasurement Measurement):
  // Convert the body height measurement in a Quantity so that
  // IdealBodyWeight has access to the value and the unit.
  IdealBodyWeight(Patient.genderConcept,
                  OMOPHelpers.ToQuantity(HeightMeasurement))

// TODO(jmoringe): We should not do repeated retrieves for body height
// measurements. Get them once and cache the result:
// define "Body Height Measurements": [Measurement: "Body Height"]
//   m sort by measurementDatetime asc
// Would be even better to do this only if the patient is in the
// intervention set, but I don't see a way to achieve that.
define "Body Height Measurements": [Measurement: "Body Height"]

define function "Body Height as of"(Time DateTime):
  Last("Body Height Measurements" m
         where m.measurementDatetime <= Time
         return all m
         sort by measurementDatetime asc) // TODO(jmoringe): move sort into previous expression

define function "Ideal Body Weight as of"(Time DateTime):
  "Ideal Body Weight for Height"("Body Height as of"(Time))

define function DivideQuantities(Divident System.Quantity,
                                 Divisor  System.Quantity):
  // Divide the values and do a sketchy "division" of the units as
  // well. Should work for the units involved here but not in general.
  System.Quantity{
    value: Divident.value / Divisor.value,
    unit:  Divident.unit + '/' + Divisor.unit
  }

define "Tidal Volume Measurements":
  [Measurement: "Tidal Volume on Ventilator"] m
    where ((ObservationWindow is null)
           or (m.measurementDatetime in ObservationWindow))
          and WithinBase(m.measurementDatetime)
    return all m
    sort by measurementDatetime

define "Tidal Volume per Ideal Body Weight":
  // Get all tidal volume measurements and, for each such measurement,
  // find the most recent ideal body weight estimation before that
  // tidal volume measurement. The ideal body weight estimation is
  // based on the body height measurement.
  ("Tidal Volume Measurements") m
    let IdealWeight: "Ideal Body Weight as of"(m.measurementDatetime)
      // Never actually null at the moment but we could change the
      // handling of invalid values.
      where not IdealWeight is null
      return all {
        time:  m.measurementDatetime,
        value: DivideQuantities(
          OMOPHelpers.ToQuantity(m),
          IdealWeight
        )
      }

define "Tidal Volume per Ideal Body Weight Intervals":
  IntersectIntervalsMaybePlusOne(
    ((
      PointsToIntervals(
        (("Tidal Volume per Ideal Body Weight") p
          // For the tolerance, see method ValueNumeric.to_sql()
          // in the execution-engine
          return all {
            time: p.time,
            good: (p.value - 6.0 'mL/kg') <= ThresholdTolerance
          }
          sort by time),
        end of Last("Visit Occurrences")
      ) as List<Tuple{time Interval<DateTime>, good Boolean}>
    ) i where i.good return i.time),
    "Visit Occurrences")

define Intervention:
  "Tidal Volume per Ideal Body Weight Intervals"

// Entry point

define Quality:
  IntersectIntervals(Population, Intervention)

define QualityDayCoverage:
  DayCoverage(Quality)
