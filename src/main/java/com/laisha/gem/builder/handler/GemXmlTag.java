package com.laisha.gem.builder.handler;

public enum GemXmlTag {

    GEMS,
    PARAMETERS,
    PRECIOUS_GEM,
    SEMIPRECIOUS_GEM,
    CERTIFIED,
    COLOUR,
    FACET_NUMBER,
    ID,
    NAME,
    ORIGIN,
    REGISTRATION_DATE,
    PRICE,
    TRANSPARENCY,
    VALUE,
    WEIGHT;

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    @Override
    public String toString() {
        return name().replace(UNDERSCORE, HYPHEN).toLowerCase();
    }
}
