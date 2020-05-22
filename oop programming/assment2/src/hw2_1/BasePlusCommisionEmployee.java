package hw2_1;

public class BasePlusCommisionEmployee extends CommisionEmployee {

    public BasePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber,
            CompensationModel compensationModel) {
        super(firstName, lastName, socialSecurityNumber, compensationModel);
    }

    public double getBaseSalary() {
        //업캐스팅 후, baseSalary호출
        return ((BasePlusCommisionCompensationModel)getCompensationModel()).getBaseSalary();
    }

    public void setBaseSalary(double baseSalary) {
        //업캐스팅 후, setBaseSalary 호출
        ((BasePlusCommisionCompensationModel)getCompensationModel()).setBaseSalary(baseSalary);
    }

    @Override
    public String toString() {
        return String.format("base-salaried commision employee: %s %s\n"
                + "social security number: %s\n"
                + "gross sales: %.2f\n"
                + "commision rate: %.2f\n"
                + "base salary: %.2f",
                getFirstName(), getLastName(), getSocialSecurityNumber(),
                getGrossSales(), getCommisionRate(), getBaseSalary());
    }
    
}