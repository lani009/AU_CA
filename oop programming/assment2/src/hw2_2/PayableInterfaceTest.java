package hw2_2;

public class PayableInterfaceTest {
    public static void main(String[] args) {
        Payable[] payableObjects = new Payable[] {
            new Invoice("01234", "seat", 2, 375.00),
            new Invoice("56789", "tire", 4, 79.95),
            new SalariedEmployee("John", "smith", "111-11-1111", 800.00),
            new HourlyEmployee("Lisa", "Barnes", "888-88-8888", 1200.00, 30),
            new CommisionEmployee("Miclael", "Jackson", "777-77-7777", 450.00, 0.4),
            new BasePlusCommisionEmployee("Nora", "Jhons", "555-55-5555", 700.00, 0.6, 50.00)
        };

        for (Payable payableItreate : payableObjects) {
            //나-1 Payable객체의 String출력
            System.out.println(payableItreate.toString());
            System.out.println();
        }
        System.out.println();

        for (Payable payableItreate : payableObjects) {
            if(payableItreate instanceof BasePlusCommisionEmployee) {
                //나-2 base salary 10%인상
                BasePlusCommisionEmployee temp = (BasePlusCommisionEmployee) payableItreate;
                temp.setBaseSalasy(temp.getBaseSalasy() * 1.1);
            }
            //payable객체의 지불비용 출력
            System.out.printf("%n%s %npayment due: $%,.2f%n",
                    payableItreate.toString(),
                    payableItreate.getPaymentAmount()); //동적바인딩
        }
    }
}