package lani;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        Random random = new Random();

        try {
            System.out.println("data를 읽어 옵니다.");
            parkingLot.readData();

            System.out.printf("총 주차 요금은 %d원 입니다.\n", parkingLot.calcCharge());

            int sample = random.nextInt(1000);
            System.out.printf("%d번째 data를 샘플로 저장합니다.\n", sample+1);
            parkingLot.writeData(sample, "sample"+sample+".dat");
        } catch (Exception e) {
            System.err.println("파일이 존재하지 않습니다.");
        }

    }
}
