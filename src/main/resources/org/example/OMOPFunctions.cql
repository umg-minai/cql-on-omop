library OMOPFunctions version '1.0'

using OMOP version 'v5.4'

include OMOPHelpers

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
  [DrugStrength: ingredientConcept ~ (convert Ingredient to Concept)]

define function DrugStrengthForIngredient(Ingredient List<OMOP.Concept>):
  [DrugStrength: ingredientConcept ~ OMOPHelpers.ToConcept(Ingredient)]

// Validity

define function Validity(DrugStrength OMOP.DrugStrength):
  Interval[DrugStrength.validStartDate, DrugStrength.validEndDate]

define function IsValid(DrugStrength OMOP.DrugStrength):
  Today() in Validity(DrugStrength)
