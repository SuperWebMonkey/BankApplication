package person;

import Interfaces.IPerson;

public abstract class Person implements IPerson {
    private String name;
    private String emailAddress;
    private static int countPerson = 0;

    static {
        countPerson++;
    }

    public Person(String name, String accountNumber) {
        setName(name);
        setEmailAddress(accountNumber);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return this.name;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public boolean equals(Object o){
        if (o == this)
            return true;
        if (!(o instanceof Person))
            return false;
        Person other = (Person)o;
        boolean balanceEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean emailAddressEquals = (this.emailAddress == null && other.emailAddress == null)
                || (this.emailAddress != null && this.emailAddress.equals(other.emailAddress));
        return emailAddressEquals && balanceEquals;
    }

    public int hashCode() {
        int result = 17;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (emailAddress != null) {
            result = 31 * result + emailAddress.hashCode();
        }

        return result;
    }

    public String toString() {
        return "Hello " + this.name + " your email address is " + this.emailAddress;
    }
}
