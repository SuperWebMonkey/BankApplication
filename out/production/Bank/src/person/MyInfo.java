package person;
import Interfaces.IMyInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyInfo implements IMyInfo {
    private Customer customer;
    private static Logger logger = LogManager.getLogger(MyInfo.class);

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
        logger.info("Your Information");
        logger.info("Name: " + customer.getName());
        logger.info("Email Address" + customer.getEmailAddress());
        if(customer.getAccount().size() == 0){
            logger.info("You have 0 accounts.");
        } else {
            logger.info("YOu have " + customer.getAccount().size() + " accounts.");
        }
    }
}
