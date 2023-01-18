package db.models;

public class AirlineCompanies {
    private int companyId;
    private String companyName;

    public AirlineCompanies(){}

    public AirlineCompanies(int company_id, String company_name){
        this.companyId = company_id;
        this.companyName = company_name;
    }

    public void setCompanyId(int company_id){
        this.companyId = company_id;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public int getCompanyId(){
        return companyId;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String toString(){
        return this.companyId + " " + this.companyName;
    }
}