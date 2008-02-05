package com.example.xml;

import com.example.model.User;
import org.jdom.Element;

import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class JDomXmlBuilder extends JDomXmlSupport implements XmlBuilder {

    public JDomXmlBuilder() {
        super(NAMESPACE);
    }

    public void buildXml(List<User> users, Result result) throws TransformerException {

        Element rootElement = element("Users");

        for (User user : users) {
            Element userElement = element("User");
            rootElement.addContent(userElement);

            userElement.addContent(elementWithText("Id", user.getId()));
            userElement.addContent(elementWithText("FirstName", user.getFirstName()));
            userElement.addContent(elementWithText("MiddleName", user.getMiddleName()));
            userElement.addContent(elementWithText("LastName", user.getLastName()));
            userElement.addContent(elementWithText("Birthday", user.getBirthday()));
        }

        transform(rootElement, result);
    }

}
