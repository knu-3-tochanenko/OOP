package Parsers;

import Entities.Greenhouse;
import Helpers.GreenhouseXMLBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserStAXTest {
    private final String validXML = "src\\main\\resources\\Greenhouse.xml";
    private final String invalidXML = "src\\main\\resources\\GreenhouseWrong.xml";
    private final String wrongXML = "src\\main\\resources\\oopsie.xml";

    private final String xsd = "src\\main\\resources\\Greenhouse.xsd";

    private ParserStAX<Greenhouse> sax = new ParserStAX<>(new GreenhouseXMLBuilder());
    private GreenhouseParser parser = new GreenhouseParser(sax);

    @Test
    void parseCorrect() {
        assertNotNull(parser.parse(validXML, xsd));
    }

    @Test
    void parseInvalid() {
        assertNull(parser.parse(invalidXML, xsd));
    }

    @Test
    void parseGemCorrect() throws Exception {
        sax.parseItem(validXML);
    }

    @Test
    void parseGemInvalid() {
        assertDoesNotThrow(() -> sax.parseItem(invalidXML));
    }

    @Test
    void wrongFile() {
        assertThrows(Exception.class, () -> sax.parseItem(wrongXML));
    }
}