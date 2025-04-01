package org.example.engine;

import java.util.Map;

public class Constants {

    /**
     * The URI that identifies the OMOP Standard Vocabulary code system. We treat this as a special case because we map
     * CQL codes directly to OMOP concept IDs without looking at the concept code or vocabulary.
     */
    public final static String OMOP_CODESYSTEM_URI = "https://fhir-terminology.ohdsi.org";

    /**
     * Mapping from built-in Codesystem URIs to OMOP vocabulary ids. We use these when we map a code with id i and
     * Codesystem URI u to an OMOP concept: assume that this map maps URI u to vocabulary id v, then the row in the
     * concept table has concept_code = i and vocabulary_id = v.
     */
    public final static Map<String, String> OMOP_CODESYSTEM_URI_TO_VOCABULARY_ID = Map.of(
            "http://snomed.info/sct", "SNOMED"
    );

}
