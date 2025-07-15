package de.umg.minai.cqlonomop.terminal;

import org.jline.utils.AttributedStyle;

public class DefaultTheme implements Theme {

    public static final int RED       = 0xd02b61;
    public static final int ORANGE    = 0xda8548;
    public static final int GREEN     = 0x60aa00;
    public static final int TEAL      = 0x4db5bd;
    public static final int YELLOW    = 0xd08928;
    public static final int BLUE      = 0x6c9ef8;
    public static final int DARK_BLUE = 0x6688aa;
    public static final int MAGENTA   = 0xb77fdb;
    public static final int VIOLET    = 0xa9a1e1;
    public static final int CYAN      = 0x00aa80;
    public static final int DARK_CYAN = 0x5699AF;
    public static final int URLBLUE   = 0x57aadd;
    public static final int IOLIME    = 0xbbfc20;
    public static final int IOPURPLE  = 0xbb20fc;
    public static final int IOCYAN    = 0x20bbfc;
    public static final int IOPINK    = 0xfc20bb;
    public static final int IOTEAL    = 0x20fcbb;
    public static final int BASE6     = 0x808080;

    public static final AttributedStyle STYLE_DEFAULT         = new AttributedStyle();
    public static final AttributedStyle STYLE_INACTIVE        = new AttributedStyle().foregroundRgb(BASE6);
    public static final AttributedStyle STYLE_HEADING         = new AttributedStyle().bold();
    public static final AttributedStyle STYLE_WARNING         = new AttributedStyle().foregroundRgb(YELLOW);
    public static final AttributedStyle STYLE_ERROR           = new AttributedStyle().foregroundRgb(RED);

    public static final AttributedStyle STYLE_MESSAGE_OTHER   = new AttributedStyle().foregroundRgb(BASE6);
    public static final AttributedStyle STYLE_MESSAGE_INFO    = new AttributedStyle().foregroundRgb(GREEN);
    public static final AttributedStyle STYLE_MESSAGE_WARNING = new AttributedStyle().foregroundRgb(YELLOW);

    public static final AttributedStyle STYLE_KEYWORD         = new AttributedStyle().foregroundRgb(BLUE);
    public static final AttributedStyle STYLE_STRING_LITERAL  = new AttributedStyle().foregroundRgb(YELLOW);
    public static final AttributedStyle STYLE_NUMBER_LITERAL  = new AttributedStyle().foregroundRgb(TEAL);
    public static final AttributedStyle STYLE_GENERIC_LITERAL = new AttributedStyle().foregroundRgb(GREEN);
    public static final AttributedStyle STYLE_IDENTIFIER      = new AttributedStyle().foregroundRgb(MAGENTA);
    public static final AttributedStyle STYLE_DEFINITION_NAME = new AttributedStyle().foregroundRgb(MAGENTA).bold();
    public static final AttributedStyle STYLE_TYPE_SPECIFIER  = new AttributedStyle().foregroundRgb(CYAN);

    @Override
    public AttributedStyle styleForElement(Element element) {
        return switch (element) {
            // Generic elements
            case DEFAULT         -> STYLE_DEFAULT;
            case INACTIVE        -> STYLE_INACTIVE;
            case HEADING         -> STYLE_HEADING;
            case WARNING         -> STYLE_WARNING;
            case ERROR           -> STYLE_ERROR;
            // Messages
            case MESSAGE_OTHER   -> STYLE_MESSAGE_OTHER;
            case MESSAGE_INFO    -> STYLE_MESSAGE_INFO;
            case MESSAGE_WARNING -> STYLE_MESSAGE_WARNING;
            // Syntax elements
            case KEYWORD         -> STYLE_KEYWORD;
            case STRING_LITERAL  -> STYLE_STRING_LITERAL;
            case NUMBER_LITERAL  -> STYLE_NUMBER_LITERAL;
            case GENERIC_LITERAL -> STYLE_GENERIC_LITERAL;
            case IDENTIFIER      -> STYLE_IDENTIFIER;
            case DEFINITION_NAME -> STYLE_DEFINITION_NAME;
            case TYPE_SPECIFIER  -> STYLE_TYPE_SPECIFIER;
        };
    }

}
