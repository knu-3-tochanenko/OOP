package Parsers;

import Helpers.XMLBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSAX<T> extends DefaultHandler {
    private XMLBuilder<T> builder;

    public HandlerSAX(XMLBuilder<T> builder) {
        this.builder = builder;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        builder.addOpenTag(qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            builder.addAttribute(attributes.getQName(i), attributes.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        builder.addCloseTag(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        data = data.replace("\n", "").trim();
        if (!data.equals(""))
            builder.addData(data);
    }
}