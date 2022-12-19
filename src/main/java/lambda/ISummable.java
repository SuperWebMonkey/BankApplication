package lambda;

import person.Employee;

@FunctionalInterface
public interface ISummable<T extends Employee> {
    public abstract void sum(T[] a);
}
