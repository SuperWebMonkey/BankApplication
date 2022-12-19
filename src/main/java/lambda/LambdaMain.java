package lambda;

import org.apache.logging.log4j.*;
import person.*;

import java.util.*;
import java.util.function.Predicate;

public class LambdaMain {

    private final static Logger LOGGER = LogManager.getLogger(LambdaMain.class);

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();

        Employee bob = new Employee("Bob", "bob@gmail.com", 20.0);
        employeeList.add(bob);

        Employee john = new Employee("John", "john@yahoo.com", 30.0);
        employeeList.add(john);

        Employee alice = new Employee("Alice", "alice@msn.com", 25.50);
        employeeList.add(alice);

        IGetInfo info = p -> {
            LOGGER.info(p);
        };

        IPrintable print = () -> {
            LOGGER.info("This is the lambda main class");
        };

        ISummable sum = (a) -> {
            double total = 0;
            for (int i = 0; i < a.length; i++) {
                total += a[i].getSalary();
            }
            LOGGER.info("Total salary of employees is " + total);
        };

        employeeList.forEach((n) -> LOGGER.info(n));

        Predicate<Employee> checkSalary = s -> s.getSalary() > 15.0;

    }
}
