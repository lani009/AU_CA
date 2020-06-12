package ParkingApplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<Car> cars;
    private String fileName;

    public ParkingLot() {
        cars = new ArrayList<>();
    }

    public void searchCar(String searchType, int input) {
        // input이 String타입일건데.... input.txt의 사항이랑 맞지 않는 것 같아서 변수 이용을 과제와 달리함.
        switch (input) {
            case 1:
                // search car number
                for (Car car : cars) {
                    if(car.getCarNumber().equals(searchType)) {
                        System.out.println(car);
                        return;
                    }
                }
                System.out.println("찾고자 하는 차량이 없습니다.");
                break;
        
            case 2:
                //search phone number
                boolean flag = false;
                for (Car car : cars) {
                    if(car.getPhoneNumber().equals(searchType)) {
                        System.out.println(car.toString());
                        flag = true;
                    }
                }
                if(!flag) {
                    System.out.println("찾고자 하는 차량이 없습니다.");
                }
        }
    }

    public void inputParkingData(String inputData) {
        this.fileName = inputData;
        String path = System.getProperty("user.dir") + "\\src\\ParkingApplication\\" + fileName + ".txt";

        System.out.printf("파일경로 :%s\n", this.fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String temp;
            while ((temp = br.readLine()) != null) {
                String res[] = temp.split("//");
                cars.add(new Car(res[0], res[1], Integer.parseInt(res[2]), Boolean.parseBoolean(res[3])));
            }

        } catch (FileNotFoundException e) {
            System.out.println("해당 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateTotalSales() {
        int sum = cars.stream().map(t -> {
            if(t.getParkingTicket()) {
                // 정기 주차권을 소유한 경우 50% 할인
                return 500 * t.getParkingTime();
            } else {
                return 1000 * t.getParkingTime();
            }
        }).reduce(0, Integer::sum);

        System.out.printf("총 매출: %5d 원 입니다.\n", sum);
    }
}