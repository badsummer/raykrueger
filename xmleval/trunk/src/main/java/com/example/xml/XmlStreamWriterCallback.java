package com.example.xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public interface XmlStreamWriterCallback {
    void doInWriter(XMLStreamWriter xml) throws XMLStreamException;
}
