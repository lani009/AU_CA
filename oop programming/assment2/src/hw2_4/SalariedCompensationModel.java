package hw2_4;

public class SalariedCompensationModel implements CompensationModel {
    private double weeklySalary;

    public SalariedCompensationModel(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double earnings() {
        return getWeeklySalary();
    }

    public double getWeeklySalary() {
        return this.weeklySalary;
    }

}