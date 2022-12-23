import account.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.*;

import java.lang.reflect.*;

public class ReflectionMain {

    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Class<?> pClass;

        try {
            pClass = Class.forName("person.Employee");
            Constructor[] cons = pClass.getDeclaredConstructors();
            Field fields = pClass.getDeclaredFields()[0];
            LOGGER.info(fields);

            Method method = pClass.getDeclaredMethod("setSalary", Double.TYPE);

            Class[] parameterTypes = method.getParameterTypes();
            LOGGER.info(parameterTypes);

            Class returnType = method.getReturnType();
            LOGGER.info(returnType);

            int modifier = method.getModifiers();
            LOGGER.info(modifier);

            Employee alice = (Employee) cons[0].newInstance("Alice", "alice@gmail.com", 25.0);
            method.invoke(alice, 35.0);
            LOGGER.info(alice.getSalary());
        } catch (Exception e) {
            LOGGER.info(e);
        }
    }
}
