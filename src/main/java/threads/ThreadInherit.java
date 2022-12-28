package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadInherit extends Thread {
    private final static Logger LOGGER = LogManager.getLogger(ThreadsMain.class);

    @Override
    public void run() {

        synchronized (ThreadsMain.lock1) {
            LOGGER.info(Thread.currentThread().getName() + " locked ");

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                LOGGER.info(e);
            }

            String message = "deadlock";

            synchronized (ThreadsMain.lock2) {
                LOGGER.info(message);
            }
        }
    }
}
