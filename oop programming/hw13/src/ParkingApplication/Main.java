package ParkingApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        try (Scanner key = new Scanner(System.in)) {
            int select;

            while (true) {
                System.out.println("1. 파일 불러오기");
                System.out.println("2. 차량 기록 검색 (차량번호)");
                System.out.println("3. 차량 기록 검색 (연락처)");
                System.out.println("4. 총 매출액 정산");
                System.out.println("5. 종료");

                select = key.nextInt();

                switch (select) {
                    case 1:
                        System.out.println("불러오고자 하는 파일 이름을 입력하세요");
                        parkingLot.inputParkingData(key.next());
                        break;

                    case 2:
                        System.out.println("찾고자 하는 차량 번호를 입력하세요.");
                        parkingLot.searchCar(key.next(), 1);
                        break;

                    case 3:
                        System.out.println("찾고자 하는 연락처를 입력하세요.");
                        parkingLot.searchCar(key.next(), 2);
                        break;

                    case 4:
                        parkingLot.calculateTotalSales();
                        break;

                    case 5:
                        System.out.println("프로그램을 종료합니다.");
                        return;

                    default:
                        System.out.println("선택지를 잘못 입력하셨습니다.");
                        break;
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("선택지를 잘못 입력하셨습니다.");
        }

    }
}