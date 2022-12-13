package person;

public class Employee extends Person {
    private double salary;

    public Employee(String name, String emailAddress, double salary) {
        super(name, emailAddress);
        setSalary(salary);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}
