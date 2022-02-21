package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.exception.ProjectException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SaxGemBuilderTest {

    private static final String TEST_GEM_XML_FILEPATH ="data\\test_gem.xml";
    private static final String TEST_GEM_EMPTY_XML_FILEPATH ="data/test_empty_file.xml";
    Set<AbstractGem> gems;

    @BeforeEach
    void setUp() {
        gems = new HashSet<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Builds gems from valid test XML file.")
    void buildGemsPositiveTest() {

        SaxGemBuilder builder = new SaxGemBuilder();
        try {
            builder.buildGems(TEST_GEM_XML_FILEPATH);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        gems = builder.getGems();
        int expected = 15;
        int actual = gems.size();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Has an exception when filepath is null or empty string.")
    void buildGemsFirstNegativeTest(String filepath) {

        SaxGemBuilder builder = new SaxGemBuilder();
        String expectedExceptionMessage = "Working with file is impossible.";
        String actualExceptionMessage = null;
        try {
            builder.buildGems(filepath);
        } catch (ProjectException e) {
            actualExceptionMessage = e.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {TEST_GEM_EMPTY_XML_FILEPATH})
    @DisplayName("Has an exception when file is empty.")
    void buildGemsSecondNegativeTest(String filepath) {

        SaxGemBuilder builder = new SaxGemBuilder();
        String expectedExceptionMessage = "File \"" + filepath + "\" " +
                "is empty. Reading from the file is impossible.";
        String actualExceptionMessage = null;
        try {
            builder.buildGems(filepath);
        } catch (ProjectException e) {
            actualExceptionMessage = e.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}