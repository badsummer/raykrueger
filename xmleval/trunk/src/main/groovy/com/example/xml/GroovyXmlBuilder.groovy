package com.example.xml

import com.example.model.User
import groovy.xml.MarkupBuilder
import javax.xml.transform.Result
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamSource

public class GroovyXmlBuilder implements XmlBuilder {

    def transformerFactory = TransformerFactory.newInstance()

    public void buildXml(List<User> users, Result result) {
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)

        builder.Users(xmlns:NAMESPACE) {
            users.each {user ->
                User {
                    Id(user.id)
                    FirstName(user.firstName)
                    MiddleName(user.middleName)
                    LastName(user.lastName)
                    Birthday(user.birthday)
                }
            }
        }

        def transformer = transformerFactory.newTransformer()
        transformer.transform(new StreamSource(new StringReader(writer.toString())), result)
    }
}