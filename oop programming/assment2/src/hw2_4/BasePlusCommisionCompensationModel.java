package hw2_4;

public class BasePlusCommisionCompensationModel implements CompensationModel {
    private double baseSalary;
    private double grossSales;
    private double commisionRate;

    public BasePlusCommisionCompensationModel(double baseSalary, double grossSales, double commisionRate) {
        this.baseSalary = baseSalary;
        this.grossSales = grossSales;
        this.commisionRate = commisionRate;
    }

    @Override
    public double earnings() {
        return getBaseSalary() + getGrossSales() * getCommisionRate();
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public double getGrossSales() {
        return this.grossSales;
    }

    public double getCommisionRate() {
        return this.commisionRate;
    }
    
}