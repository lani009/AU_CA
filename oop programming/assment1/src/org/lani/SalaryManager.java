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
        updateEmployeeSalary(employees);    //직원들의 연봉을 인상
        printRealMonthlySalary(employees);  //실 수령 월급을 출력

        //먼저 실행되고, 승진이 끝나면 프로그램의 종료를 묻기 위해 do-while 사용.
        do {
            promoteEmployee(employees); //직원을 승진시킨다.
        } while (inputContinueProgram());
        System.out.println("프로그램을 종료합니다.");
    }

    /**
     * 직원들의 정보를 초기화 하는 메소드
     * @param length 직원 수
     */
    public static Employee[] initEmployeeArray(int length) {
        System.out.println("직원들의 정보를 입력해 주세요");
        Employee[] employees = new Employee[length];    //Employee를 담는 배열 선언.
        //5명 직원의 정보를 초기화 한다.
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(inputName(), inputType().toInt(), inputSalary());  //급여 초기화
            System.out.println();   //한줄 띄움
        }
        return employees;
    }

    /**
     * 직원을 진급시키기 위한 메소드
     * @param employee 직원 배열
     * @throws NoSuchTypeException
     */
    public static void promoteEmployee(Employee[] employee) throws NoSuchTypeException {
        System.out.println("\n진급할 직원을 입력해 주세요");
        String name = inputName();   //이름을 입력받는다.

        //employee 배열중에서 해당하는 이름의 직원을 찾는다.
        for (Employee i : employee) {
            if (i.getName().equals(name)) {
                // 만약에 입력받은 이름과 일치하는 직원이라면,
                System.out.printf("%s님의 진급할 직급을 입력해 주세요\n", name);
                TypeEnum type; // 진급하게 될 직급
                TypeEnum originType = TypeEnum.getEnum(i.getType()); // 원래의 직급
                type = inputType();  //직급을 입력받는다.
                i.setType(type.toInt());    //직급을 set한다.
                System.out.printf("%s님이 %s에서 %s로 진급했습니다.\n", i.getName(), originType, type);
                updateEmployeeSalary(i);    //해당하는 직원의 임금을 인상
                printRealMonthlySalary(employee);  //모든 직원에 대해서 출력
                return; // 메소드 종료
            }
        }
        System.out.printf("%s 직원이 없습니다.\n", name);  //for문을 다 돌았을 경우 -> 입력받은 직원이 존재하지 않는 것.
    }

    /**
     * 실제 수령 월급을 계산 및 출력하기 위한 메소드
     * @param employees 직원 배열
     * @throws NoSuchTypeException
     */
    public static void printRealMonthlySalary(Employee[] employees) throws NoSuchTypeException {
        for (Employee i : employees) {
            CalculateRealMonthlySalary realMonthlySalary = new CalculateRealMonthlySalary(i);   //실 수령 월급을 계산한다.
            System.out.println(realMonthlySalary); // 실제 수령 월급을 출력한다. Object클래스 오버라이딩을 통해 .toString()호출
        }
    }

    /**
     * 직원의 연봉을 인상한다.
     * @param employee 직원
     */
    public static void updateEmployeeSalary(Employee employee) throws NoSuchTypeException {
        CalculateAnnualSalary annualSalary = new CalculateAnnualSalary();   //인상된 연봉을 계산하기 위함
        employee.setSalary(annualSalary.getIncresedAnnualSalary(employee)); //인상된 만큼 연봉을 업데이트 해준다.
    }

    /**
     * 모든 직원의 연봉을 인상한다.
     * @param employee[] 직원 배열
     */
    public static void updateEmployeeSalary(Employee[] employee) throws NoSuchTypeException {
        CalculateAnnualSalary annualSalary = new CalculateAnnualSalary();   //인상된 연봉을 계산하기 위함
        for (Employee i : employee) {
            i.setSalary(annualSalary.getIncresedAnnualSalary(i)); //인상된 만큼 연봉을 업데이트 해준다.
        }
    }

    /**
     * 이름을 입력받기 위한 메소드
     * @return name 이름
     */
    public static String inputName() {
        System.out.print("이름: ");
        return key.next();  //입력 받은 String을 return
    }

    /**
     * 직급을 입력받기 위한 메소드
     * @return type 직급
     */
    public static TypeEnum inputType() {
        //while문을 통해 제대로 입력할 때 까지 계속해서 입력을 받는다. (예외처리)
        while(true) {
            System.out.print("직급: ");
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

    /**
     * 연봉을 입력받기 위한 메소드
     * @return salary 연봉
     */
    public static double inputSalary() {
        // while문을 통해 제대로 입력할 떄 까지 계속해서 입력을 받는다. (예외처리)
        while(true) {
            System.out.print("급여: ");
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

    /**
     * 프로그램을 종료할지 입력받는 메소드
     * @return true 계속, false 취소
     */
    public static boolean inputContinueProgram() {
        while(true) {
            System.out.println("계속하시겠습니까?(Y/N)");
            String input = key.next().toUpperCase();    //대소문자 구분없이 입력받기 위해 UpperCase사용.
            if(input.equals("Y")) {
                return true;    //프로그램 계속
            } else if(input.equals("N")) {
                return false;   //프로그램 종료
            } else {
                System.out.println("다시입력해 주십시오.");
                continue;   //다시 입력하기 위해 continue
            }
        }
    }
}