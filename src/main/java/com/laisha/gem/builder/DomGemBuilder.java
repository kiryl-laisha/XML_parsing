package com.laisha.gem.builder;

import com.laisha.gem.entity.AbstractGem;
import com.laisha.gem.entity.GemVisualParameters;
import com.laisha.gem.entity.PreciousGem;
import com.laisha.gem.entity.SemiPreciousGem;
import com.laisha.gem.entity.enums.GemOriginCountry;
import com.laisha.gem.exception.ProjectException;
import com.laisha.gem.util.ContentedFileDefinition;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomGemBuilder extends AbstractGemBuilder {

    private static final Logger logger = LogManager.getLogger();
    private static final ContentedFileDefinition fileContent = ContentedFileDefinition.getInstance();
    private DocumentBuilder documentBuilder;
    private AbstractGem currentGem;

    public DomGemBuilder() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.WARN, "Parser can't be created with " +
                    "required parameters.", e);
        }
    }

    @Override
    protected void buildGems(String filepath) throws ProjectException {

        Document document;
        File file = fileContent.defineFileForData(filepath);
        try {
            document = documentBuilder.parse(file);
        } catch (IOException | SAXException e) {
            throw new ProjectException("Exception was occurred during DOM parsing. ", e);
        }
        Element root = document.getDocumentElement();
        List<NodeList> gemElements = new ArrayList<>();
        gemElements.add(root.getElementsByTagName(GemXmlTag.PRECIOUS_GEM.toString()));
        gemElements.add(root.getElementsByTagName(GemXmlTag.SEMIPRECIOUS_GEM.toString()));
        for (NodeList nodeList : gemElements) {
            int length = nodeList.getLength();
            for (int j = 0; j < length; j++) {
                Element gemElement = (Element) nodeList.item(j);
                buildAbstractGem(gemElement);
                gems.add(currentGem);
            }
        }
    }

    private void buildAbstractGem(Element gemElement) {

        if (gemElement.getTagName().equals(GemXmlTag.PRECIOUS_GEM.toString())) {
            currentGem = new PreciousGem();
            buildPreciousGem(gemElement);
        } else {
            currentGem = new SemiPreciousGem();
            buildSemipreciousGem(gemElement);
        }
        String gemId = gemElement.getAttribute(GemXmlTag.ID.toString());
        currentGem.setGemId(gemId);
        String name = getElementTextContent(gemElement, GemXmlTag.NAME.toString());
        currentGem.setName(name);
        String originCountry =
                getElementTextContent(gemElement, GemXmlTag.ORIGIN_COUNTRY.toString());
        currentGem.setOriginCountry(GemOriginCountry.valueOfXmlContent(originCountry));
        String registrationDate =
                getElementTextContent(gemElement, GemXmlTag.REGISTRATION_DATE.toString());
        currentGem.setRegistrationDate(LocalDate.parse(registrationDate));
        String price = getElementTextContent(gemElement, GemXmlTag.PRICE.toString());
        currentGem.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
        Element parameters =
                (Element) gemElement.getElementsByTagName(GemXmlTag.PARAMETERS.toString()).item(0);
        String facetNumber = getElementTextContent(parameters, GemXmlTag.FACET_NUMBER.toString());
        String transparency = getElementTextContent(parameters, GemXmlTag.TRANSPARENCY.toString());
        String colour = parameters.getAttribute(GemXmlTag.COLOUR.toString());
        String certified = parameters.getAttribute(GemXmlTag.COLOUR.toString());
        if (!certified.isEmpty()) {
            certified = String.valueOf(GemVisualParameters.DEFAULT_CERTIFIED_GEM);
        }
        GemVisualParameters visualParameters = new GemVisualParameters(
                colour,
                Integer.parseInt(facetNumber),
                Integer.parseInt(transparency),
                Boolean.parseBoolean(certified));
        currentGem.setParameters(visualParameters);
    }

    private void buildPreciousGem(Element gemElement) {

        String value = getElementTextContent(gemElement, GemXmlTag.VALUE.toString()).toUpperCase();
        ((PreciousGem) currentGem).setValue(Double.parseDouble(value));
    }

    private void buildSemipreciousGem(Element gemElement) {

        String weight = getElementTextContent(gemElement, GemXmlTag.WEIGHT.toString()).toUpperCase();
        ((SemiPreciousGem) currentGem).setWeight(Double.parseDouble(weight));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodes = element.getElementsByTagName(elementName);
        Node node = nodes.item(0);
        return node.getTextContent();
    }
}
