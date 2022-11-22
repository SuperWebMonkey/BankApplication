package account;

public class MinorAccount extends Account {
    private int age;

    public MinorAccount(double balance, String accountNumber, int age) {
        super(balance, accountNumber);
    }

    public void setAge() {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

}
