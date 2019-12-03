package Helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorXMLTest {

    @Test
    void validateCorrect() {
        String xml = "src\\main\\resources\\Greenhouse.xml";
        String xsd = "src\\main\\resources\\Greenhouse.xsd";
        assertTrue(ValidatorXML.validate(xml, xsd));
    }

    @Test
    void validateInvalid() {
        String xml = "src\\main\\resources\\GreenhouseWrong.xml";
        String xsd = "src\\main\\resources\\Greenhouse.xsd";
        assertFalse(ValidatorXML.validate(xml, xsd));
    }

    @Test
    void validateMissingFile() {
        String xml = "";
        String xsd = "";
        assertFalse(ValidatorXML.validate(xml, xsd));
    }
}