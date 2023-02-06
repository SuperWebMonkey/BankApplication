package db.patterns;

import db.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatternMain {
    private static final Logger LOGGER = LogManager.getLogger(PatternMain.class);

    public static void main(String[] args) {
        DatabaseFactory df = new DatabaseFactory();
        IDatabaseType dbType = df.getInstance("jdbc");
        dbType.type();

        Customer customer = new CustomerBuilder()
                                .customerId(5)
                                .firstName("Brian")
                                .lastName("Smith")
                                .phone("707-482-1011")
                                .build();

        LOGGER.info(customer);
    }
}
