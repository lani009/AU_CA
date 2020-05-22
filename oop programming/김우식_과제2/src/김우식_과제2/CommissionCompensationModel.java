package ±è¿ì½Ä_°úÁ¦2;

public class CommissionCompensationModel extends CompensationModel{
	
	private double grossSales; // gross weekly sales
	private double commissionRate; // commission percentage
	
	public CommissionCompensationModel(double grossSales, double commissionRate) {
		
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}
	
	public double getGrossSales() {return grossSales;}
	public double getCommissionRate() {return commissionRate;}
	
	// calculate earnings
	public double earnings() {return commissionRate * grossSales;}
}
