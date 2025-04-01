library Test version '1.0.0'

using "OMOP" version 'v4.5'

// context Patient

define Foo: 1

define Bar: {1,2,3,4,5,6}

define Baz: Bar x where ((x mod 2) = 0) return x*x

define X: {1,2}
define Y: {3,4}
define Z: from X x, Y y return {x, y}

// codesystem "LOINC": 'https://foo'

define Query: [Person] p where (@0001-01-01 + System.Quantity{value: p.yearOfBirth - 1, unit: 'years'}) before @1995-01-01
                         return Tuple{ x: (@0001-01-01 + System.Quantity{value: p.yearOfBirth - 1, unit: 'years'}), id: p.personId, yearOfBirth: p.yearOfBirth }

// define P: Patient


code "Condition COVID19": '840539006' from SNOMED display 'Disease caused by Severe acute respiratory syndrome coronavirus 2 (disorder)'
[VisitOccurrence] v return {s:v.visitStartDatetime, e:v.visitEndDatetime}
define visitIntervals: [VisitOccurrence] v where (v.visitStartDatetime <= v.visitEndDatetime) return { visit: v, interval: Interval[v.visitStartDatetime, v.visitEndDatetime] }
define conditionIntervals: [ConditionOccurrence: "Condition COVID19"] c return { condition: c, interval: Interval[c.conditionStartDatetime, c.conditionEndDatetime] }
from conditionIntervals c, visitIntervals v where (c.interval overlaps v.interval) return {visit: v.visit, condition: c.condition, intersection: v.interval intersect c.interval}