// java -jar cql-on-omop-….jar <database connection options>                 \
//   -I <path>/cql-on-omop/examples/                                         \
//   --result-name 'Output.*' --sink histogram output-histogram

library "output-histogram"

using "OMOP" version 'v5.4'

include "OMOPHelpers"

define Conditions: [ConditionOccurrence]

define "Output-Conditions":
  Flatten(
    (Conditions) c
      let DaysAsDateTime: expand (convert c to Interval<DateTime>) per day,
          DaysAsDate:     (DaysAsDateTime) d return all convert d to Date
      return all DaysAsDate
  )

define Procedures:
  [ProcedureOccurrence] p
    // Filter out invalid entries in database.
    where p.procedureDatetime <= p.procedureEndDatetime
    return all p

define "Output-Procedures":
  Flatten(
    (Procedures) p
      let DaysAsDateTime: expand (convert p to Interval<DateTime>) per day,
          DaysAsDate:     (DaysAsDateTime) d return all convert d to Date
      return all DaysAsDate
  )
