package lani;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<CarData> array = new ArrayList<>();

    public ParkingLot() {

    }

    public void readData() {
        String inputPath = System.getProperty("user.dir") + "\\practice_week13\\src\\lani\\input.txt";
        try (FileReader fr = new FileReader(inputPath);
                BufferedReader br = new BufferedReader(fr)) {

            String temp;
            while((temp = br.readLine()) != null) {
                String res[] = temp.split(",");
                array.add(new CarData(Integer.parseInt(res[0]), res[1], Integer.parseInt(res[2]),
                                                        res[3].equals("1"), res[4].equals("1")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calcCharge() {
        int temp = 0;
        for (CarData carData : array) {
            temp += carData.getCharge();
        }

        return temp;
    }

    public void writeData(int i, String fName) {
        String outputPath = System.getProperty("user.dir") + "\\practice_week13\\src\\lani\\" + fName;
        try(FileOutputStream fos = new FileOutputStream(outputPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(array.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}