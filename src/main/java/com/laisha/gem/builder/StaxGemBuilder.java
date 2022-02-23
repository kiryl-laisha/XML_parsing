package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.entity.GemVisualParameters;
import com.laisha.gem.entity.PreciousGem;
import com.laisha.gem.entity.SemiPreciousGem;
import com.laisha.gem.entity.GemColour;
import com.laisha.gem.entity.GemOriginCountry;
import com.laisha.gem.exception.ProjectException;
import com.laisha.gem.util.ContentedFileDefinition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static com.laisha.gem.builder.GemXmlTag.*;

public class StaxGemBuilder extends AbstractGemBuilder {

    private static final Logger logger = LogManager.getLogger();
    private static final ContentedFileDefinition fileContent = ContentedFileDefinition.getInstance();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private final XMLInputFactory factory;
    private AbstractGem currentGem;
    private GemVisualParameters currentParameters;

    public StaxGemBuilder() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    protected void buildGems(String filepath) throws ProjectException {

        logger.log(Level.DEBUG, "Gem StAX builder has started.");
        XMLStreamReader reader;
        File file = fileContent.defineFileWithData(filepath);
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
        GemXmlTag tagName = GemXmlTag.valueOf(toConstantName(elementName));
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
                currentParameters = new GemVisualParameters();
                fillParametersAttribute(reader);
                break;
        }
        return tagName;
    }

    private void processText(String text, GemXmlTag currentXmlTag) {

        switch (currentXmlTag) {
            case NAME:
                currentGem.setName(text);
                break;
            case ORIGIN_COUNTRY:
                currentGem.setOriginCountry(GemOriginCountry.valueOfXmlContent(text));
                break;
            case PRICE:
                currentGem.setPrice(BigDecimal.valueOf(Double.parseDouble(text)));
                break;
            case REGISTRATION_DATE:
                currentGem.setRegistrationDate(LocalDate.parse(text));
                break;
            case CARAT_VALUE:
                ((PreciousGem) currentGem).setCaratValue(Double.parseDouble(text));
                break;
            case WEIGHT:
                ((SemiPreciousGem) currentGem).setWeight(Double.parseDouble(text));
                break;
            case FACET_NUMBER:
                currentParameters.setFacetNumber(Integer.parseInt(text));
                break;
            case TRANSPARENCY:
                currentParameters.setTransparency(Integer.parseInt(text));
                break;
            default:
                throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
        }
    }

    private void finishElement(XMLStreamReader reader) {

        String elementName = reader.getLocalName();
        GemXmlTag tagName = GemXmlTag.valueOf(toConstantName(elementName));
        switch (tagName) {
            case PRECIOUS_GEM:
            case SEMIPRECIOUS_GEM:
                gems.add(currentGem);
                currentGem = null;
                break;
            case PARAMETERS:
                currentGem.setParameters(currentParameters);
                currentParameters = null;
                break;
        }
    }

    private void fillGemAttribute(XMLStreamReader reader) {

        String gemId = reader.getAttributeValue(null, GemXmlTag.ID.toString());
        currentGem.setGemId(gemId);
    }

    private void fillParametersAttribute(XMLStreamReader reader) {

        String colour = reader.getAttributeValue(null, COLOUR.toString());
        currentParameters.setColour(GemColour.valueOfXmlContent(colour));
        String isCertified = reader.getAttributeValue(null, IS_CERTIFIED.toString());
        if (isCertified == null) {
            isCertified = GemVisualParameters.DEFAULT_IS_CERTIFIED;
        }
        currentParameters.setIsCertified(Boolean.parseBoolean(isCertified));
    }

    private String toConstantName(String tagName) {
        return tagName.replace(HYPHEN, UNDERSCORE).toUpperCase();
    }
}
