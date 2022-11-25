package person;
import Interfaces.IMyInfo;

public class MyInfo implements IMyInfo {
    private Customer customer;

    public MyInfo(Customer customer){
        this.customer = customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void myInfo() {
        System.out.println("Your Information");
        System.out.println("Name: " + customer.getName());
        System.out.println("Email Address" + customer.getEmailAddress());
        if(customer.getAccount().size() == 0){
            System.out.println("You have 0 accounts.");
        } else {
            System.out.println("YOu have " + customer.getAccount().size() + " accounts.");
        }
    }
}
