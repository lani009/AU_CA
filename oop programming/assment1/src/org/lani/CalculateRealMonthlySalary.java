package org.lani;

/**
 * 세금 등을 고려하여 실제 수령받는 월급을 계산한다.
 */
public class CalculateRealMonthlySalary {
    private Employee employee;

    public CalculateRealMonthlySalary() {
        employee = null;    //employee를 null로 초기화
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;   //employee 인스턴스 초기화
    }

    /**
     * 연봉을 리턴한다
     * @return AnnualSalary
     */
    private double getAnnualSalary() {
        return employee.getSalary();  //연봉 리턴
    }

    /**
     * 실제 수령 연봉을 리턴
     * @return real annual salary
     */
    public double getRealAnnualSalary() {
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
    double getIncomeTax() {
        return getAnnualSalary() * 0.1; //근로소득세 계산 후 리턴
    }

    /**
     * 국민연금을 구하는 메소드
     * @return National Pension
     */
    double getNationalPension() {  
        return getAnnualSalary() * 0.08;    //국민연금 계산 후 리턴
    }

    /**
     * 건겅보험료를 구하는 메소드
     * @return National Health Insurance Premium
     */
    double getHealthInsurance() {
        return getAnnualSalary() * 0.05;    //건강보험료 계산 후 리턴
    }

    @Override   //오버리이드 명시
    public String toString() {
        return String.format("%s = %f", employee.getName(), getRealMonthlySalary());    //"이름" = "실 수령 월급" 형식으로 리턴
    }
}