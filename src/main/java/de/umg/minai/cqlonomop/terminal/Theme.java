package de.umg.minai.cqlonomop.terminal;

import org.jline.utils.AttributedStyle;

public interface Theme {

    enum Element {
        // Generic elements
        DEFAULT,
        INACTIVE,
        HEADING,
        WARNING,
        ERROR,
        // Messages
        MESSAGE_OTHER,
        MESSAGE_INFO,
        MESSAGE_WARNING,
        // Syntax elements
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
