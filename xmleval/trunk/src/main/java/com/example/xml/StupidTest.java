package com.example.xml;

import com.example.model.UserGenerator;

import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;
import java.io.StringWriter;

import org.springframework.xml.transform.StringSource;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class StupidTest {

    public static void main(String[] args) throws Exception {
        JDomXmlBuilder builder = new JDomXmlBuilder();
        StringWriter buffer = new StringWriter();
        builder.buildXml(UserGenerator.generateUsers(1), new StreamResult(buffer));

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(StupidTest.class.getResource("/user.xsd"));

        Validator validator = schema.newValidator();
        validator.validate(new StringSource(buffer.toString()));


    }
}
