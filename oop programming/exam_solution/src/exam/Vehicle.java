package exam;

public abstract class Vehicle {
	private double[] point = new double[3];
	private int device_code;
	
	public Vehicle(int code, double[] location) {
		device_code = code;
		point = location;
	}

	
	public double[] getPoint() {
		return point;
	}

	public void setPoint(double[] point) {
		this.point = point;
	}

	public int getDevice_code() {
		return device_code;
	}

	public void setDevice_code(int device_code) {
		this.device_code = device_code;
	}
	
	public abstract void move(double to);
	public abstract String toString();
}
