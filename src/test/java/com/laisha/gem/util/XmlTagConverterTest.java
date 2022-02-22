package com.laisha.gem.util;

import com.laisha.gem.builder.GemXmlTag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlTagConverterTest {

    private static XmlTagConverter converter = XmlTagConverter.getInstance();

    @AfterAll
    static void tearDownClass() {
        converter = null;
    }

    @ParameterizedTest
    @DisplayName("Test converts valid tag name to GemXmlTag.")
    @ValueSource(strings = {"origin-country"})
    void convertXmlTagPositiveTest(String localName) {

        GemXmlTag expected = GemXmlTag.ORIGIN_COUNTRY;
        GemXmlTag actual = converter.convertXmlTag(localName);
        assertEquals(expected, actual);
    }
}