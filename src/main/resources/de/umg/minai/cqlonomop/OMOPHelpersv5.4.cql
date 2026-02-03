// This file has been generated from a description of the OMOP CMD v5.4 - do not edit

library OMOPHelpers version '1.0'

using OMOP version 'v5.4'

// Conversion to Interval<System.Date>

define function ToDateInterval(OMOPObject OMOP.Concept):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToDateInterval(OMOPObject OMOP.ConceptRelationship):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToDateInterval(OMOPObject OMOP.ConditionEra):
  Interval[OMOPObject.conditionEraStartDate, OMOPObject.conditionEraEndDate]
  
define function ToDateInterval(OMOPObject OMOP.ConditionOccurrence):
  Interval[OMOPObject.conditionStartDate, OMOPObject.conditionEndDate]
  
define function ToDateInterval(OMOPObject OMOP.DeviceExposure):
  Interval[OMOPObject.deviceExposureStartDate, OMOPObject.deviceExposureEndDate]
  
define function ToDateInterval(OMOPObject OMOP.DoseEra):
  Interval[OMOPObject.doseEraStartDate, OMOPObject.doseEraEndDate]
  
define function ToDateInterval(OMOPObject OMOP.DrugEra):
  Interval[OMOPObject.drugEraStartDate, OMOPObject.drugEraEndDate]
  
define function ToDateInterval(OMOPObject OMOP.DrugExposure):
  Interval[OMOPObject.drugExposureStartDate, OMOPObject.verbatimEndDate]
  
define function ToDateInterval(OMOPObject OMOP.DrugStrength):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToDateInterval(OMOPObject OMOP.Episode):
  Interval[OMOPObject.episodeStartDate, OMOPObject.episodeEndDate]
  
define function ToDateInterval(OMOPObject OMOP.ObservationPeriod):
  Interval[OMOPObject.observationPeriodStartDate, OMOPObject.observationPeriodEndDate]
  
define function ToDateInterval(OMOPObject OMOP.PayerPlanPeriod):
  Interval[OMOPObject.payerPlanPeriodStartDate, OMOPObject.payerPlanPeriodEndDate]
  
define function ToDateInterval(OMOPObject OMOP.ProcedureOccurrence):
  Interval[OMOPObject.procedureDate, OMOPObject.procedureEndDate]
  
define function ToDateInterval(OMOPObject OMOP.SourceToConceptMap):
  Interval[OMOPObject.validStartDate, OMOPObject.validEndDate]
  
define function ToDateInterval(OMOPObject OMOP.VisitDetail):
  Interval[OMOPObject.visitDetailStartDate, OMOPObject.visitDetailEndDate]
  
define function ToDateInterval(OMOPObject OMOP.VisitOccurrence):
  Interval[OMOPObject.visitStartDate, OMOPObject.visitEndDate]
  
// Conversion to Interval<System.DateTime>

define function ToDateTimeInterval(OMOPObject OMOP.ConditionOccurrence):
  Interval[OMOPObject.conditionStartDatetime, OMOPObject.conditionEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.DeviceExposure):
  Interval[OMOPObject.deviceExposureStartDatetime, OMOPObject.deviceExposureEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.DrugExposure):
  Interval[OMOPObject.drugExposureStartDatetime, OMOPObject.drugExposureEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.Episode):
  Interval[OMOPObject.episodeStartDatetime, OMOPObject.episodeEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.ProcedureOccurrence):
  Interval[OMOPObject.procedureDatetime, OMOPObject.procedureEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.VisitDetail):
  Interval[OMOPObject.visitDetailStartDatetime, OMOPObject.visitDetailEndDatetime]
  
define function ToDateTimeInterval(OMOPObject OMOP.VisitOccurrence):
  Interval[OMOPObject.visitStartDatetime, OMOPObject.visitEndDatetime]
  
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
  
// Conversion to System.Date

define function ToDate(OMOPObject OMOP.CdmSource):
  OMOPObject.cdmReleaseDate
  
define function ToDate(OMOPObject OMOP.CohortDefinition):
  OMOPObject.cohortInitiationDate
  
define function ToDate(OMOPObject OMOP.Concept):
  OMOPObject.validStartDate
  
define function ToDate(OMOPObject OMOP.ConceptRelationship):
  OMOPObject.validStartDate
  
define function ToDate(OMOPObject OMOP.ConditionEra):
  OMOPObject.conditionEraStartDate
  
