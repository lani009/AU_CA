package app.lani;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberTest {
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        ArrayList<Member> members = new ArrayList<>();  //고객 저장 리스트
        //while문을 통해 계속해서 입력을 받는다.
        while(true) {
            System.out.println("1. Bronze 등급 고객 등록");
            System.out.println("2. Silver 등급 고객 등록");
            System.out.println("3. Gold 등급 고객 등록");
            System.out.println("4. 총 매출액 정산");
            System.out.println("5. 종료");
            int select;
            try {
                select = key.nextInt();
            } catch (InputMismatchException e) {
                //integer형 이외의 값을 받았을 때.
                System.out.println("숫자만 입력해 주십시오.\n");
                key.nextLine(); //버퍼 비움
                continue;
            }
            
            switch(select) {
                case 1:
                case 2:
                case 3:
                    members.add(register(key, select)); //고객 등록
                    break;
    
                case 4:
                    double total = 0;
                    for (Member i : members) {
                        total += i.calcSales();
                    }
                    System.out.printf("총 매출은 %.1f원 입니다\n\n", total);
                    break;
    
                case 5:
                    key.close();
                    System.out.println("프로그램 종료");
                    return;

                default:
                    System.out.println("1~5 사이의 숫자값을 입력해 주십시오.\n");
            }
        }
    }

    public static Member register(Scanner key, int select) {
        System.out.println("고객 이름을 입력하세요");
        String name = key.next();

        System.out.println("고객 연락처를 입력하세요");
        String phone = key.next();

        System.out.println("구매 개수를 입력하세요");
        int purchase = key.nextInt();

        System.out.println("입력되었습니다.\n");

        switch(select) {
            case 1:
                return new Bronze_Member(name, phone, purchase);    //bronze 고객
            
            case 2:
                return new Silver_Member(name, phone, purchase, 0.1);   //silver 고객

            case 3:
                return new Gold_Member(name, phone, purchase, 0.5); //gold 고객

            default:
                return null;
        }
    }
}