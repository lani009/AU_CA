package hw2_1;

public class CommisionCompensationModel extends CompensationModel {

    public CommisionCompensationModel(double grossSales, double commisionRate) throws IllegalArgumentException {
        super(grossSales, commisionRate);
    }

    @Override
    public double earnings() {
        //나-1번
        return getGrossSales() * getCommisionRate();
    }    
}