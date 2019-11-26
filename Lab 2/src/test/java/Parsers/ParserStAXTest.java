package Parsers;

import Entities.Greenhouse;
import Helpers.GreenhouseXMLBuilder;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ParserStAXTest {
    private final String validXML = "src\\main\\resources\\Greenhouse.xml";
    private final String invalidXML = "src\\main\\resources\\GreenhouseWrong.xml";
    private final String wrongXML = "src\\main\\resources\\oopsie.xml";

    private final String xsd = "src\\main\\resources\\Greenhouse.xsd";

    private ParserStAX<Greenhouse> stax = new ParserStAX<>(new GreenhouseXMLBuilder());
    private GreenhouseParser parser = new GreenhouseParser(stax);

    @Test
    void parseCorrect() {
        assertNotNull(parser.parse(validXML, xsd));
    }

    @Test
    void parseInvalid() {
        assertNull(parser.parse(invalidXML, xsd));
    }

    @Test
    void parseItemCorrect() throws Exception {
        GreenhouseParser parser = new GreenhouseParser(new ParserStAX<>(new GreenhouseXMLBuilder()));
        assertNotNull(parser.parse(validXML, xsd));
    }

    @Test
    void parseItemInvalid() throws FileNotFoundException, XMLStreamException {
        GreenhouseParser parser = new GreenhouseParser(new ParserStAX<>(new GreenhouseXMLBuilder()));
        assertNull(parser.parse(invalidXML, xsd));
    }

    @Test
    void wrongFile() {
        assertThrows(Exception.class, () -> stax.parseItem(wrongXML));
    }
}