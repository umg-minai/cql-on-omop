package OMOP.v54;

import OMOP.DataTypeInfo;

public class EpisodeEventInfo implements DataTypeInfo {

    public Class<?> getClazz() {
        return EpisodeEvent.class;
    }

    public String contextPath(final String contextName) {
        return null;
    }

    public ContextInfo infoForContext(final String contextPath, final Object contextValue) {
        return null;
    }

    public boolean isJoinableCodePath(final String codePath) {
        return codePath.equals("episodeEventFieldConcept");
    }


}
