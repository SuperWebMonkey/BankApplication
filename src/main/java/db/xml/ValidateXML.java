package db.xml;


import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class ValidateXML {
    private static final Logger LOGGER = LogManager.getLogger(XMLMain.class);

    public static void main(String[] args) {
        boolean isValid = validateXMLSchema("src/main/resources/db.xsd", "src/main/resources/db.xml");

        if (isValid) {
            LOGGER.info("db.xml" + " is valid against " + "db.xsd");
        } else {
            LOGGER.info("db.xml" + " is not valid against " + "db.xsd");
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e1) {
            System.out.println("SAX Exception: " + e1.getMessage());
            return false;
        }

        return true;

    }
}
