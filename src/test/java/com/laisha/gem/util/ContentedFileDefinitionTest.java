package com.laisha.gem.util;

import com.laisha.gem.exception.ProjectException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ContentedFileDefinitionTest {

    private static ContentedFileDefinition fileDefinition = ContentedFileDefinition.getInstance();
    private static final String TEST_GEM_XML_FILEPATH = "data\\test_gem.xml";
    private static final String TEST_GEM_EMPTY_XML_FILEPATH = "data/test_empty_file.xml";

    @AfterAll
    static void tearDownClass() {
        fileDefinition = null;
    }

    @ParameterizedTest
    @DisplayName("File path is valid. File contains a required data.")
    @ValueSource(strings = {TEST_GEM_XML_FILEPATH})
    void defineFileWithDataPositiveTest(String filepath) {

        String expected = "E:\\blinov_web_course_by_epam\\projects\\XML_parsing\\" +
                "target\\test-classes\\data\\test_gem.xml";
        File file = null;
        try {
            file = fileDefinition.defineFileWithData(filepath);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        String actual = String.valueOf(file);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"data/validator_data/file.txt",
            "data\\arrays.txt",
            " ",
            "\n  \t \r  "})
    @DisplayName("Test catches an exception when file path is null " +
            "or empty or not exist.")
    void defineFileWithDataFirstNegativeTest(String filepath) {

        String expectedExceptionMessage = "Working with file is impossible.";
        String actualExceptionMessage = null;
        try {
            fileDefinition.defineFileWithData(filepath);
        } catch (ProjectException e) {
            actualExceptionMessage = e.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {TEST_GEM_EMPTY_XML_FILEPATH})
    @DisplayName("Test catches an exception when file is empty.")
    void defineFileWithDataSecondNegativeTest(String filepath) {

        String expectedExceptionMessage = "File \"" + filepath + "\" " +
                "is empty. Reading from the file is impossible.";
        String actualExceptionMessage = null;
        try {
            fileDefinition.defineFileWithData(filepath);
        } catch (ProjectException e) {
            actualExceptionMessage = e.getMessage();
        }
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }
}