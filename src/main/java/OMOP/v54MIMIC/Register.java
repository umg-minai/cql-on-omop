package OMOP.v54MIMIC;

import OMOP.MappingInfo;

public class Register {

    public static void register(final MappingInfo mappingInfo) {
        mappingInfo.registerDataTypeInfo("CareSite", new CareSiteInfo());
        mappingInfo.registerDataTypeInfo("CdmSource", new CdmSourceInfo());
        mappingInfo.registerDataTypeInfo("CohortDefinition", new CohortDefinitionInfo());
        mappingInfo.registerDataTypeInfo("Concept", new ConceptInfo());
        mappingInfo.registerDataTypeInfo("ConceptAncestor", new ConceptAncestorInfo());
        mappingInfo.registerDataTypeInfo("ConceptClass", new ConceptClassInfo());
        mappingInfo.registerDataTypeInfo("ConceptRelationship", new ConceptRelationshipInfo());
        mappingInfo.registerDataTypeInfo("ConceptSynonym", new ConceptSynonymInfo());
        mappingInfo.registerDataTypeInfo("ConditionEra", new ConditionEraInfo());
        mappingInfo.registerDataTypeInfo("ConditionOccurrence", new ConditionOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("Cost", new CostInfo());
        mappingInfo.registerDataTypeInfo("Death", new DeathInfo());
        mappingInfo.registerDataTypeInfo("DeviceExposure", new DeviceExposureInfo());
        mappingInfo.registerDataTypeInfo("Domain", new DomainInfo());
        mappingInfo.registerDataTypeInfo("DoseEra", new DoseEraInfo());
        mappingInfo.registerDataTypeInfo("DrugEra", new DrugEraInfo());
        mappingInfo.registerDataTypeInfo("DrugExposure", new DrugExposureInfo());
        mappingInfo.registerDataTypeInfo("DrugStrength", new DrugStrengthInfo());
        mappingInfo.registerDataTypeInfo("Episode", new EpisodeInfo());
        mappingInfo.registerDataTypeInfo("EpisodeEvent", new EpisodeEventInfo());
        mappingInfo.registerDataTypeInfo("FactRelationship", new FactRelationshipInfo());
        mappingInfo.registerDataTypeInfo("Location", new LocationInfo());
        mappingInfo.registerDataTypeInfo("Measurement", new MeasurementInfo());
        mappingInfo.registerDataTypeInfo("Metadata", new MetadataInfo());
        mappingInfo.registerDataTypeInfo("Note", new NoteInfo());
        mappingInfo.registerDataTypeInfo("NoteNlp", new NoteNlpInfo());
        mappingInfo.registerDataTypeInfo("Observation", new ObservationInfo());
        mappingInfo.registerDataTypeInfo("ObservationPeriod", new ObservationPeriodInfo());
        mappingInfo.registerDataTypeInfo("PayerPlanPeriod", new PayerPlanPeriodInfo());
        mappingInfo.registerDataTypeInfo("Person", new PersonInfo());
        mappingInfo.registerDataTypeInfo("ProcedureOccurrence", new ProcedureOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("Provider", new ProviderInfo());
        mappingInfo.registerDataTypeInfo("Relationship", new RelationshipInfo());
        mappingInfo.registerDataTypeInfo("SourceToConceptMap", new SourceToConceptMapInfo());
        mappingInfo.registerDataTypeInfo("Specimen", new SpecimenInfo());
        mappingInfo.registerDataTypeInfo("VisitDetail", new VisitDetailInfo());
        mappingInfo.registerDataTypeInfo("VisitOccurrence", new VisitOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("Vocabulary", new VocabularyInfo());

    }


}
