package com.laisha.gem.builder.handler;

import com.laisha.gem.builder.GemXmlTag;
import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.entity.GemVisualParameters;
import com.laisha.gem.entity.PreciousGem;
import com.laisha.gem.entity.SemiPreciousGem;
import com.laisha.gem.entity.GemColour;
import com.laisha.gem.entity.GemOriginCountry;
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
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private final Set<AbstractGem> gems;
    private final EnumSet<GemXmlTag> containedText;
    private AbstractGem currentGem;
    private GemVisualParameters currentParameters;
    private GemXmlTag currentXmlTag;

    public GemHandler() {

        gems = new HashSet<>();
        containedText = EnumSet.range(GemXmlTag.COLOUR, GemXmlTag.WEIGHT);
    }

    @Override
    public void startDocument() {
        logger.log(Level.DEBUG, "Gem SAX handler has started.");
    }

    @Override
    public void endDocument() {

        logger.log(Level.DEBUG, "Gem SAX handler has finished.");
        currentXmlTag = null;
        currentGem = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {

        String preciousGemTag = GemXmlTag.PRECIOUS_GEM.toString();
        String semipreciousGemTag = GemXmlTag.SEMIPRECIOUS_GEM.toString();
        if (preciousGemTag.equals(qName) || semipreciousGemTag.equals(qName)) {
            currentGem = preciousGemTag.equals(qName) ? new PreciousGem() : new SemiPreciousGem();
            currentGem.setGemId(attrs.getValue(GemXmlTag.ID.toString()));
            return;
        }
        String gemParametersTag = GemXmlTag.PARAMETERS.toString();
        if (gemParametersTag.equals(qName)) {
            currentParameters = new GemVisualParameters();
            String colour = attrs.getValue(GemXmlTag.COLOUR.toString());
            currentParameters.setColour(GemColour.valueOfXmlContent(colour));
            String isCertified = attrs.getValue(GemXmlTag.IS_CERTIFIED.toString());
            if (isCertified == null) {
                isCertified = GemVisualParameters.DEFAULT_IS_CERTIFIED;
            }
            currentParameters.setIsCertified(Boolean.parseBoolean(isCertified));
            return;
        }
        GemXmlTag temporaryTag = GemXmlTag.valueOf(toConstantName(qName));
        if (containedText.contains(temporaryTag)) {
            currentXmlTag = temporaryTag;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String text = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME:
                    currentGem.setName(text);
                    break;
                case ORIGIN_COUNTRY:
                    currentGem.setOriginCountry(GemOriginCountry.valueOfXmlContent(text));
                    break;
                case REGISTRATION_DATE:
                    currentGem.setRegistrationDate(LocalDate.parse(text));
                    break;
                case PRICE:
                    currentGem.setPrice(BigDecimal.valueOf(Double.parseDouble(text)));
                    break;
                case FACET_NUMBER:
                    currentParameters.setFacetNumber(Integer.parseInt(text));
                    break;
                case TRANSPARENCY:
                    currentParameters.setTransparency(Integer.parseInt(text));
                    break;
                case CARAT_VALUE:
                    ((PreciousGem) currentGem).setCaratValue(Double.parseDouble(text));
                    break;
                case WEIGHT:
                    ((SemiPreciousGem) currentGem).setWeight(Double.parseDouble(text));
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
        String gemParametersTag = GemXmlTag.PARAMETERS.toString();
        if (gemParametersTag.equals(qName)) {
            currentGem.setParameters(currentParameters);
            currentParameters = null;
        }
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    private String toConstantName(String tagName) {
        return tagName.replace(HYPHEN, UNDERSCORE).toUpperCase();
    }
}
