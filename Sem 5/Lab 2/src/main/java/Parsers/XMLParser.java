package Parsers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface XMLParser<T> {
    public T parseItem(String xmlFile) throws ParserConfigurationException, SAXException, IOException, XMLStreamException;
}
