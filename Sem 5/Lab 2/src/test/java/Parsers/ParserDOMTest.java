package Parsers;

import Entities.Greenhouse;
import Helpers.GreenhouseXMLBuilder;
import Helpers.ValidatorXML;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserDOMTest {
    private final String validXML = "src\\main\\resources\\Greenhouse.xml";
    private final String invalidXML = "src\\main\\resources\\GreenhouseWrong.xml";
    private final String wrongXML = "src\\main\\resources\\oopsie.xml";

    private final String xsd = "src\\main\\resources\\Greenhouse.xsd";

    private ParserDOM<Greenhouse> dom = new ParserDOM<>(new GreenhouseXMLBuilder());
    private GreenhouseParser parser = new GreenhouseParser(dom);

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
        GreenhouseParser parser = new GreenhouseParser(new ParserDOM<>(new GreenhouseXMLBuilder()));
        assertNotNull(parser.parse(validXML, xsd));
    }

    @Test
    void parseItemInvalid() throws IOException, SAXException, ParserConfigurationException {
        GreenhouseParser parser = new GreenhouseParser(new ParserDOM<>(new GreenhouseXMLBuilder()));
        assertNull(parser.parse(invalidXML, xsd));
    }

    @Test
    void wrongFile() {
        assertThrows(Exception.class, () -> dom.parseItem(wrongXML));
    }
}