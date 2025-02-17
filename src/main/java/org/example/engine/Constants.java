package org.example.engine;

import java.util.Map;

public class Constants {

    public final static String OMOP_CODESYSTEM_URI = "https://fhir-terminology.ohdsi.org";

    public final static Map<String, String> OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID = Map.of(
            "http://snomed.info/sct", "SNOMED"
    );

}
