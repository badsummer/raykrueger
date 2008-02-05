package com.example.xml;

import com.example.model.User;
import com.example.schemas.Users;
import org.joda.time.LocalDate;
import org.springframework.xml.transform.TransformerObjectSupport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Result;
import java.util.List;
import java.math.BigInteger;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class JaxbXmlBuilder extends TransformerObjectSupport implements XmlBuilder {

    public void buildXml(List<User> users, Result result) {
        Users schemaUsers = new Users();

        for (User user : users) {
            com.example.schemas.User schemaUser = new com.example.schemas.User();
            schemaUsers.getUser().add(schemaUser);
            schemaUser.setId(new BigInteger(user.getId().toString()));
            schemaUser.setFirstName(user.getFirstName());
            schemaUser.setMiddleName(user.getMiddleName());
            schemaUser.setLastName(user.getLastName());

            LocalDate birthday = user.getBirthday();

            XMLGregorianCalendar schemaBirthday = newDataTypeFactory().newXMLGregorianCalendar(
                    birthday.getYear(), birthday.getMonthOfYear(), birthday.getDayOfMonth(), 0, 0, 0, 0, 0);
            schemaUser.setBirthday(schemaBirthday);
        }

        Marshaller marshaller = createMarshaller();
        marshall(marshaller, schemaUsers, result);

    }

    private void marshall(Marshaller marshaller, Users users, Result result) {
        try {
            marshaller.marshal(users, result);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private Marshaller createMarshaller() {
        try {
            return JAXBContext.newInstance(Users.class).createMarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private DatatypeFactory newDataTypeFactory() {
        try {
            return DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
