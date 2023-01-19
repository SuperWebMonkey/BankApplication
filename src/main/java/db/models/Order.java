package db.models;

public class Order {
    private int orderId;
    private double payment;
    private int customerId;
    private int staffId;
    private int toursId;
    private int statusId;
    private int paymentId;
    private int drivingCompaniesId;

    public Order() {}

    public Order(int orderId, double payment, int customerId, int staffId, int toursId, int statusId, int paymentId, int drivingCompaniesId) {
        this.orderId = orderId;
        this.payment = payment;
        this.customerId = customerId;
        this.staffId = staffId;
        this.toursId = toursId;
        this.statusId = statusId;
        this.paymentId = paymentId;
        this.drivingCompaniesId = drivingCompaniesId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getToursId() {
        return toursId;
    }

    public void setToursId(int toursId) {
        this.toursId = toursId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getDrivingCompaniesId() {
        return drivingCompaniesId;
    }

    public void setDrivingCompaniesId(int drivingCompaniesId) {
        this.drivingCompaniesId = drivingCompaniesId;
    }

    public String toString() {
        return orderId + " " + payment + " " + customerId + " " + staffId + " " + toursId + " " + statusId + " "
                + paymentId + " " + drivingCompaniesId;
    }
}
