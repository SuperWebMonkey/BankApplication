package person;
import Interfaces.IMyInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyInfo implements IMyInfo {
    private Customer customer;
    private final static Logger LOGGER = LogManager.getLogger(MyInfo.class);

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
        LOGGER.info("Your Information");
        LOGGER.info("Name: " + customer.getName());
        LOGGER.info("Email Address" + customer.getEmailAddress());
        if(customer.getAccount().size() == 0){
            LOGGER.info("You have 0 accounts.");
        } else {
            LOGGER.info("YOu have " + customer.getAccount().size() + " accounts.");
        }
    }
}
