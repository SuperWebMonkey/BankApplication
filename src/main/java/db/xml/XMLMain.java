package db.xml;

import db.models.AirlineCompany;
import db.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;

public class XMLMain {

    private static final Logger LOGGER = LogManager.getLogger(XMLMain.class);
    private static ArrayList<AirlineCompany> airlineCompanies = new ArrayList<>();
    private static ArrayList<City> cities = new ArrayList<>();
    private static ArrayList<Country> countries = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Hotel> hotels = new ArrayList<>();

    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            document = builder.parse(new File(System.getProperty("user.dir") + "/src/main/resources/db.xml"));
        } catch (Exception e) {
            LOGGER.info(e);
        }
        readXML(document, "airlineCompany");
        readXML(document, "city");
        readXML(document, "country");
        readXML(document, "customer");
        readXML(document, "hotel");
        LOGGER.info(airlineCompanies);
        LOGGER.info(cities);
        LOGGER.info(countries);
        LOGGER.info(customers);
        LOGGER.info(hotels);
    }

    public static void readXML(Document document, String entity) {
        NodeList nodeList = null;
        if (document != null) {
            nodeList = document.getElementsByTagName(entity);
        }
        int length = 0;
        if (nodeList != null) {
            length = nodeList.getLength();
        }
        for (int i = 0; i < length; i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            NamedNodeMap attributes = node.getAttributes();
            switch (entity) {
                case "airlineCompany":
                    AirlineCompany ac = new AirlineCompany();
                    ac.setCompanyId(Integer.parseInt(element.getElementsByTagName("companyId").item(0).getTextContent()));
                    ac.setCompanyName(element.getElementsByTagName("companyName").item(0).getTextContent());
                    airlineCompanies.add(ac);
                    break;
                case "city":
                    City city = new City();
                    city.setCityId(Integer.parseInt(element.getElementsByTagName("cityId").item(0).getTextContent()));
                    city.setCityName(element.getElementsByTagName("name").item(0).getTextContent());
                    city.setCountryId(Integer.parseInt(element.getElementsByTagName("countryId").item(0).getTextContent()));
                    cities.add(city);
                    break;
                case "country":
                    Country country = new Country();
                    country.setCountryId(Integer.parseInt(element.getElementsByTagName("countryId").item(0).getTextContent()));
                    country.setCountryName(element.getElementsByTagName("name").item(0).getTextContent());
                    countries.add(country);
                    break;
                case "customer":
                    Customer customer = new Customer();
                    customer.setCustomerId(Integer.parseInt(element.getElementsByTagName("customerId").item(0).getTextContent()));
                    customer.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
                    customer.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                    customer.setPhone(element.getElementsByTagName("phone").item(0).getTextContent());
                    customers.add(customer);
                    break;
                case "hotel":
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(Integer.parseInt(element.getElementsByTagName("hotelId").item(0).getTextContent()));
                    hotel.setHotelName(element.getElementsByTagName("name").item(0).getTextContent());
                    hotel.setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
                    hotel.setCityId(Integer.parseInt(element.getElementsByTagName("cityId").item(0).getTextContent()));
                    hotels.add(hotel);
                    break;
            }
        }
    }
}
