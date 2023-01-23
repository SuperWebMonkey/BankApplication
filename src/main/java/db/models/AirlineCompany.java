package db.models;

public class AirlineCompany {
    private int companyId;
    private String companyName;

    public AirlineCompany() {
    }

    public AirlineCompany(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

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
