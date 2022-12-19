package creditcard;

import interfaces.ILoan;

public class Loan implements ILoan {
    private static double loan;
    private static final int MONTHS_IN_A_YEAR = 12;

    public Loan(double loan) {
        this.loan = loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getLoan() {
        return loan;
    }

    public void addLoan(double amount) {
        loan += amount;
    }

    public void deductLoan(double amount) {
        loan -= amount;
    }

    public double calculateMonthlyLoan(int termInYears, double annualInterestRate) {
        double interestDecimal = annualInterestRate / 100;
        double monthlyInterestRate = interestDecimal / MONTHS_IN_A_YEAR;
        int numPayments = termInYears * MONTHS_IN_A_YEAR;

        double monthlyPayment = this.loan * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numPayments))) /
                ((Math.pow(1 + monthlyInterestRate, numPayments) - 1));

        return monthlyPayment;
    }
}
