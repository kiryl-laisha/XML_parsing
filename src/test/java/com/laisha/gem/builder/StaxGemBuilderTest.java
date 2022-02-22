package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.exception.ProjectException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StaxGemBuilderTest {

    private static final String TEST_GEM_XML_FILEPATH = "data\\test_gem.xml";
    private static final String TEST_GEM_EMPTY_XML_FILEPATH = "data/test_empty_file.xml";
    private static StaxGemBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new StaxGemBuilder();
    }

    @AfterAll
    static void tearDownClass() {
        builder = null;
    }

    @Test
    @DisplayName("Builds gems from valid test XML file.")
    void buildGemsPositiveTest() {

        Set<AbstractGem> gems;
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
    @DisplayName("Throws an exception when filepath is null or empty string.")
    void buildGemsFirstNegativeTest(String filepath) {

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
    @DisplayName("Throws an exception when file is empty.")
    void buildGemsSecondNegativeTest(String filepath) {

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