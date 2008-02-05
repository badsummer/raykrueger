package com.example.parse;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class StaxParser {

    private XMLInputFactory factory = XMLInputFactory.newInstance();

    public void parse(Source source) throws Exception {
        XMLStreamReader parser = factory.createXMLStreamReader(source);

        while (parser.hasNext()) {
            parser.next();
            if (parser.getEventType() == XMLEvent.START_ELEMENT) {
                Object o = executeStartElementMethod(parser);
                System.out.println("o: [" + o + "]");
            }
        }

    }

    private void parseHotelShoppingRequest(XMLStreamReader parser) throws XMLStreamException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    }

    private Object executeStartElementMethod(XMLStreamReader parser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = this.getClass().getMethod("start" + parser.getLocalName(), XMLStreamReader.class);
        System.out.println("method: [" + method.getName() + "]");
        return method.invoke(this, parser);
    }

    public void startHotelShoppingRequest(XMLStreamReader parser) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, XMLStreamException {
        if (parser.next() == XMLEvent.START_ELEMENT) {
            executeStartElementMethod(parser);
        }
    }

    public String startPointOfSale(XMLStreamReader parser) throws XMLStreamException {
        int event = parser.next();
        if (event == XMLEvent.CHARACTERS) {
            return parser.getText();
        }
        throw new IllegalStateException("Expected character content from PointOfSale element");
    }

    public static void main(String[] args) throws Exception {
        StaxParser parser = new StaxParser();
        parser.parse(new StreamSource(new FileInputStream("input.xml")));
    }

}
