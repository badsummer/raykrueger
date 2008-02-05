package com.example.xml;

import org.springframework.xml.transform.TransformerObjectSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class DomSupport extends TransformerObjectSupport {

    private Document document;
    private final String defaultNameSpace;

    public DomSupport(String defaultNameSpace) {
        this.defaultNameSpace = defaultNameSpace;
    }

    private Document newDocument() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Unable to create new document", e);
        }
    }

    public Element element(String name) {
        return document.createElementNS(defaultNameSpace, name);
    }

    public Element rootElement(String name) {
        this.document = newDocument();
        return element(name, document);
    }

    public Element element(String name, Node parent) {
        Element child = element(name);
        parent.appendChild(child);
        return child;
    }

    public Element element(String name, Map<String, String> attributes) {
        Element element = element(name);
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            element.setAttribute(attribute.getKey(), attribute.getValue());
        }
        return element;
    }

    public Element element(String name, Map<String, String> attributes, Node parent) {
        Element child = element(name, attributes);
        parent.appendChild(child);
        return child;
    }

    public Element elementWithContent(String name, Object content) {
        Element element = element(name);
        setContent(element, content);
        return element;
    }

    public Element elementWithContent(String name, Object content, Map<String, String> attributes) {
        Element child = element(name, attributes);
        setContent(child, content);
        return child;
    }

    public void setContent(Element element, Object content) {
        if (content != null) {
            element.setTextContent(String.valueOf(content));
        }
    }

    public Element elementWithContent(String name, Object content, Node parent) {
        Element child = elementWithContent(name, content);
        parent.appendChild(child);
        return child;
    }

    public Element elementWithContent(String name, Object content, Map<String, String> attributes, Node parent) {
        Element child = elementWithContent(name, content, attributes);
        parent.appendChild(child);
        return child;
    }

    public Document getDocument() {
        return document;
    }

    public void toResult(Result result) throws TransformerException {
        transform(new DOMSource(document), result);
    }

}
