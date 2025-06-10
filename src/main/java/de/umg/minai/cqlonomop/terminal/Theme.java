package de.umg.minai.cqlonomop.terminal;

import org.jline.utils.AttributedStyle;

public interface Theme {

    enum Element {
        //
        DEFAULT,
        INACTIVE,
        HEADING,
        WARNING,
        ERROR,
        //
        MESSAGE_OTHER,
        MESSAGE_INFO,
        MESSAGE_WARNING,
        //
        KEYWORD,
        STRING_LITERAL,
        NUMBER_LITERAL,
        GENERIC_LITERAL,
        IDENTIFIER,
        DEFINITION_NAME,
        TYPE_SPECIFIER,
    }

    AttributedStyle styleForElement(Element element);

}
