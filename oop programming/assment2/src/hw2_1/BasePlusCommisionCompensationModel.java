package hw2_1;

public class BasePlusCommisionCompensationModel extends CompensationModel {
    private double baseSalary;

    public BasePlusCommisionCompensationModel(double grossSales, double commisionRate, double baseSalary) throws IllegalArgumentException {
        super(grossSales, commisionRate);
        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        //나-2번
        return getBaseSalary() + getGrossSales() * getCommisionRate();
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }
    
	public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    
}