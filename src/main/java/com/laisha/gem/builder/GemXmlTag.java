package com.laisha.gem.builder;

public enum GemXmlTag {

    GEMS,
    PARAMETERS,
    PRECIOUS_GEM,
    SEMIPRECIOUS_GEM,
    COLOUR,
    FACET_NUMBER,
    ID,
    IS_CERTIFIED,
    NAME,
    ORIGIN_COUNTRY,
    REGISTRATION_DATE,
    PRICE,
    TRANSPARENCY,
    VALUE,
    WEIGHT;

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";

    @Override
    public String toString() {
        return name().replace(UNDERSCORE, HYPHEN).toLowerCase();
    }
}
