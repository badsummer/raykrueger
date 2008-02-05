package com.example.xml;

import com.example.model.User;
import com.example.model.UserGenerator;
import junit.framework.TestCase;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class ValidationTest extends TestCase {

    private Validator validator;
    private static List<User> users = UserGenerator.generateUsers(10);

    @Override
    protected void setUp() throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(StupidTest.class.getResource("/user.xsd"));

        validator = schema.newValidator();
    }

    public void testStax() throws Exception {
        buildAndValidate(new StaxXmlBuilder());
    }

    public void testJDom() throws Exception {
        buildAndValidate(new JDomXmlBuilder());
    }

    public void testDom() throws Exception {
        buildAndValidate(new DomXmlBuilder());
    }

/*
    public void testJaxb() throws Exception {
        buildAndValidate(new JaxbXmlBuilder());
    }
*/

    public void testGroovyMarkUpBuilder() throws Exception {
        buildAndValidate(new GroovyXmlBuilder());
    }

/*
    public void testGroovyStaxBuilder() throws Exception {
        buildAndValidate(new GroovyStaxBuilder());
    }
*/

    public void testGroovyJdomXmlBuilder() throws Exception {
        buildAndValidate(new GroovyJDomXmlBuilder());
    }

    private void buildAndValidate(XmlBuilder builder) throws TransformerException, SAXException, IOException {
        StringWriter buffer = new StringWriter();
        builder.buildXml(users, new StreamResult(buffer));
        //System.out.println("result: [" + buffer + "]");
        validator.validate(new StreamSource(new StringReader(buffer.toString())));
    }

}
