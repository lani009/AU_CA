package org.lani;

import java.util.Scanner;

import org.lani.enumeration.TypeEnum;
import org.lani.exception.NoSuchTypeException;

public class SalaryManager {
    static Scanner key = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("----------------");
        System.out.println("급여계산 프로그램");
        System.out.println("----------------");
        final int NUM_EMPLOYEES = 5; // 직원의 수
        Employee[] employees; // 직원 정보를 담는 배열 선언
        employees = initEmployeeArray(NUM_EMPLOYEES); // 직원들의 정보를 초기화
        printRealMonthlySalary(employees);

        promoteEmployee(employees);

        printRealMonthlySalary(employees);

    }

    /**
     * 직원들의 정보를 초기화 하는 메소드
     * @param length 직원 수
     */
    public static Employee[] initEmployeeArray(int length) {

        // employee[0] = new Employee()
        // .setName("신동엽")
        // .setType(5)
        // .setSalary(50000000);
        // employee[1] = new Employee()
        // .setName("송가인")
        // .setType(4)
        // .setSalary(90000000);
        // employee[2] = new Employee()
        // .setName("박나래")
        // .setType(3)
        // .setSalary(70000000);
        // employee[3] = new Employee()
        // .setName("강호동")
        // .setType(2)
        // .setSalary(80000000);
        // employee[4] = new Employee()
        // .setName("유재석")
        // .setType(1)
        // .setSalary(100000000);

        System.out.println("직원들의 정보를 입력해 주세요");
        Employee[] employees = new Employee[length];    //Employee를 담는 배열 선언.
        //5명 직원의 정보를 초기화 한다.
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee()
                            .setName(inputName())   //이름 초기화
                            .setType(inputType().toInt())   //직급 초기화 <- EnumType을 int로 변환
                            .setSalary(inputSalary());  //급여 초기화
            
        }
        return employees;
    }

    /**
     * 직원을 진급시키기 위한 메소드
     * @param employee 직원 배열
     * @throws NoSuchTypeException
     */
    public static void promoteEmployee(Employee[] employee) throws NoSuchTypeException {
        System.out.println("진급할 직원을 입력해 주세요");
        String name = inputName();   //이름을 입력받는다.

        //employee 배열중에서 해당하는 이름의 직원을 찾는다.
        for (Employee i : employee) {
            if (i.getName().equals(name)) {
                // 만약에 입력받은 이름과 일치하는 직원이라면,
                System.out.printf("%s님의 진급할 직급을 입력해 주세요\n", name);
                TypeEnum type; // 진급하게 될 직급
                TypeEnum originType = TypeEnum.getEnum(i.getType()); // 원래의 직급
                type = inputType();  //직급을 입력받는다.
                System.out.printf("%s님이 %s에서 %s로 진급했습니다.", i.getName(), originType, type);
                return; // 메소드 종료
            }
        }
        System.out.printf("%s에 해당하는 직원이 없습니다.", name);  //for문을 다 돌았을 경우 -> 입력받은 직원이 존재하지 않는 것.
    }

    /**
     * 실제 수령 월급을 계산 및 출력하기 위한 메소드
     * @param employees 직원 배열
     * @throws NoSuchTypeException
     */
    public static void printRealMonthlySalary(Employee[] employees) throws NoSuchTypeException {
        CalculateAnnualSalary annualSalary = new CalculateAnnualSalary();   //인상된 연봉을 계산하기 위함
        CalculateRealMonthlySalary realMonthlySalary = new CalculateRealMonthlySalary();    //실 수령 월급을 계산하기 위함

        for (Employee i : employees) {
            i.setSalary(annualSalary.getIncresedAnnualSalary(i)); // 인상된 연봉을 구하고 이를 Employee에 저장해준다.
            realMonthlySalary.setEmployee(i); // 실 월급을 구하기 위해 employee를 set한다.
            System.out.println(realMonthlySalary); // 실제 수령 월급을 출력한다. Object클래스 오버라이딩을 통해 .toString()호출
        }
    }

    /**
     * 이름을 입력받기 위한 클래스
     * @return name 이름
     */
    public static String inputName() {
        System.out.println("이름: ");
        return key.next();  //입력 받은 String을 return
    }

    /**
     * 직급을 입력받기 위한 클래스
     * @return type 직급
     */
    public static TypeEnum inputType() {
        //while문을 통해 제대로 입력할 때 까지 계속해서 입력을 받는다. (예외처리)
        while(true) {
            System.out.println("직급: ");
            try {
                if(key.hasNextInt()) {
                    // 입력받은게 int형 인 경우
                    return TypeEnum.getEnum(key.nextInt()); //typeEnum으로 변환
                }
                else {
                    //입력받은게 String형 인 경우
                    return TypeEnum.getEnum(key.next());    //typeEnum으로 변환
                }
            } catch (NoSuchTypeException e) {
                // 해당하는 직급이 없을 경우 Enum에서 Execption throw한다. 이를 catch에서 예외처리
                System.out.println("해당하는 직급이 없습니다. 다시입력해 주세요");
            }
        }
    }

    public static double inputSalary() {
        // while문을 통해 제대로 입력할 떄 까지 계속해서 입력을 받는다. (예외처리)
        while(true) {
            System.out.println("급여:");
            double salary = key.nextDouble();
            if(salary <= 0) {
                // 급여가 0 이하인 경우
                System.out.println("급여는 0 이하가 될 수 없습니다.");
            }
            else {
                return salary;  //아무 이상 없을 경우 급여 리턴
            }
        }

    }
}