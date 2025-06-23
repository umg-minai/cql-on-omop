package OMOP.v54;

import OMOP.DataTypeInfo;

public class NoteInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return Note.class;
    }

    public String contextPath(final String contextName) {
        if (contextName.equals("Patient")) {
            return "person";
        } else {
            return null;
        }
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        if (contextPath.equals("person") && (contextValue instanceof Person person)) {
            return new ContextInfo("personId", person.getPersonId());
        } else {
            return null;
        }
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("noteEventFieldConcept")
               || codePath.equals("languageConcept")
               || codePath.equals("encodingConcept")
               || codePath.equals("noteClassConcept")
               || codePath.equals("noteTypeConcept");
    }


}
