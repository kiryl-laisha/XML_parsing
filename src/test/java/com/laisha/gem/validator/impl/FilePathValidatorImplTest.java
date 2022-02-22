package com.laisha.gem.validator.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FilePathValidatorImplTest {

    static FilePathValidatorImpl validator = FilePathValidatorImpl.getInstance();

    @AfterAll
    static void tearDownClass() {
        validator = null;
    }

    @ParameterizedTest
    @DisplayName("File path is valid.")
    @ValueSource(strings = {"data\\test_empty_file.xml",
            "data/test_gem.xml"})
    void validateFilePathPositiveTest(String filepath) {

        boolean isValidFilePath = validator.validateFilePath(filepath);
        assertTrue(isValidFilePath);
    }

    @ParameterizedTest
    @DisplayName("File path is null or empty or not exist.")
    @NullAndEmptySource
    @ValueSource(strings = {"data/validator_data/file.txt",
            "data\\arrays.txt",
            " ",
            "\n  \t \r  "})
    void validateFilePathNegativeTest(String filepath) {

        boolean isValidFilePath = validator.validateFilePath(filepath);
        assertFalse(isValidFilePath);
    }
}


