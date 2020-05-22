package hw2_3;

public class Test {
    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
            new Employee("Jhon", "Smith", "111-11-1111", new CommisionCompensationModel(1.5, 0.4)),
            new Employee("Sarah", "Jones", "151-55-8473", new SalariedCompensationModel(280.0)),
            new Employee("Michael", "Jackson", "444-44-5555", new HourlyCompensationModel(80.0, 7)),
            new Employee("Barak", "Obama", "555-11-7777", new BasePlusCommisionCompensationModel(300.0, 1.5, 0.1))
        };

        for (Employee employeeItreate : employees) {
            System.out.printf("%s %s: $%,.2f\n", employeeItreate.getFirstName(),
                    employeeItreate.getLastName(), employeeItreate.getEarnings());
        }
    }
}