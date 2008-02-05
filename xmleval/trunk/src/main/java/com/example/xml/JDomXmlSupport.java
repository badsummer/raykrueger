package com.example.xml;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.Document;
import org.jdom.transform.JDOMSource;
import org.jdom.output.XMLOutputter;
import org.springframework.xml.transform.TransformerObjectSupport;

import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class JDomXmlSupport extends TransformerObjectSupport {

    private final Namespace defaultNamespace;

    public JDomXmlSupport(String defaultNamespace) {
        this.defaultNamespace = Namespace.getNamespace(defaultNamespace);
    }

    public Element element(String name) {
        return new Element(name, defaultNamespace);
    }

    public Element element(String name, Map<String, String> attributes) {
        Element element = element(name);
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            element.setAttribute(entry.getKey(), entry.getValue());
        }
        return element;
    }

    public Element elementWithText(String name, Object text) {
        Element element = element(name);
        return setText(element, text);
    }

    public Element elementWithText(String name, Map<String, String> attributes, Object text) {
        Element element = element(name, attributes);
        return setText(element, text);
    }

    public Element setText(Element child, Object text) {
        if (text != null) {
            child.setText(String.valueOf(text));
        }
        return child;
    }

    protected void transform(final Element rootElement, Result result) throws TransformerException {
        transform(new JDOMSource(new Document(rootElement)), result);
    }
}
