package app;

import java.util.Scanner;

public class BankTest {
    static Bank bank = null;    //전역변수로 선언
    static Scanner key = null; //전역변수로 선언
    public static void main(String[] args) {
        bank = new Bank();  //bank 초기화
        key = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------------------------");
            System.out.println("1.계좌생성|2.계좌목록|3.예금|4.출금|5.종료");
            System.out.println("-------------------------------------------");
            System.out.print("선택: ");

            switch (key.nextInt()) {
                case 1:
                    creatAccount();
                    break;
            
                case 2:
                    printAccountList();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    withdraw();
                    break;
                
                case 5:
                    key.close();
                    System.out.println("프로그램 종료");
                    return;
            }
        }
    }

    /**
     * 1. 계좌생성
     */
    public static void creatAccount() {
        System.out.println("--------");
        System.out.println("계좌생성");
        System.out.println("--------");
        System.out.print("계좌번호: ");
        String accountNumber = key.next();
        System.out.print("계좌주: ");
        String owner = key.next();
        System.out.print("초기입금액: ");
        int money = key.nextInt();

        int result = bank.creatAccount(accountNumber, owner, money);  //실행 결과를 result에 저장
        switch (result) {
            case -1:
                //초기 입금 금액이 0원 이하
                System.out.println("Error: 초기 입금 금액이 0원 이하 입니다.");
                break;

            case 0:
                //정상적인 리턴
                System.out.println("결과: 계좌가 생성되었습니다.");
                break;
        }
    }

    /**
     * 2. 계좌목록
     */
    public static void printAccountList() {
        System.out.println("--------");
        System.out.println("계좌 목록");
        System.out.println("--------");
        bank.getAccountList();
    }

    /**
     * 3. 예금
     */
    public static void deposit() {
        System.out.println("--------");
        System.out.println("예금");
        System.out.println("--------");
        System.out.print("계좌번호: ");
        String accountNumber = key.next();
        System.out.print("예금액: ");
        int money = key.nextInt();
        int result = bank.deposit(accountNumber, money);    //리턴 코드를 저장

        switch (result) {
            case 1:
                //예금액이 0 이하
                System.out.println("Error: 예금액이 0 이하입니다.");
                break;
        
            case -1:
                //해당 계좌가 없음
                System.out.println("Error: 해당 계좌가 존재하지 않습니다.");
                break;
            
            case 0:
                //정상 리턴 코드
                System.out.println("결과: 예금이 성공되었습니다.");
                break;
        }
    }

    /**
     * 4. 출금
     */
    public static void withdraw() {
        System.out.println("--------");
        System.out.println("출금");
        System.out.println("--------");
        System.out.print("계좌번호: ");
        String accountNumber = key.next();
        System.out.print("출금액: ");
        int money = key.nextInt();

        int result = bank.withdraw(accountNumber, money);   //리턴 코드를 저장

        switch (result) {
            case 1:
                //출금할 금액이 보유 잔액을 초과
                System.out.println("Error: 출금할 금액이 보유 잔액보다 큽니다.");
                break;

            case -1:
                //해당 계좌가 존재하지 않음
                System.out.println("Error: 해당 계좌가 존재하지 않습니다.");
                break;

            case 0:
                //정상 리턴코드
                System.out.println("결과: 출금이 성공되었습니다.");
                break;
        }
    }
}