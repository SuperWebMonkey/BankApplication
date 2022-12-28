package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadsMain {

    private final static Logger LOGGER = LogManager.getLogger(ThreadsMain.class);
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadInherit());
        Thread t2 = new Thread(new ThreadRunnable());
        ConnectionPool pool = new ConnectionPool(5);

        try {
            for (int i = 0; i < 7; i++) {
                Thread thread = new Thread(new ThreadInherit());
                pool.add(thread);
                pool.getConnection();
            }

        } catch (Exception e) {
            LOGGER.info(e);
        }

        Callable<String> task = new Callable<String>() {
            String message = "Thread is done";

            @Override
            public String call() throws Exception {
                synchronized (message) {
                    return message;
                }
            }
        };

        ExecutorService executor2 = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Future<String> future = executor2.submit(task);
            list.add(future);
        }

        try {
            for (Future f : list) {
                Thread.sleep(1000);
                LOGGER.info(f.get());
            }
        } catch (Exception e) {
            LOGGER.info(e);
        } finally {
            executor2.shutdown();
        }

        t1.start();
        t2.start();
    }
}
