package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private BlockingQueue<Thread> connectionPool;
    private int capacity;

    public ConnectionPool(int capacity) {
        this.capacity = capacity;
        connectionPool = new LinkedBlockingQueue<>(capacity);
    }

    public void add(Thread thread) {

        try {
            if (connectionPool.size() >= capacity) {
                connectionPool.wait();
                Thread.sleep(3000);
            }

            connectionPool.put(thread);
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void getConnection() {
        LOGGER.info("Connection is ready");
    }

    public void releaseConnection() {
        LOGGER.info("Releasing connection");
    }
}
