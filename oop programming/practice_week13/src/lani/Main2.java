package lani;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main2 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String inputPath = System.getProperty("user.dir") + "\\practice_week13\\src\\lani\\sample591.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputPath));

        CarData sample = (CarData) ois.readObject();

        System.out.println(sample.getCarID());
        System.out.println(sample.getParkingLotID());
        System.out.println(sample.getCharge());
        System.out.println(sample.getMember());
        System.out.println(sample.getPaid());

        ois.close();
    }
}