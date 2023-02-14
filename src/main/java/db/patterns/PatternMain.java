package db.patterns;

import db.dao.IBaseDAO;
import db.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatternMain {
    private static final Logger LOGGER = LogManager.getLogger(PatternMain.class);

    public static void main(String[] args) {

        IBaseDAO cityDAO = DatabaseProvider.getInstance(DatabaseConnectionType.JDBC).createDAO("City");
        LOGGER.info(cityDAO.getAllEntities());

        Customer customer = new CustomerBuilder()
                                .withCustomerId(5)
                                .withFirstName("Brian")
                                .withLastName("Smith")
                                .withPhone("707-482-1011")
                                .build();

        LOGGER.info(customer);
    }
}
