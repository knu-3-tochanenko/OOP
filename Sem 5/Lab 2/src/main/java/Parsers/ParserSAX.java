package Parsers;

import Helpers.XMLBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParserSAX<T> implements XMLParser {

    private XMLBuilder<T> builder;

    public ParserSAX(XMLBuilder<T> builder) {
        this.builder = builder;
    }

    @Override
    public Object parseItem(String xmlFile) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser = saxParserFactory.newSAXParser();
        HandlerSAX handler = new HandlerSAX<>(builder);
        parser.parse(new File(xmlFile), handler);
        return builder.getRoot();
    }
}
