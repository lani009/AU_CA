package org.lani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SalaryManager {
    public static void main(String[] args) throws IOException {
        System.out.println("ㄹㅇㄴ");
        final int NUM_EMPLOYEES = 5;    //직원의 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   //표준입력 객체
        Employee[] employee = new Employee[NUM_EMPLOYEES];  //직원 정보를 담는 배열 선언

        for (int i = 0; i < NUM_EMPLOYEES; i++) {
            StringTokenizer strtok = new StringTokenizer(br.readLine());    //입력받은 문자열을 공백을 기준으로 토큰화
            employee[i] = new Employee()
                .setName(strtok.nextToken())    //name초기화
                .setType(Integer.valueOf(strtok.nextToken()))    //type을 int로 변환한 후 초기화
                .setSalary(Double.valueOf(strtok.nextToken()));  //salary를 double로 변환한 후 초기화
        }

        CalculateRealMonthlySalary realMonthlySalary = new CalculateRealMonthlySalary();    //실제 월급을 구하기 위한 객체 선언
        for (int i = 0; i < NUM_EMPLOYEES; i++) {
            StringBuilder sb = new StringBuilder();
            realMonthlySalary.setSalary(new CalculateAnnualSalary(employee[i]).getIncresedAnnualSalary());
            sb.append(employee[i].getName());
            sb.append(" = ");
            sb.append(realMonthlySalary.getRealMonthlySalary());
            System.out.println(sb.toString());
        }

        br.close();
    }
}