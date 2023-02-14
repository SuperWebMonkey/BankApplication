package db.patterns;

import db.utils.MyBatisSqlFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseProvider {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisSqlFactory.class);

    public static AbstractFactory getInstance(DatabaseConnectionType type) {
        if (type == DatabaseConnectionType.JDBC)
            return new JDBCDAOFactory();
        else if (type == DatabaseConnectionType.MYBATIS)
            return new MyBatisDAOFactory();
        else {
            throw new UnknownDatabaseTypeException("Unknown model provided.");
        }
    }
}
