package com.laisha.gem.util;

import com.laisha.gem.exception.ProjectException;
import com.laisha.gem.validator.impl.FilePathValidatorImpl;

import java.io.File;
import java.net.URL;

public class ContentedFileDefinition {

    private static final ContentedFileDefinition instance = new ContentedFileDefinition();
    private static final String WINDOWS_DIRECTORY_SEPARATOR = "\\";
    private static final String UNIX_DIRECTORY_SEPARATOR = "/";

    private ContentedFileDefinition() {
    }

    public static ContentedFileDefinition getInstance() {
        return instance;
    }

    public File defineFilePathForData(String filepath)
            throws ProjectException {

        FilePathValidatorImpl validator = FilePathValidatorImpl.getInstance();
        boolean isFilePathValid = validator.validateFilePath(filepath);
        if (!isFilePathValid) {
            throw new ProjectException("Working with file is impossible.");
        }
        filepath = filepath.replace(WINDOWS_DIRECTORY_SEPARATOR, UNIX_DIRECTORY_SEPARATOR);
        URL dataFileUrl = getClass().getClassLoader().getResource(filepath);
        if (dataFileUrl == null) {
            throw new ProjectException("URL for the file path \"" + filepath + "\" could not " +
                    "be found. Reading from the file is impossible.");
        }
        File dataFile = new File(dataFileUrl.getFile());
        if (dataFile.length() == 0) {
            throw new ProjectException("File \"" + filepath + "\" " +
                    "is empty. Reading from the file is impossible.");
        }
        return dataFile;
    }
}
