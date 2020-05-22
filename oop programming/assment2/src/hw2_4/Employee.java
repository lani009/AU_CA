package hw2_4;

public class Employee implements Payable {
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

    public double getGrossSales() throws IllegalAccessException {
        //해당 Model이 Commision속성을 가지고 있다면 return, 아닐 경우 예외 생성
        if(compensationModel instanceof CommisionCompensationModel) {
            return ((CommisionCompensationModel) compensationModel).getGrossSales();
        } else if(compensationModel instanceof BasePlusCommisionCompensationModel) {
            return ((BasePlusCommisionCompensationModel) compensationModel).getGrossSales();
        } else {
            throw new IllegalAccessException("The object has no gross sales attribute.");
        }
    }

    public double getCommisionRate() throws IllegalAccessException {
            //해당 Model이 Commision속성을 가지고 있다면 return, 아닐 경우 예외 생성
            if(compensationModel instanceof CommisionCompensationModel) {
                return ((CommisionCompensationModel) compensationModel).getCommisionRate();
            } else if(compensationModel instanceof BasePlusCommisionCompensationModel) {
                return ((BasePlusCommisionCompensationModel) compensationModel).getCommisionRate();
            } else {
                throw new IllegalAccessException("The object has no gross sales attribute.");
            }
    }

    protected CompensationModel getCompensationModel() {
        return compensationModel;
    }

    @Override
    public String toString() {
        return String.format("employee: %s %s\n"
                + "social security number: %s\n",
                getFirstName(), getLastName(), getSocialSecurityNumber());
    }

    @Override
    public double getPaymentAmount() {
        return getEarnings();
    }
    
}