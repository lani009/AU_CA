package hw2_1;

public class Employee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private CompensationModel compensationModel;

    public Employee(String firstName, String lastName, String socialSecurityNumber,
                CompensationModel compensationModel) {

        //라-1번
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.compensationModel = compensationModel;
    }

    public void setCompenStationModel(CompensationModel compensationModel) {
        this.compensationModel = compensationModel; //라-2번
    }

    public double getEarnings() {
        return compensationModel.earnings();    //다번, 라-3번
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public double getGrossSales() {
        return compensationModel.getGrossSales();
    }

    public double getCommisionRate() {
        return compensationModel.getCommisionRate();
    }

    protected CompensationModel getCompensationModel() {
        return compensationModel;
    }

    @Override
    public String toString() {
        return String.format("commision employee: %s %s\n"
                + "social security number: %s\n"
                + "gross sales: %.2f\n"
                + "commision rate: %.2f",
                getFirstName(), getLastName(), getSocialSecurityNumber(),
                getGrossSales(), getCommisionRate());
    }
    
}