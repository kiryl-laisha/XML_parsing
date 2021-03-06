package com.laisha.gem.entity;

public enum GemOriginCountry {

    ANGOLA,
    AUSTRALIA,
    BRAZIL,
    CANADA,
    MADAGASCAR,
    RUSSIA,
    SOUTH_AFRICA,
    USA,
    ZAMBIA;

    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    public static GemOriginCountry valueOfXmlContent(String content) {
        return GemOriginCountry.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
