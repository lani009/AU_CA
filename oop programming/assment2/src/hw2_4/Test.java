package hw2_4;

public class Test {
    public static void main(String[] args) {
        Payable[] payables = new Payable[] {
            new Invoice("01234", "seat", 2, 375.00),
            new Invoice("56789", "tire", 4, 79.95),
            new Employee("Jhon", "Smith", "111-11-1111", new CommisionCompensationModel(1.5, 0.4)),
            new Employee("Sarah", "Jones", "151-55-8473", new SalariedCompensationModel(280.0)),
            new Employee("Michael", "Jackson", "444-44-5555", new HourlyCompensationModel(80.0, 7)),
            new Employee("Barak", "Obama", "555-11-7777", new BasePlusCommisionCompensationModel(300.0, 1.5, 0.1))
        };

        for (Payable payableItreate : payables) {
            System.out.println(payableItreate.toString());  //동적바인딩을 통해 다형적으로 Override된 toString이 호출된다.
            System.out.println();
        }

        System.out.println();

        for (Payable payableItreate : payables) {
            System.out.println(payableItreate.getPaymentAmount());  //동적바인딩을 통해 다형적으로 Override된 getPaymentAmount가 호출된다.
        }
    }
}