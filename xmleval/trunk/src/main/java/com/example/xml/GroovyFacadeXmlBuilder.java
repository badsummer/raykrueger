package com.example.xml;

import com.example.model.User;
import groovy.lang.GroovyClassLoader;

import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class GroovyFacadeXmlBuilder implements XmlBuilder {

    private final XmlBuilder xmlBuilder;

    public GroovyFacadeXmlBuilder() {
        this.xmlBuilder = newXmlBuilder();
    }

    public void buildXml(List<User> users, Result result) throws TransformerException {
        xmlBuilder.buildXml(users, result);
    }

    private XmlBuilder newXmlBuilder() {
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader());
            Class cls = classLoader.parseClass(getClass().getResourceAsStream("GroovyStaxBuilder.groovy"));
            return (XmlBuilder) cls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
