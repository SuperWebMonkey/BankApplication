package db.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "airlineCompany")
@XmlType(propOrder = {"companyId", "companyName"})
public class AirlineCompany {
    @JsonProperty("id")
    private int companyId;
    @JsonProperty("name")
    private String companyName;

    public AirlineCompany() {
    }

    public AirlineCompany(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    @XmlElement(name = "companyId")
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @XmlElement(name = "companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String toString() {
        return this.companyId + " " + this.companyName;
    }
}
