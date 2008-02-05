package com.example.xml

import com.ctc.wstx.stax.WstxOutputFactory
import com.example.model.User
import javax.xml.transform.Result
//import org.sensatic.dynawork.groovy.StaxBuilder

public class GroovyStaxBuilder implements XmlBuilder {

    public void buildXml(List<User> users, Result result) {
        throw new UnsupportedOperationException()
/*
        def factory = new WstxOutputFactory()
        def writer = new StringWriter()
        def xml = new StaxBuilder(
                factory.createXMLStreamWriter(result)
        )

        xml.Users(xmlns:NAMESPACE) {
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
*/
    }
}