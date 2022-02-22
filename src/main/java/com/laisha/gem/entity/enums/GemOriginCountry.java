package com.laisha.gem.entity.enums;

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

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    public static GemOriginCountry valueOfXmlContent(String content) {
        return GemOriginCountry.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
