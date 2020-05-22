package hw2_3;

public class CommisionCompensationModel implements CompensationModel {
    private double grossSales;
    private double commisionRate;

    public CommisionCompensationModel(double grossSales, double commisionRate) {
        this.grossSales = grossSales;
        this.commisionRate = commisionRate;
    }

    @Override
    public double earnings() {
        return getGrossSales() * getCommisionRate();
    }

    public double getGrossSales() {
        return this.grossSales;
    }

    public double getCommisionRate() {
        return this.commisionRate;
    }
    
}