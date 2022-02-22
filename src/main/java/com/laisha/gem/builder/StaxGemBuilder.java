package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.entity.GemVisualParameters;
import com.laisha.gem.entity.PreciousGem;
import com.laisha.gem.entity.SemiPreciousGem;
import com.laisha.gem.exception.ProjectException;
import com.laisha.gem.util.FileContentDefinition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.laisha.gem.builder.GemXmlTag.*;

public class StaxGemBuilder extends AbstractGemBuilder {

    private static final Logger logger = LogManager.getLogger();
    private static final FileContentDefinition fileContent = FileContentDefinition.getInstance();
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    private final XMLInputFactory factory;
    private AbstractGem currentGem;
    private GemVisualParameters parameters;

    public StaxGemBuilder() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    protected void buildGems(String filepath) throws ProjectException {

        logger.log(Level.DEBUG, "Gem StAX builder has started.");
        XMLStreamReader reader;
        File file = fileContent.defineFilePathForData(filepath);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            reader = factory.createXMLStreamReader(inputStream);
            int type;
            String text;
            GemXmlTag tagName = GEMS;
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        tagName = processElement(reader);
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        text = reader.getText();
                        if (!text.isBlank()) {
                            processText(text, tagName);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        finishElement(reader);
                        break;
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new ProjectException("StAX parser exception has occurred. ", e);
        }
        logger.log(Level.DEBUG, "Gem StAX builder has finished.");
    }

    private GemXmlTag processElement(XMLStreamReader reader) {

        String elementName = reader.getLocalName();
        GemXmlTag tagName = defineGemXmlTag(elementName);
        switch (tagName) {
            case PRECIOUS_GEM:
                currentGem = new PreciousGem();
                fillGemAttribute(reader);
                break;
            case SEMIPRECIOUS_GEM:
                currentGem = new SemiPreciousGem();
                fillGemAttribute(reader);
                break;
            case PARAMETERS:
                parameters = new GemVisualParameters();
                fillParametersAttribute(reader);
                break;
        }
        return tagName;
    }

    private void processText(String text, GemXmlTag tagName) {

        switch (tagName) {
            case NAME:
                currentGem.setName(text);
                break;
            case ORIGIN_COUNTRY:
                currentGem.setOriginCountry(text);
                break;
            case FACET_NUMBER:
                parameters.setFacetNumber(Integer.parseInt(text));
                break;
            case TRANSPARENCY:
                parameters.setTransparency(Integer.parseInt(text));
                break;
            case PRICE:
                currentGem.setPrice(BigDecimal.valueOf(Double.parseDouble(text)));
                break;
            case REGISTRATION_DATE:
                currentGem.setRegistrationDate(LocalDate.parse(text));
                break;
            case VALUE:
                ((PreciousGem) currentGem).setValue(Double.parseDouble(text));
                break;
            case WEIGHT:
                ((SemiPreciousGem) currentGem).setWeight(Double.parseDouble(text));
                break;
        }
    }

    private void finishElement(XMLStreamReader reader) {

        String elementName = reader.getLocalName();
        GemXmlTag tagName = defineGemXmlTag(elementName);
        switch (tagName) {
            case PRECIOUS_GEM:
            case SEMIPRECIOUS_GEM:
                gems.add(currentGem);
                currentGem = null;
                break;
            case PARAMETERS:
                currentGem.setParameters(parameters);
                parameters = null;
                break;
        }
    }

    private void fillGemAttribute(XMLStreamReader reader) {

        String gemId = reader.getAttributeValue(null, GemXmlTag.ID.toString());
        currentGem.setGemId(gemId);
    }

    private void fillParametersAttribute(XMLStreamReader reader) {

        String colour = reader.getAttributeValue(null, COLOUR.toString());
        parameters.setColour(colour);
        String certified = reader.getAttributeValue(null, CERTIFIED.toString());
        if (certified == null) {
            certified = String.valueOf(GemVisualParameters.DEFAULT_CERTIFIED_GEM);
        }
        parameters.setCertified(Boolean.parseBoolean(certified));
    }

    private GemXmlTag defineGemXmlTag(String name) {
        return GemXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
