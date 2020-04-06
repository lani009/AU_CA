package org.lani;

public class CalculateRealMonthlySalary {
    private double salary;

    /**
     * 연봉을 초기화한다.
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * 연봉을 리턴한다
     * @return AnnualSalary
     */
    protected double getAnnualSalary() {
        return salary;
    }

    /**
     * 실제 수령 연봉을 리턴
     * @return real annual salary
     */
    protected double getRealAnnualSalary() {
        return getAnnualSalary() - (getIncomeTax() + getHealthInsurance() + getNationalPension()); //연봉에서 총 공제액을 제하여 리턴
    }

    /**
     * 실제 수령 월급을 리턴
     * @return real monthly salary
     */
    public double getRealMonthlySalary() {
        return getRealAnnualSalary() / 12;  //실제 수령 연봉에서 12만큼 나누어, 실제 수령 월급을 리턴
    }

    /**
     * 근로소득세를 구하는 메소드
     * @return Earned Income Tax
     */
    private double getIncomeTax() {
        return getAnnualSalary() * 0.1;
    }

    /**
     * 국민연금을 구하는 메소드
     * @return National Pension
     */
    private double getNationalPension() {
        return getAnnualSalary() * 0.08;
    }

    /**
     * 건겅보험료를 구하는 메소드
     * @return National Health Insurance Premium
     */
    private double getHealthInsurance() {
        return getAnnualSalary() * 0.05;
    }
}