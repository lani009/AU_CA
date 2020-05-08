package exam;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        double[] point1 = {0, 0, 10};
        double[] center1 = {10, 10};
        vehicles.add(new UAV(123456, point1, center1, 10));
        double[] point2 = {0, 0, 10};
        double[] center2 = {10, 10};
        vehicles.add(new UAV(201911, point2, center2, 10));
        double[] point3 = {0, 0, 10};
        double[] center3 = {10, 10};
        vehicles.add(new UAV(200106, point3, center3, 10));
        double[] point4 = {10, 10, 0};
        vehicles.add(new GCS(112001, point4));

        double[] point5 = {0,0,17.5};
        Flight flight = new Flight(999999, point5);

        double[] init = new double[10];
        for (int i = 0; i < init.length; i++) {
            init[i] = Math.PI / 4 * i;
            System.out.println(flight);
            flight.move(2);

            for (Vehicle j : vehicles) { 
                if(j instanceof UAV) {
                    ((UAV) j).move(init[i], 2 * Math.PI / 3 * (i % 3));

                }
                if(j instanceof GCS) {
                    j.move(rand.nextDouble()*6 - 3);
                }
                flight.communication(j);
            }
            System.out.println();
        }


    }
}