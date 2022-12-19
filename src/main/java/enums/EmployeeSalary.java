package enums;

public enum EmployeeSalary {
    BANK_MANAGER(50),
    BANK_TELLER(20),
    BANKER(30);

    private final double salary;

    EmployeeSalary(double salary) {
        this.salary = salary;
    }

    double getSalary() {
        return this.salary;
    }
}
