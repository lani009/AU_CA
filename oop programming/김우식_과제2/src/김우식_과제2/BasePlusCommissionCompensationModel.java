package ±è¿ì½Ä_°úÁ¦2;

public class BasePlusCommissionCompensationModel extends CompensationModel{

	private double grossSales; // gross weekly sales
	private double commissionRate; // commission percentage
	private double baseSalary; // base salary per week
	
	public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary) {
		
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
		this.baseSalary = baseSalary;
	}
	
	public double getGrossSales() {return grossSales;}
	public double getCommissionRate() {return commissionRate;}
	public double getBaseSalary() {return baseSalary;}
	
	// calculate earnings
	public double earnings() {return baseSalary + (commissionRate * grossSales);}
}
