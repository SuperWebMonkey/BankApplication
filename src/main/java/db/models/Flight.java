package db.models;

public class Flight {
    private int flightId;
    private double price;
    private int airlineId;
    private int originCityId;
    private int destinationCityId;

    public Flight(){}

    public Flight(int flightId, double price, int airlineId, int originCityId, int destinationCityId) {
        this.flightId = flightId;
        this.price = price;
        this.airlineId = airlineId;
        this.originCityId = originCityId;
        this.destinationCityId = destinationCityId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public int getOriginCityId() {
        return originCityId;
    }

    public void setOriginCityId(int originCityId) {
        this.originCityId = originCityId;
    }

    public int getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(int destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public String toString() {
        return flightId + " " + price + " " + airlineId + " " + originCityId + " " + destinationCityId;
    }
}
