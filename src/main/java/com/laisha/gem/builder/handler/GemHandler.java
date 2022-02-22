package com.laisha.gem.builder.handler;

import com.laisha.gem.builder.GemXmlTag;
import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.entity.GemVisualParameters;
import com.laisha.gem.entity.PreciousGem;
import com.laisha.gem.entity.SemiPreciousGem;
import com.laisha.gem.entity.enums.GemOriginCountry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


public class GemHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private static final String DELIMITER_HYPHEN = "-";
    private static final String DELIMITER_UNDERSCORE = "_";

    private final Set<AbstractGem> gems;
    private final EnumSet<GemXmlTag> containedText;
    private AbstractGem currentGem;
    private GemXmlTag currentXmlTag;

    public GemHandler() {
        gems = new HashSet<>();
        containedText = EnumSet.range(GemXmlTag.CERTIFIED, GemXmlTag.WEIGHT);
    }

    @Override
    public void startDocument() {
        logger.log(Level.DEBUG, "Gem SAX handler has started.");
        currentXmlTag = null;
    }

    @Override
    public void endDocument() {
        logger.log(Level.DEBUG, "Gem SAX handler has finished.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        String preciousGemTag = GemXmlTag.PRECIOUS_GEM.toString();
        String semipreciousGemTag = GemXmlTag.SEMIPRECIOUS_GEM.toString();
        if (preciousGemTag.equals(qName) || semipreciousGemTag.equals(qName)) {
            currentGem = preciousGemTag.equals(qName) ? new PreciousGem() : new SemiPreciousGem();
            currentGem.setGemId(attrs.getValue(GemXmlTag.ID.toString()));
        }
        String gemParametersTag = GemXmlTag.PARAMETERS.toString();
        if (gemParametersTag.equals(qName)) {
            String gemColour = attrs.getValue(GemXmlTag.COLOUR.toString());
            currentGem.getParameters().setColour(gemColour);
            String certifiedParameter = attrs.getValue(GemXmlTag.CERTIFIED.toString());
            boolean certifiedGem = certifiedParameter == null ?
                    GemVisualParameters.DEFAULT_CERTIFIED_GEM :
                    Boolean.parseBoolean(certifiedParameter);
            currentGem.getParameters().setCertified(certifiedGem);
        }
        GemXmlTag temporaryTag = defineXmlTag(qName);
        if (containedText.contains(temporaryTag)) {
            currentXmlTag = temporaryTag;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {

        String currentData = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    currentGem.setName(currentData);
                    break;
                case ORIGIN_COUNTRY:
                    currentGem.setOriginCountry(GemOriginCountry.valueOfXmlContent(currentData));
                    break;
                case REGISTRATION_DATE:
                    currentGem.setRegistrationDate(LocalDate.parse(currentData));
                    break;
                case PRICE:
                    currentGem.setPrice(BigDecimal.valueOf(Double.parseDouble(currentData)));
                    break;
                case VALUE:
                    ((PreciousGem) currentGem).setValue(Double.parseDouble(currentData));
                    break;
                case WEIGHT:
                    ((SemiPreciousGem) currentGem).setWeight(Double.parseDouble(currentData));
                    break;
                case FACET_NUMBER:
                    currentGem.getParameters().setFacetNumber(Integer.parseInt(currentData));
                    break;
                case TRANSPARENCY:
                    currentGem.getParameters().setTransparency(Integer.parseInt(currentData));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        String preciousGemTag = GemXmlTag.PRECIOUS_GEM.toString();
        String semipreciousGemTag = GemXmlTag.SEMIPRECIOUS_GEM.toString();
        if (preciousGemTag.equals(qName) || semipreciousGemTag.equals(qName)) {
            gems.add(currentGem);
        }
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    private GemXmlTag defineXmlTag(String qName) {

        String tag = qName.toUpperCase().replace(DELIMITER_HYPHEN, DELIMITER_UNDERSCORE);
        return GemXmlTag.valueOf(tag);
    }
}
