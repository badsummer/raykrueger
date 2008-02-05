package com.example.xml;

import com.example.model.User;
import org.springframework.xml.transform.StringResult;
import org.w3c.dom.Element;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class DomXmlBuilder extends DomSupport implements XmlBuilder {

    public DomXmlBuilder() {
        super(NAMESPACE);
    }

    public void buildXml(List<User> users, Result result) {

        Element rootElement = rootElement("Users");

        for (User user : users) {
            Element userElement = element("User", rootElement);
            userElement.appendChild(elementWithContent("Id", user.getId()));
            userElement.appendChild(elementWithContent("FirstName", user.getFirstName()));
            userElement.appendChild(elementWithContent("MiddleName", user.getMiddleName()));
            userElement.appendChild(elementWithContent("LastName", user.getLastName()));
            userElement.appendChild(elementWithContent("Birthday", user.getBirthday()));
        }

        try {
            transform(new DOMSource(rootElement), result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

        //return rootElement;
    }

}
