package exam;

public class Flight extends Vehicle {

	public Flight(int code, double[] location) {
		super(code, location);
	}

	@Override
	public void move(double to) {
		double[] newPoint = { getPoint()[0] + to, getPoint()[1] + to, getPoint()[2] };
		setPoint(newPoint);
	}

	@Override
	public String toString() {
		return "Fight 위치 x: " + getPoint()[0] + " y: " + getPoint()[1] + " z: " + getPoint()[2];
	}

	public void communication(Vehicle v) {
		if (calcDistance(v.getPoint()[0], v.getPoint()[1], v.getPoint()[2])< 20) {
			System.out.println(v);
		}
	}
	// 거리 계산 method
	public double calcDistance(double x, double y, double z) {
		return Math.sqrt(Math.pow(x - getPoint()[0], 2) + Math.pow(y - getPoint()[1], 2) + Math.pow(z - getPoint()[2], 2));
	}
}
