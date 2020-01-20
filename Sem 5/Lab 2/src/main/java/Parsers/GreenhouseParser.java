package Parsers;

import Entities.Greenhouse;
import Helpers.ValidatorXML;

public class GreenhouseParser {
    private XMLParser<Greenhouse> parser;

    public GreenhouseParser(XMLParser<Greenhouse> parser) {
        this.parser = parser;
    }

    public Greenhouse parse(String xmlPath, String xsdPath) {
        if (!ValidatorXML.validate(xmlPath, xsdPath))
            return null;
        try {
            return parser.parseItem(xmlPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
