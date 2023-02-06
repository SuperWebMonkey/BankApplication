package db.patterns;

import db.connectionpool.ConnectionPool;
import db.utils.MyBatisSqlFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseFactory {
    private static final Logger LOGGER = LogManager.getLogger(MyBatisSqlFactory.class);

    public IDatabaseType getInstance(String type) {
        if (type.equals("jdbc"))
            return ConnectionPool.getInstance();
        else if (type.equals("mybatis"))
            return new MyBatisSqlFactory();
        else
            LOGGER.info("Invalid option. No database selected.");
        return null;
    }
}
