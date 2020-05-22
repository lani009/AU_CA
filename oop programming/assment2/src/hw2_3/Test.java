package hw2_3;

import java.util.Random;

public class Test {
    public static final double grossSales = 1.5;    //gross sales는 같은 회계년도에서는 변하지 않음
    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
            new Employee("Jhon", "Smith", "111-11-1111", new CommisionCompensationModel(1.5, 0.4)),
            new Employee("Sarah", "Jones", "151-55-8473", new SalariedCompensationModel(280.0)),
            new Employee("Michael", "Jackson", "444-44-5555", new HourlyCompensationModel(80.0, 7)),
            new Employee("Barak", "Obama", "555-11-7777", new BasePlusCommisionCompensationModel(300.0, 1.5, 0.1))
        };

        for (Employee employeeItreate : employees) {
            //각 직원의 earnings를 출력
            System.out.printf("%s %s: $%,.2f\n", employeeItreate.getFirstName(),
                    employeeItreate.getLastName(), employeeItreate.getEarnings());
        }
        System.out.println();
        Random random = new Random(System.currentTimeMillis());
        for (Employee employeeIterate : employees) {
            switch (random.nextInt(4)+1) {
                //랜덤으로 Model교체
                case 1:
                    //랜덤으로 commision rate 결정
                    employeeIterate.setCompenStationModel(new CommisionCompensationModel(grossSales, random.nextDouble()));
                    break;
            
                case 2:
                    //0~500사이의 실수값으로 주급 결정
                    employeeIterate.setCompenStationModel(new SalariedCompensationModel(random.nextDouble()*500));
                    break;

                case 3:
                    //0~10사이의 실수값으로 시급 결정, 0~50사이의 정수값으로 일한 시간 결정
                    employeeIterate.setCompenStationModel(new HourlyCompensationModel(random.nextDouble() * 10, random.nextInt(50)));
                    break;

                case 4:
                    //랜덤으로 commision rate 결정
                    //0~500사이의 실수값으로 base salary 결정
                    employeeIterate.setCompenStationModel(new BasePlusCommisionCompensationModel(random.nextDouble() * 500,
                            grossSales, random.nextDouble()));
                    break;
            }
            
        }

        for (Employee employeeItreate : employees) {
            //각 직원의 earnings를 출력
            System.out.printf("%s %s: $%,.2f\n", employeeItreate.getFirstName(),
                    employeeItreate.getLastName(), employeeItreate.getEarnings());
        }
    }
}