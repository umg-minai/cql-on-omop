[Measurement]

[Measurement] m return m.measurementConcept.conceptName

[Measurement] m where m.measurementConcept.conceptName = 'Body weight'

First([Measurement] m where m.measurementConcept.conceptName = 'Body weight' return m.measurementConcept)

code "Body weight": '4099154' from OMOPSV

[Measurement: "Body weight"]

[Measurement: "Body weight"] m return {p:m.person, v:m.valueAsNumber, u:m.unitConcept.conceptName}

[Measurement: "Body weight"] m return {p:m.person, v: Quantity{value: m.valueAsNumber, unit:m.unitConcept.conceptCode} }

[Measurement: "Body weight"] m return {p:m.person, v: OMOPHelpers.ToQuantity(m)}

[Measurement: "Body weight"] m return {p:m.person, v: convert OMOPHelpers.ToQuantity(m) to 'g' }

[Person]

,focus First([Person])

context Patient

Patient

[Measurement: "Body weight"] m return {p:m.person, v: convert OMOPHelpers.ToQuantity(m) to 'g' }

,focus [Person]

[Measurement: "Body weight"] m return {p:m.person, v: convert OMOPHelpers.ToQuantity(m) to 'g' }

First([ConditionOccurrence])

Count([ConditionOccurrence])
