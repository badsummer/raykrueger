package com.example.xml;

import com.example.model.User;

import javax.xml.transform.TransformerException;
import javax.xml.transform.Result;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public interface XmlBuilder {

    public static final String NAMESPACE = "http://www.example.com/";

    void buildXml(List<User> users, Result result) throws TransformerException;
}
