package com.laisha.gem.entity;

public enum gemOrigin {

    ANGOLA,
    AUSTRALIA,
    BRAZIL,
    CANADA,
    MADAGASCAR,
    RUSSIA,
    SOUTH_AFRICA,
    USA,
    ZAMBIA;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    public static gemOrigin valueOfXmlContent(String content) {
        return gemOrigin.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
