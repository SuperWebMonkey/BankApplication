package db.models;

public class Tours {
    private int tourId;
    private String tourName;
    private int hotelId;
    private int flightToId;
    private int flightFromId;

    public Tours() {}

    public Tours(int tourId, String tourName, int hotelId, int flightToId, int flightFromId) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.hotelId = hotelId;
        this.flightToId = flightToId;
        this.flightFromId = flightFromId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getFlightToId() {
        return flightToId;
    }

    public void setFlightToId(int flightToId) {
        this.flightToId = flightToId;
    }

    public int getFlightFromId() {
        return flightFromId;
    }

    public void setFlightFromId(int flightFromId) {
        this.flightFromId = flightFromId;
    }

    public String toString() {
        return this.tourId + " " + this.tourName + " " + this.hotelId + " " + this.flightToId + " " + this.flightFromId;
    }
}
