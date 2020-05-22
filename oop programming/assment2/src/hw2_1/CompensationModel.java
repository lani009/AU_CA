package hw2_1;

public abstract class CompensationModel {
    //하위클래스가 모두 포함하는 공통적인 필드를 상위에 추출
    private double grossSales;
    private double commisionRate;

    public CompensationModel(double grossSales, double commisionRate) throws IllegalArgumentException {
        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
        if(commisionRate <= 0.0 || commisionRate >= 1.0) {
            throw new IllegalArgumentException("Commision rate must be > 0.0 and <1.0");
        }
        this.grossSales = grossSales;
        this.commisionRate = commisionRate;
    }

    public abstract double earnings();  //추상메소드로 정의하여, 하위클래스에서 재정의 하게끔 하였음.

    public double getGrossSales() {
        return this.grossSales;
    }

    public double getCommisionRate() {
        return this.commisionRate;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

	public void setCommisionRate(double commisionRate) {
        this.commisionRate = commisionRate;
    }

}