library OMOPHelpers version '1.0'

using OMOP version 'v5.4'

// Conversion to concept

define function ToConcept(OMOPConcept OMOP.Concept):
  System.Concept{
    codes: ({ OMOPConcept } union OMOPConcept.ancestors) c return ToCode(c)
  }

// Conversion to code

define function ToCode(OMOPConcept OMOP.Concept):
  System.Code{
    code:   ToString(OMOPConcept.conceptId),
    system: OMOPConcept.vocabularyId
  }

// Conversion to quantity

define function ToQuantity(OMOPMeasurement OMOP.Measurement):
  if OMOPMeasurement.valueAsNumber is null or OMOPMeasurement.unitConcept is null then
    null
  else
    System.Quantity{
      value: OMOPMeasurement.valueAsNumber,
      unit:  OMOPMeasurement.unitConcept.conceptCode
    }

// Conversion to temporal interval

// Maybe; Note that start/end are Date not Datetime in this case
//define function ToInterval(OMOPConditionEra OMOP.ConditionEra):
//  Interval[OMOPConditionEra.conditionEraStartDate,
//           OMOPConditionEra.conditionEraEndDate]

define function ToInterval(OMOPConditionOccurrence OMOP.ConditionOccurrence):
  Interval[OMOPConditionOccurrence.conditionStartDatetime,
           OMOPConditionOccurrence.conditionEndDatetime]

define function ToInterval(OMOPDeviceExposure OMOP.DeviceExposure):
  Interval[OMOPDeviceExposure.deviceExposureStartDatetime,
           OMOPDeviceExposure.deviceExposureEndDatetime]

// Maybe; Note that start/end are Date not Datetime in this case
//define function ToInterval(OMOPDoseEra OMOP.DoseEra):
//  Interval[OMOPDoseEra.doseEraStartDate,
//           OMOPDoseEra.doseEraEndDate]

// Maybe; Note that start/end are Date not Datetime in this case
//define function ToInterval(OMOPDrugEra OMOP.DrugEra):
//  Interval[OMOPDrugEra.drugEraStartDate,
//           OMOPDrugEra.drugEraEndDate]

define function ToInterval(OMOPDrugExposure OMOP.DrugExposure):
  Interval[OMOPDrugExposure.drugExposureStartDatetime,
           OMOPDrugExposure.drugExposureEndDatetime]

define function ToInterval(OMOPEpisode OMOP.Episode):
  Interval[OMOPEpisode.episodeStartDatetime,
           OMOPEpisode.episodeEndDatetime]

define function ToInterval(OMOPVisitOccurrence OMOP.VisitOccurrence):
  Interval[OMOPVisitOccurrence.visitStartDatetime,
           OMOPVisitOccurrence.visitEndDatetime]

// Conversion to Date

define function ToDate(OMOPDeath OMOP.Death):
  OMOPDeath.deathDate

// Conversion to DateTime

define function ToDateTime(OMOPDeath OMOP.Death):
  OMOPDeath.deathDatetime

define function ToDateTime(OMOPMeasurement OMOP.Measurement):
  OMOPMeasurement.measurementDatetime
