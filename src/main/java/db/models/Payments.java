package db.models;

public class Payments {
    public Payments() {}

    private int paymentId;
    private double amount;
    private int paymentTypeId;

    public Payments(int paymentId, double amount, int paymentTypeId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentTypeId = paymentTypeId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String toString() {
        return paymentId + " " + amount + " " + paymentTypeId;
    }
}
