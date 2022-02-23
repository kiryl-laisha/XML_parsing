package com.laisha.gem.entity;

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

    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    public static GemColour valueOfXmlContent(String content) {
        return GemColour.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}

