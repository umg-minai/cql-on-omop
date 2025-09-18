// java -jar cql-on-omop-….jar <database connection options>                 \
//   -I <path>/cql-on-omop/examples/                                         \
//   -c '[Person]' --result-name Output --sink csv output-csv

library "output-csv"

using "OMOP" version 'v5.4'

include "OMOPHelpers"

context Patient

define Conditions: [ConditionOccurrence]

//
define Output:
  (Conditions) c
    let interval: convert c to Interval<DateTime>
      return all {
        p: Patient.personId,
        s: start of interval,
        e: end of interval,
        l: duration in days between start of interval and end of interval,
        c: c.conditionConcept.conceptId,
        n: c.conditionConcept.conceptName
      }
      sort by s