define function ToDate(OMOPObject OMOP.ConditionOccurrence):
  OMOPObject.conditionStartDate
  
define function ToDate(OMOPObject OMOP.Death):
  OMOPObject.deathDate
  
define function ToDate(OMOPObject OMOP.DeviceExposure):
  OMOPObject.deviceExposureStartDate
  
define function ToDate(OMOPObject OMOP.DoseEra):
  OMOPObject.doseEraStartDate
  
define function ToDate(OMOPObject OMOP.DrugEra):
  OMOPObject.drugEraStartDate
  
define function ToDate(OMOPObject OMOP.DrugExposure):
  OMOPObject.drugExposureStartDate
  
define function ToDate(OMOPObject OMOP.DrugStrength):
  OMOPObject.validStartDate
  
define function ToDate(OMOPObject OMOP.Episode):
  OMOPObject.episodeStartDate
  
define function ToDate(OMOPObject OMOP.Measurement):
  OMOPObject.measurementDate
  
define function ToDate(OMOPObject OMOP.Metadata):
  OMOPObject.metadataDate
  
define function ToDate(OMOPObject OMOP.Note):
  OMOPObject.noteDate
  
define function ToDate(OMOPObject OMOP.NoteNlp):
  OMOPObject.nlpDate
  
define function ToDate(OMOPObject OMOP.Observation):
  OMOPObject.observationDate
  
define function ToDate(OMOPObject OMOP.ObservationPeriod):
  OMOPObject.observationPeriodStartDate
  
define function ToDate(OMOPObject OMOP.PayerPlanPeriod):
  OMOPObject.payerPlanPeriodStartDate
  
define function ToDate(OMOPObject OMOP.ProcedureOccurrence):
  OMOPObject.procedureDate
  
define function ToDate(OMOPObject OMOP.SourceToConceptMap):
  OMOPObject.validStartDate
  
define function ToDate(OMOPObject OMOP.Specimen):
  OMOPObject.specimenDate
  
define function ToDate(OMOPObject OMOP.VisitDetail):
  OMOPObject.visitDetailStartDate
  
define function ToDate(OMOPObject OMOP.VisitOccurrence):
  OMOPObject.visitStartDate
  
// Conversion to System.DateTime

define function ToDateTime(OMOPObject OMOP.ConditionOccurrence):
  OMOPObject.conditionStartDatetime
  
define function ToDateTime(OMOPObject OMOP.Death):
  OMOPObject.deathDatetime
  
define function ToDateTime(OMOPObject OMOP.DeviceExposure):
  OMOPObject.deviceExposureStartDatetime
  
define function ToDateTime(OMOPObject OMOP.DrugExposure):
  OMOPObject.drugExposureStartDatetime
  
define function ToDateTime(OMOPObject OMOP.Episode):
  OMOPObject.episodeStartDatetime
  
define function ToDateTime(OMOPObject OMOP.Measurement):
  OMOPObject.measurementDatetime
  
define function ToDateTime(OMOPObject OMOP.Metadata):
  OMOPObject.metadataDatetime
  
define function ToDateTime(OMOPObject OMOP.Note):
  OMOPObject.noteDatetime
  
define function ToDateTime(OMOPObject OMOP.NoteNlp):
  OMOPObject.nlpDatetime
  
define function ToDateTime(OMOPObject OMOP.Observation):
  OMOPObject.observationDatetime
  
define function ToDateTime(OMOPObject OMOP.Person):
  OMOPObject.birthDatetime
  
define function ToDateTime(OMOPObject OMOP.ProcedureOccurrence):
  OMOPObject.procedureDatetime
  
define function ToDateTime(OMOPObject OMOP.Specimen):
  OMOPObject.specimenDatetime
  
define function ToDateTime(OMOPObject OMOP.VisitDetail):
  OMOPObject.visitDetailStartDatetime
  
define function ToDateTime(OMOPObject OMOP.VisitOccurrence):
  OMOPObject.visitStartDatetime
  
// Conversion to System.Quantity

define function ToQuantity(OMOPObject OMOP.DrugStrength):
  if OMOPObject.amountValue is null or OMOPObject.amountUnitConcept is null then
    null
  else
    System.Quantity{
      value: OMOPObject.amountValue,
      unit:  OMOPObject.amountUnitConcept.conceptCode
    }
  
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
  
