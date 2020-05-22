package hw2_4;

public class HourlyCompensationModel implements CompensationModel {
    private double wage;
    private double hours;

    public HourlyCompensationModel(double wage, double hours) {
        this.wage = wage;
        this.hours = hours;
    }

    @Override
    public double earnings() {
        //40시간이 넘을 경우 1.5배의 초과수당
        if(getHours() <= 40) {
            return getWage() * getHours();
        }
        else {
            return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;
        }
    }

    public double getWage() {
        return this.wage;
    }

    public double getHours() {
        return this.hours;
    }

    
}