library OMOPHelpers version '1.0'

using OMOP version 'v5.4'

// Conversion to System.Code

define function ToCode(OMOPObject OMOP.Concept):
  System.Code{
    code:    ToString(OMOPObject.conceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.vocabularyId,
  //  display: OMOPObject.conceptName
  }
  
define function ToCode(OMOPObject OMOP.ConceptClass):
  System.Code{
    code:    ToString(OMOPObject.conceptClassConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.conceptClassConcept.vocabularyId,
  //  display: OMOPObject.conceptClassConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.ConditionOccurrence):
  System.Code{
    code:    ToString(OMOPObject.conditionConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.conditionConcept.vocabularyId,
  //  display: OMOPObject.conditionConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.DeviceExposure):
  System.Code{
    code:    ToString(OMOPObject.deviceConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.deviceConcept.vocabularyId,
  //  display: OMOPObject.deviceConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Domain):
  System.Code{
    code:    ToString(OMOPObject.domainConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.domainConcept.vocabularyId,
  //  display: OMOPObject.domainConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.DrugExposure):
  System.Code{
    code:    ToString(OMOPObject.drugConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.drugConcept.vocabularyId,
  //  display: OMOPObject.drugConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Episode):
  System.Code{
    code:    ToString(OMOPObject.episodeConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.episodeConcept.vocabularyId,
  //  display: OMOPObject.episodeConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Measurement):
  System.Code{
    code:    ToString(OMOPObject.measurementConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.measurementConcept.vocabularyId,
  //  display: OMOPObject.measurementConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Metadata):
  System.Code{
    code:    ToString(OMOPObject.metadataConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.metadataConcept.vocabularyId,
  //  display: OMOPObject.metadataConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.NoteNlp):
  System.Code{
    code:    ToString(OMOPObject.noteNlpConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.noteNlpConcept.vocabularyId,
  //  display: OMOPObject.noteNlpConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Observation):
  System.Code{
    code:    ToString(OMOPObject.observationConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.observationConcept.vocabularyId,
  //  display: OMOPObject.observationConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.ProcedureOccurrence):
  System.Code{
    code:    ToString(OMOPObject.procedureConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.procedureConcept.vocabularyId,
  //  display: OMOPObject.procedureConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Relationship):
  System.Code{
    code:    ToString(OMOPObject.relationshipConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.relationshipConcept.vocabularyId,
  //  display: OMOPObject.relationshipConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Specimen):
  System.Code{
    code:    ToString(OMOPObject.specimenConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.specimenConcept.vocabularyId,
  //  display: OMOPObject.specimenConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.VisitDetail):
  System.Code{
    code:    ToString(OMOPObject.visitDetailConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.visitDetailConcept.vocabularyId,
  //  display: OMOPObject.visitDetailConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.VisitOccurrence):
  System.Code{
    code:    ToString(OMOPObject.visitConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.visitConcept.vocabularyId,
  //  display: OMOPObject.visitConcept.conceptName
  }
  
define function ToCode(OMOPObject OMOP.Vocabulary):
  System.Code{
    code:    ToString(OMOPObject.vocabularyConceptId),
    system:  'https://fhir-terminology.ohdsi.org' //OMOPObject.vocabularyConcept.vocabularyId,
  //  display: OMOPObject.vocabularyConcept.conceptName
  }
  
// Conversion to System.Concept

define function ToConcept(OMOPObject OMOP.Concept):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject List<OMOP.Concept>):
  System.Concept{
    codes: (OMOPObject) o return all ToCode(o)
  }
  
define function ToConcept(OMOPObject OMOP.ConceptClass):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.ConditionOccurrence):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.DeviceExposure):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Domain):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.DrugExposure):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Episode):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Measurement):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Metadata):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.NoteNlp):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Observation):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.ProcedureOccurrence):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Relationship):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Specimen):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.VisitDetail):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.VisitOccurrence):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
define function ToConcept(OMOPObject OMOP.Vocabulary):
  System.Concept{
    codes: { ToCode(OMOPObject) }
  }
  
// Conversion to System.Interval

define function ToInterval(OMOPObject OMOP.Concept):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToInterval(OMOPObject OMOP.ConceptRelationship):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToInterval(OMOPObject OMOP.ConditionEra):
  Interval[OMOPObject.conditionEraStartDate, OMOPObject.conditionEraEndDate]
  
define function ToInterval(OMOPObject OMOP.ConditionOccurrence):
  Interval[OMOPObject.conditionStartDatetime, OMOPObject.conditionEndDatetime]
  
define function ToInterval(OMOPObject OMOP.ConditionOccurrence):
  Interval[OMOPObject.conditionStartDate, OMOPObject.conditionEndDate]
  
define function ToInterval(OMOPObject OMOP.DeviceExposure):
  Interval[OMOPObject.deviceExposureStartDatetime, OMOPObject.deviceExposureEndDatetime]
  
define function ToInterval(OMOPObject OMOP.DeviceExposure):
  Interval[OMOPObject.deviceExposureStartDate, OMOPObject.deviceExposureEndDate]
  
define function ToInterval(OMOPObject OMOP.DoseEra):
  Interval[OMOPObject.doseEraStartDate, OMOPObject.doseEraEndDate]
  
define function ToInterval(OMOPObject OMOP.DrugEra):
  Interval[OMOPObject.drugEraStartDate, OMOPObject.drugEraEndDate]
  
define function ToInterval(OMOPObject OMOP.DrugExposure):
  Interval[OMOPObject.drugExposureStartDatetime, OMOPObject.drugExposureEndDatetime]
  
define function ToInterval(OMOPObject OMOP.DrugExposure):
  Interval[OMOPObject.drugExposureStartDate, OMOPObject.verbatimEndDate]
  
define function ToInterval(OMOPObject OMOP.DrugStrength):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToInterval(OMOPObject OMOP.Episode):
  Interval[OMOPObject.episodeStartDatetime, OMOPObject.episodeEndDatetime]
  
define function ToInterval(OMOPObject OMOP.Episode):
  Interval[OMOPObject.episodeStartDate, OMOPObject.episodeEndDate]
  
define function ToInterval(OMOPObject OMOP.ObservationPeriod):
  Interval[OMOPObject.observationPeriodStartDate, OMOPObject.observationPeriodEndDate]
  
define function ToInterval(OMOPObject OMOP.PayerPlanPeriod):
  Interval[OMOPObject.payerPlanPeriodStartDate, OMOPObject.payerPlanPeriodEndDate]
  
define function ToInterval(OMOPObject OMOP.ProcedureOccurrence):
  Interval[OMOPObject.procedureDatetime, OMOPObject.procedureEndDatetime]
  
define function ToInterval(OMOPObject OMOP.ProcedureOccurrence):
  Interval[OMOPObject.procedureDate, OMOPObject.procedureEndDate]
  
define function ToInterval(OMOPObject OMOP.SourceToConceptMap):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToInterval(OMOPObject OMOP.VisitDetail):
  Interval[OMOPObject.visitDetailStartDatetime, OMOPObject.visitDetailEndDatetime]
  
define function ToInterval(OMOPObject OMOP.VisitDetail):
  Interval[OMOPObject.visitDetailStartDate, OMOPObject.visitDetailEndDate]
  
define function ToInterval(OMOPObject OMOP.VisitOccurrence):
  Interval[OMOPObject.visitStartDatetime, OMOPObject.visitEndDatetime]
  
define function ToInterval(OMOPObject OMOP.VisitOccurrence):
  Interval[OMOPObject.visitStartDate, OMOPObject.visitEndDate]
  
// Conversion to System.Quantity

define function ToQuantity(OMOPObject OMOP.Measurement):
  if OMOPObject.valueAsNumber is null or OMOPObject.unitConcept is null then
    null
  else
    System.Quantity{
      value: OMOPObject.valueAsNumber,
      unit:  OMOPObject.unitConcept.conceptCode
    }
  
define function ToQuantity(OMOPObject OMOP.Observation):
  if OMOPObject.valueAsNumber is null or OMOPObject.unitConcept is null then
    null
  else
    System.Quantity{
      value: OMOPObject.valueAsNumber,
      unit:  OMOPObject.unitConcept.conceptCode
    }
  
