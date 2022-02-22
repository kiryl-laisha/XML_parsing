package com.laisha.gem.util;

import com.laisha.gem.builder.GemXmlTag;

public class XmlTagConverter {

    private static final XmlTagConverter instance = new XmlTagConverter();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    private XmlTagConverter() {
    }

    public static XmlTagConverter getInstance() {
        return instance;
    }


    public GemXmlTag convertXmlTag(String qName) {

        String tagName = qName.toUpperCase().replace(HYPHEN, UNDERSCORE);
        return GemXmlTag.valueOf(tagName);
    }
}
