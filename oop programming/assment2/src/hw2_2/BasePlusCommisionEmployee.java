package hw2_2;

public class BasePlusCommisionEmployee extends CommisionEmployee {
    private double baseSalary;

    public BasePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales,
            double commisionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commisionRate);
        
        if(baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }

        this.baseSalary = baseSalary;
    }

    public double getBaseSalasy() {
        return this.baseSalary;
    }

    public void setBaseSalasy(double baseSalary) {
        if(baseSalary < 0.0) {
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        }
        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        return getBaseSalasy() + super.earnings();
    }

    @Override
    public String toString() {
        return String.format("Base-salaried %s: base salary: %.2f", super.toString(), getBaseSalasy());
    }
    
    @Override
    public double getPaymentAmount() {
        return earnings();
    }
}