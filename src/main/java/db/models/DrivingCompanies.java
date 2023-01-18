package db.models;

public class DrivingCompanies {
    private int drivingId;
    private double price;
    private int cityId;

    public DrivingCompanies() {}

    public DrivingCompanies(int drivingId, double price, int cityId) {
        this.drivingId = drivingId;
        this.price = price;
        this.cityId = cityId;
    }

    public int getDrivingId() {
        return drivingId;
    }

    public void setDrivingId(int drivingId) {
        this.drivingId = drivingId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String toString() {
        return this.drivingId + " " + price + " " + cityId;
    }
}
