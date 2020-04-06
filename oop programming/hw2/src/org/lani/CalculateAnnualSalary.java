package org.lani;

public class CalculateAnnualSalary {
    private Employee employee;
    public CalculateAnnualSalary(Employee employee) {
        this.employee = employee;
    }

    public double getIncresedAnnualSalary() {
        switch (employee.getType()) {
            case 1:
                return employee.getSalary() * 0.7;
        
            case 2:
                return employee.getSalary() * 1.1;
            
            case 3:
                return employee.getSalary() * 1.2;

            case 4:
                return employee.getSalary() * 1.3;

            case 5:
                return employee.getSalary() * 1.6;

            default:
                return 0;
        }
    }
}