package com.example.xml

import com.example.model.User
import javax.xml.transform.Result

class GroovyJDomXmlBuilder extends JDomXmlSupport implements XmlBuilder {

    public GroovyJDomXmlBuilder() {
        super(XmlBuilder.NAMESPACE);
    }

    public void buildXml(List<User> users, Result result) {
        def rootElement = element("Users")
        users.each {user ->
            def userElement = element("User")
            rootElement.addContent(userElement)

            userElement.addContent(elementWithText("Id", user.id))
            userElement.addContent(elementWithText("FirstName", user.firstName))
            userElement.addContent(elementWithText("MiddleName", user.middleName))
            userElement.addContent(elementWithText("LastName", user.lastName))
            userElement.addContent(elementWithText("Birthday", user.birthday))
        }

        transform(rootElement, result)
    }

}