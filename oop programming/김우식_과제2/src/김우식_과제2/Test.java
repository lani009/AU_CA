package ±è¿ì½Ä_°úÁ¦2;

public class Test {
	public static void main(String[] args) {
		
		CommissionCompensationModel comModel = new CommissionCompensationModel(500.0, 0.56);
		BasePlusCommissionCompensationModel basePlusModel = new BasePlusCommissionCompensationModel(1000.0, 0.1,200.0);
		
		CommissionEmployee comEmployee = new CommissionEmployee("±è", "¿ì½Ä", "201921169", comModel);
		BasePlusCommissionEmployee basePlusEmployee = new BasePlusCommissionEmployee("Kim", "woo-sik", "12171599", basePlusModel);
		
		System.out.println(comEmployee.earnings());
		System.out.println(basePlusEmployee.earnings());
	}
}