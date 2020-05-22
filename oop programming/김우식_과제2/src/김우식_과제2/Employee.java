package ±è¿ì½Ä_°úÁ¦2;

public class Employee {

	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private CompensationModel Model;
	
	public Employee (String firstName, String lastName, String socialSecurityNumber, CompensationModel Model){
		// implicit call to Object's default constructor occurs here
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.Model = Model;
	}
	
	public double getGrossSales() {
		if(Model instanceof CommissionCompensationModel) {
			return ((CommissionCompensationModel)Model).getGrossSales();
		}
		
		else {
			return ((BasePlusCommissionCompensationModel)Model).getGrossSales();
		}
	}
	
	public double getCommissionRate() {
		if(Model instanceof CommissionCompensationModel) {
			return ((CommissionCompensationModel)Model).getCommissionRate();
		}
		
		else {
			return ((BasePlusCommissionCompensationModel)Model).getCommissionRate();
		}
	}
	
	public double getBaseSalary() {
		return ((BasePlusCommissionCompensationModel)Model).getGrossSales();
	}
	
	public CommissionEmployee setCommission(BasePlusCommissionEmployee basePlusEmployee)
	{
		CommissionCompensationModel comModel = new CommissionCompensationModel(basePlusEmployee.getGrossSales(), basePlusEmployee.getCommissionRate());
		CommissionEmployee comEmployee = new CommissionEmployee(basePlusEmployee.getFirstName(), basePlusEmployee.getLastName(), basePlusEmployee.getSocialSecurityNumber(), comModel);
		return comEmployee;
	}
	
	public BasePlusCommissionEmployee setBasePlus(CommissionEmployee comEmployee)
	{
		BasePlusCommissionCompensationModel basePlusModel = new BasePlusCommissionCompensationModel(comEmployee.getGrossSales(), comEmployee.getCommissionRate(), 0.0);
		BasePlusCommissionEmployee basePlusEmployee = new BasePlusCommissionEmployee(comEmployee.getFirstName(), comEmployee.getLastName(), comEmployee.getSocialSecurityNumber(), basePlusModel);
		return basePlusEmployee;
	}
	
	public double earnings() {return Model.earnings();}
}
