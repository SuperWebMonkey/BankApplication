package db.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "country")
@XmlType(propOrder = {"countryId", "countryName"})
public class Country {
    private int countryId;
    private String countryName;

    public Country() {
    }

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    @XmlElement(name = "countryId")
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    @XmlElement(name = "countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return this.countryId + " " + this.countryName;
    }
}
