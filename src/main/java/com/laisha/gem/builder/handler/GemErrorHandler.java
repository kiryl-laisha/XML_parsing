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
        logger.log(Level.WARN, "Line \"{}\", column \"{}\".  There's a warning :{}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.log(Level.ERROR, "Line \"{}\", column \"{}\".  There's a error :{}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) {
        logger.log(Level.FATAL, "Line \"{}\", column \"{}\".  There's a fatal error :{}.",
                e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }
}
