library "create-patients"

using "OMOP" version 'v5.4'

parameter BirthMonth Integer default month from Today()

include "OMOPFunctions" called F

define NoMatchingConcept: F.StandardConcept(0)

define NewPersons:
  (expand Interval[1, 10] per 1) id
    return all Person{
      ethnicityConcept: NoMatchingConcept,
      raceConcept: NoMatchingConcept,
      genderConcept: F.StandardConcept(8532),
      dayOfBirth: day from Today(),
      monthOfBirth: BirthMonth,
      yearOfBirth: year from Today(),
      birthDatetime: Now()
    }

define NewConditions:
  (([Person]).take(5)) p return all ConditionOccurrence{
    conditionConcept:       F.SC(0),
    conditionTypeConcept:   F.SC(0),
    person:                 p,
    conditionStartDate:     Today(),
    conditionStartDatetime: Now(),
    conditionEndDate:       Today() + 10 days,
    conditionEndDatetime:   Now() + 10 days
  }
