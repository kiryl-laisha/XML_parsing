package com.laisha.gem.validator.impl;

import com.laisha.gem.validator.FilePathValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

public class FilePathValidatorImpl implements FilePathValidator {

    private static final Logger logger = LogManager.getLogger();
    private static final FilePathValidatorImpl instance = new FilePathValidatorImpl();
    private static final String WINDOWS_DIRECTORY_SEPARATOR = "\\";
    private static final String UNIX_DIRECTORY_SEPARATOR = "/";

    private FilePathValidatorImpl() {
    }

    public static FilePathValidatorImpl getInstance() {
        return instance;
    }

    public boolean validateFilePath(String filepath) {

        if (filepath == null) {
            logger.log(Level.WARN, "The provided file path is null.");
            return false;
        }
        if (filepath.isBlank()) {
            logger.log(Level.WARN, "The provided file path is empty.");
            return false;
        }
        filepath = filepath.replace(WINDOWS_DIRECTORY_SEPARATOR, UNIX_DIRECTORY_SEPARATOR);
        URL dataFileUrl = getClass().getClassLoader().getResource(filepath);
        if (dataFileUrl == null) {
            logger.log(Level.WARN, "The file \"{}\" could not be found.", filepath);
            return false;
        }
        logger.log(Level.DEBUG, "The file \"{}\" is exist.", dataFileUrl.getPath());
        return true;
    }
}

