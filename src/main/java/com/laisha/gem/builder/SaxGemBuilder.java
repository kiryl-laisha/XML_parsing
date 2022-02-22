package com.laisha.gem.builder;

import com.laisha.gem.builder.handler.GemErrorHandler;
import com.laisha.gem.builder.handler.GemHandler;
import com.laisha.gem.exception.ProjectException;
import com.laisha.gem.util.ContentedFileDefinition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxGemBuilder extends AbstractGemBuilder {

    private static final Logger logger = LogManager.getLogger();
    private static final ContentedFileDefinition fileContent = ContentedFileDefinition.getInstance();
    private final GemHandler handler;
    private XMLReader reader;

    public SaxGemBuilder() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            logger.log(Level.WARN, "Parser can't be created with " +
                    "required parameters.", e);
        } catch (SAXException e) {
            logger.log(Level.WARN, "SAX parser exception has occurred.", e);
        }
        handler = new GemHandler();
        reader.setContentHandler(handler);
        reader.setErrorHandler(new GemErrorHandler());
    }

    @Override
    public void buildGems(String filepath) throws ProjectException {

        File file = fileContent.defineFileForData(filepath);
        try {
            reader.parse(String.valueOf(file));
        } catch (IOException | SAXException e) {
            throw new ProjectException("Exception was occurred during SAX parsing. ", e);
        }
        gems = handler.getGems();
        logger.log(Level.DEBUG, "Gems was built by SAX parser.");
    }
}
