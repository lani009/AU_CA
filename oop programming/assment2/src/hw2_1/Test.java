package hw2_1;

public class Test {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Michale", "Jackson", "111-1151-553", new CommisionCompensationModel(0.04, 0.7));
        Employee emp2 = new Employee("Jackie", "Chan", "13513-515-555", new BasePlusCommisionCompensationModel(0.04, 0.5, 5000));
        System.out.println(emp1.getEarnings());
        System.out.println(emp2.getEarnings());
        
        //compensation model 동적으로 변경
        emp1.setCompenStationModel(new BasePlusCommisionCompensationModel(0.05, 0.6, 5000));
        emp2.setCompenStationModel(new CommisionCompensationModel(0.02, 0.4));
        System.out.println(emp1.getEarnings());
        System.out.println(emp2.getEarnings());
    }
}