library OMOPFunctions version '1.0'

using OMOP version 'v5.3'

include OMOPHelpers

// Conversion to OMOP.Concept

define function ToOMOPConcept(SystemCode System.Code):
  // TODO could do this without retrieve if system is OMOPSV
  singleton from ([OMOP.Concept: SystemCode] c return all c)

// Standard concept

define function IsStandardConcept(AConcept OMOP.Concept):
  AConcept.standardConcept = 'S'

define function StandardConcept(AConcept OMOP.Concept):
  if IsStandardConcept(AConcept) then
    AConcept
  else
    singleton from ((AConcept.relationships)
                      r where r.relationshipId = 'Maps to'
                      return all r.concept2)

define function StandardConcept(Id String):
  singleton from ([OMOP.Concept: Code{code: Id, system: 'https://fhir-terminology.ohdsi.org'}])

define function SC(Id String):
  StandardConcept(Id)

define function StandardConcept(Id Integer):
  StandardConcept(convert Id to String)

define function SC(Id Integer):
  StandardConcept(Id)

// Concept relationships

define function RelatedConcepts(OMOPConcept OMOP.Concept,
                                Relationship String):
  (OMOPConcept.relationships) r
    where r.relationshipId = Relationship
    return r.concept2

// Drug strength

define function DrugStrengthForDrug(Drug OMOP.Concept):
  [DrugStrength: drugConcept ~ (convert Drug to System.Concept)]

define function DrugStrengthForDrug(Drug List<OMOP.Concept>):
  [DrugStrength: drugConcept ~ OMOPHelpers.ToConcept(Drug)]

define function DrugStrengthForIngredient(Ingredient OMOP.Concept):
  [DrugStrength: ingredientConcept ~ (convert Ingredient to System.Concept)]

define function DrugStrengthForIngredient(Ingredient List<OMOP.Concept>):
  [DrugStrength: ingredientConcept ~ OMOPHelpers.ToConcept(Ingredient)]

// Validity

define function Validity(DrugStrength OMOP.DrugStrength):
  Interval[DrugStrength.validStartDate, DrugStrength.validEndDate]

define function IsValid(DrugStrength OMOP.DrugStrength):
  Today() in Validity(DrugStrength)
