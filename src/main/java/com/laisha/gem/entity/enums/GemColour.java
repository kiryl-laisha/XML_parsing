package com.laisha.gem.entity.enums;

public enum GemColour {

    BLACK,
    BLUE,
    BROWN,
    GREEN,
    GREY,
    MULTICOLOUR,
    PINK,
    RED,
    VIOLET,
    WHITE;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    public static GemColour valueOfXmlContent(String content) {
        return GemColour.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}

