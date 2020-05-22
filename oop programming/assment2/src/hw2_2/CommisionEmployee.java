package hw2_2;

public class CommisionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;

    public CommisionEmployee(String firstName, String lastName, String socialSecurityNumber,
            double grossSales, double commisionRate) {
        super(firstName, lastName, socialSecurityNumber);

        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
        if(commisionRate <= 0.0 || commisionRate >= 1.0) {
            throw new IllegalArgumentException("Commision rate must be > 0.0 and <1.0");
        }

        this.grossSales = grossSales;
        this.commissionRate = commisionRate;
    }


    public double getGrossSales() {
        return this.grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public double earnings() {
        return getCommissionRate() * getGrossSales();
    }

    @Override
    public String toString() {
        return String.format("%s: %s%n%s: $%,.2f; %s: %.2f",
        "commision employee",
        super.toString(),
        "gross sales",
        getGrossSales(),
        "commision rate",
        getCommissionRate());
    }

    @Override
    public double getPaymentAmount() {
        return earnings();
    }
}