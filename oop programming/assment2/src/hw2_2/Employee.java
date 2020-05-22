package hw2_2;

public abstract class Employee implements Payable {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;


    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s\nSocial Security Number: %s", getFirstName(), getLastName(), getSocialSecurityNumber());
    }

    public abstract double earnings();

    public abstract double getPaymentAmount();  //서브클래스에서 구현하게끔 함
}