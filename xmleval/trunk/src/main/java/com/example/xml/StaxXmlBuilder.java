package com.example.xml;

import com.example.model.User;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class StaxXmlBuilder implements XmlBuilder {

    private XMLOutputFactory xmlOutputFactory;

    public StaxXmlBuilder() {
        xmlOutputFactory = XMLOutputFactory.newInstance();
    }

    public void buildXml(final List<User> users, Result result) throws TransformerException {
        XMLStreamWriter xml = createXmlStreamWriter(result);

        try {
            writeDocument(xml, users);
        } catch (XMLStreamException e) {
            throw new TransformerException("Unable to write document", e);
        }

    }

    private void writeDocument(XMLStreamWriter xml, List<User> users) throws XMLStreamException {
        xml.writeStartDocument();
        writeUsers(xml, users);
        xml.writeEndDocument();
    }

    private void writeUsers(XMLStreamWriter xml, List<User> users) throws XMLStreamException {
        xml.writeStartElement("Users");
        xml.writeDefaultNamespace(NAMESPACE);
        for (User user : users) {
            writeUser(xml, user);
        }
        xml.writeEndElement();
    }

    private void writeUser(XMLStreamWriter xml, User user) throws XMLStreamException {
        xml.writeStartElement("User");
        writeUserContents(xml, user);
        xml.writeEndElement();
    }

    private void writeUserContents(XMLStreamWriter xml, User user) throws XMLStreamException {
        writeElementWithText(xml, "Id", user.getId());
        writeElementWithText(xml, "FirstName", user.getFirstName());
        writeElementWithText(xml, "MiddleName", user.getMiddleName());
        writeElementWithText(xml, "LastName", user.getLastName());
        writeElementWithText(xml, "Birthday", user.getBirthday());
    }

    private void writeElementWithText(XMLStreamWriter xml, String name, Object text) throws XMLStreamException {
        xml.writeStartElement(name);
        xml.writeCharacters(String.valueOf(text));
        xml.writeEndElement();
    }

    private XMLStreamWriter createXmlStreamWriter(Result result) throws TransformerException {
        try {
            return xmlOutputFactory.createXMLStreamWriter(result);
        } catch (XMLStreamException e) {
            throw new TransformerException("Unable to create XMLStreamWriter", e);
        }
    }
}
