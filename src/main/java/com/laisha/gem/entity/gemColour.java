package com.laisha.gem.entity;

public enum gemColour {

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

    public static gemOrigin valueOfXmlContent(String content) {
        return gemOrigin.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}

