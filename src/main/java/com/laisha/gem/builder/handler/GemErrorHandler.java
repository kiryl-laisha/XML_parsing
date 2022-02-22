package com.laisha.gem.builder.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class GemErrorHandler implements ErrorHandler {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException e) {
        logger.log(Level.WARN, "Line \"{}\", column \"{}\". Warning is occurred: {}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.log(Level.WARN, "Line \"{}\", column \"{}\". Error is occurred: {}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        logger.log(Level.WARN, "Line \"{}\", column \"{}\". Fatal error is occurred:{}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }
}
