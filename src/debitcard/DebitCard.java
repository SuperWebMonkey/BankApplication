package debitcard;

import account.Account;

public class DebitCard {
    private Account account;
    private double withdrawal;

    public DebitCard(Account account, double withdrawal) {
        this.account = account;
        this.withdrawal = withdrawal;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Account getAccount() {
        return this.account;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void withdraw(){
        account.withdraw(getWithdrawal());
    }
}
