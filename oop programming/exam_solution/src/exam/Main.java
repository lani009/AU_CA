package exam;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		// simulation 환경 정의
		ArrayList<Vehicle> nodes = new ArrayList<>();
		double[] uav_location = { 0, 0, 10 };
		double[] uav_center = { 10, 10 };
		double uav_radius = 10;

		nodes.add(new UAV(123456, uav_location, uav_center, uav_radius));
		nodes.add(new UAV(201911, uav_location, uav_center, uav_radius));
		nodes.add(new UAV(200106, uav_location, uav_center, uav_radius));

		double[] gcs_location = { 10, 10, 0 };
		nodes.add(new GCS(112001, gcs_location));

		ArrayList<Double> time = new ArrayList<Double>();
		for (double i = 0; i < 10; i++) {
			time.add(i * Math.PI / 4);
		}

		double[] flight_location = { 0, 0, 17.5 };
		Flight myFlight = new Flight(999999, flight_location);

		double phase = 0;
		
		for (double t : time) {
			// 이동
			for (Vehicle v : nodes) {
				if (v instanceof UAV) {
					UAV temp = (UAV) v;
					temp.move(t, phase);
					phase += 2 * Math.PI / 3;
				} else if (v instanceof GCS) {
					GCS temp = (GCS) v;
					temp.move((Math.random() - 0.5) * 6, (Math.random() - 0.5) * 6);
				}
			}
			myFlight.move(2);
			
			// 출력문
			System.out.println(myFlight);
			for (Vehicle v : nodes) {
				myFlight.communication(v);
			}
			System.out.println("");
		}
	}
}
