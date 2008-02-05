package com.example.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class XmlStreamWriterTemplate {

    private final XMLStreamWriter xmlStreamWriter;

    public XmlStreamWriterTemplate(XMLStreamWriter xml) {
        this.xmlStreamWriter = xml;
    }

    public void writeDocument(XmlStreamWriterCallback callback) {
        try {
            xmlStreamWriter.writeStartDocument();
            callback.doInWriter(xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new XmlStreamException("Error writing Document", e);
        }
    }

    public void writeElement(String name, XmlStreamWriterCallback callback) {
        try {
            xmlStreamWriter.writeStartElement(name);
            callback.doInWriter(xmlStreamWriter);
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            throw new XmlStreamException("Error writing element [" + name + "]", e);
        }
    }

    public void writeElementWithText(String name, final Object text) {
        writeElement(name, new XmlStreamWriterCallback() {
            public void doInWriter(XMLStreamWriter xml) throws XMLStreamException {
                xml.writeCharacters(String.valueOf(text));
            }
        });
    }

    public XMLStreamWriter getXMLStreamWriter() {
        return xmlStreamWriter;
    }
}
