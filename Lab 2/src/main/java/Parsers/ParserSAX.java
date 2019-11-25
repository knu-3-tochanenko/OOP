package Parsers;

import Helpers.XMLBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParserSAX<T> implements XMLParser {

    private XMLBuilder<T> builder;

    public ParserSAX(XMLBuilder<T> builder) {
        this.builder = builder;
    }

    @Override
    public Object parseItem(String xmlFile) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            HandlerSAX handler = new HandlerSAX<>(builder);
            parser.parse(new File(xmlFile), handler);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.INFO, "Something went wrong.", e);
        }
        return builder.getRoot();
    }
}
