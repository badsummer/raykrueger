package com.example.xml;

import com.example.model.User;
import com.example.model.UserGenerator;
import junit.framework.TestCase;
import org.springframework.xml.transform.StringResult;

import javax.xml.transform.TransformerException;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class TimeTest extends TestCase {

    private static final int ITERATIONS = 50;
    private static final List<User> users = UserGenerator.generateUsers(50);

    public void testStax() throws Exception {
        timeBuilder(users, new StaxXmlBuilder());
    }

    public void testJDom() throws Exception {
        timeBuilder(users, new JDomXmlBuilder());
    }

    public void testDom() throws Exception {
        timeBuilder(users, new DomXmlBuilder());
    }

    public void testJaxb() throws Exception {
        timeBuilder(users, new JaxbXmlBuilder());
    }

    public void testGroovyMarkupBuilder() throws Exception {
        timeBuilder(users, new GroovyXmlBuilder());
    }

    public void testGroovyJdom() throws Exception {
        timeBuilder(users, new GroovyJDomXmlBuilder());
    }

/*
    public void testGroovyStaxBuilder() throws Exception {
        timeBuilder(users, new GroovyStaxBuilder());
    }
*/

    private void timeBuilder(List<User> users, XmlBuilder builder) throws TransformerException {
        double totalTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            totalTime += runBuilder(users, builder);
        }

        System.out.println(builder.getClass().getSimpleName() + ": [" + avg(totalTime, ITERATIONS) + "]");
    }

    private double avg(double time, int count) {
        return time / count;
    }

    private long runBuilder(List<User> users, XmlBuilder builder) throws TransformerException {
        long start = System.nanoTime();
        builder.buildXml(users, new StringResult());
        return (System.nanoTime() - start) / 1000000;
    }

}
