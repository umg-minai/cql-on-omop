package OMOP.v54;

import OMOP.MappingInfo;

public class Register  {
    public static void register(final MappingInfo mappingInfo) {
        mappingInfo.registerDataTypeInfo("Person", new PersonInfo());
        mappingInfo.registerDataTypeInfo("ObservationPeriod", new ObservationPeriodInfo());
        mappingInfo.registerDataTypeInfo("VisitOccurrence", new VisitOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("VisitDetail", new VisitDetailInfo());
        mappingInfo.registerDataTypeInfo("ConditionOccurrence", new ConditionOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("DrugExposure", new DrugExposureInfo());
        mappingInfo.registerDataTypeInfo("ProcedureOccurrence", new ProcedureOccurrenceInfo());
        mappingInfo.registerDataTypeInfo("DeviceExposure", new DeviceExposureInfo());
        mappingInfo.registerDataTypeInfo("Measurement", new MeasurementInfo());
        mappingInfo.registerDataTypeInfo("Observation", new ObservationInfo());
        mappingInfo.registerDataTypeInfo("Note", new NoteInfo());
        mappingInfo.registerDataTypeInfo("NoteNlp", new NoteNlpInfo());
        mappingInfo.registerDataTypeInfo("Specimen", new SpecimenInfo());
        mappingInfo.registerDataTypeInfo("Location", new LocationInfo());
        mappingInfo.registerDataTypeInfo("CareSite", new CareSiteInfo());
        mappingInfo.registerDataTypeInfo("Provider", new ProviderInfo());
        mappingInfo.registerDataTypeInfo("PayerPlanPeriod", new PayerPlanPeriodInfo());
        mappingInfo.registerDataTypeInfo("Cost", new CostInfo());
        mappingInfo.registerDataTypeInfo("DrugEra", new DrugEraInfo());
        mappingInfo.registerDataTypeInfo("DoseEra", new DoseEraInfo());
        mappingInfo.registerDataTypeInfo("ConditionEra", new ConditionEraInfo());
        mappingInfo.registerDataTypeInfo("Episode", new EpisodeInfo());
        mappingInfo.registerDataTypeInfo("Metadata", new MetadataInfo());
        mappingInfo.registerDataTypeInfo("Concept", new ConceptInfo());
        mappingInfo.registerDataTypeInfo("Vocabulary", new VocabularyInfo());
        mappingInfo.registerDataTypeInfo("Domain", new DomainInfo());
        mappingInfo.registerDataTypeInfo("ConceptClass", new ConceptClassInfo());
        mappingInfo.registerDataTypeInfo("Relationship", new RelationshipInfo());
        mappingInfo.registerDataTypeInfo("ConceptRelationship", new ConceptRelationshipInfo());
        
    }
    
}
