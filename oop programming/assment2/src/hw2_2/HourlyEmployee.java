package hw2_2;

public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;


    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, double wage, double hours) {
        super(firstName, lastName, socialSecurityNumber);
        
        if(wage < 0.0) {
            throw new IllegalArgumentException("Hourly wage must be >= 0.0");
        }

        if(hours < 0.0 || hours > 168.0) {
            throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
        }

        this.wage = wage;
        this.hours = hours;
    }

    public void setWage(double wage) {
        if(hours < 0.0 || hours > 168.0) {
            throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
        }
        this.wage = wage;
    }

    public void setHours(double hours) {
        if(hours < 0.0 || hours > 168.0) {
            throw new IllegalArgumentException("Hours worked must be >= 0.0 and <= 168.0");
        }
        this.hours = hours;
    }
    
    public double getHours() {
        return this.hours;
    }

    public double getWage() {
        return this.wage;
    }

    @Override
    public double earnings() {
        if(getHours() <= 40) {
            return getWage() * getHours();
        }
        else {
            return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;
        }
    }

    @Override
    public String toString() {
        return String.format("hourly employee: %s%nhours wage: $%,.2f; hourly worked: %,.2f", super.toString(), getWage(), getHours());
    }

    @Override
    public double getPaymentAmount() {
        return earnings();
    }
}