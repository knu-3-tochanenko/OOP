import Entities.Greenhouse;
import Helpers.GreenhouseXMLBuilder;
import Parsers.GreenhouseParser;
import Parsers.ParserSAX;
import Parsers.ParserStAX;

public class Main {

    static final String XML = "src\\main\\resources\\Greenhouse.xml";
    static final String XSD = "src\\main\\resources\\Greenhouse.xsd";

    public static void main(String[] args) {
        ParserSAX<Greenhouse> sax = new ParserSAX<>(new GreenhouseXMLBuilder());
        ParserStAX<Greenhouse> stax = new ParserStAX<>(new GreenhouseXMLBuilder());
        GreenhouseParser parser = new GreenhouseParser(stax);
        Greenhouse greenhouse = parser.parse(XML, XSD);

        System.out.println(greenhouse);
    }
}
