package db.models;

public class PaymentType {
    private int paymentType;
    private String paymentTypeName;

    public PaymentType() {
    }

    public PaymentType(int paymentType, String paymentTypeName) {
        this.paymentType = paymentType;
        this.paymentTypeName = paymentTypeName;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public String toString() {
        return this.paymentType + " " + paymentTypeName;
    }
}
