package exam;

public class GCS extends Vehicle {

	public GCS(int code, double[] location) {
		super(code, location);
	}

	@Override
	public void move(double to) {
		double[] newPoint = { getPoint()[0] + to, getPoint()[1] + to, 0 };

		setPoint(newPoint);
	}

	public void move(double to_x, double to_y) {
		double[] newPoint = { getPoint()[0] + to_x, getPoint()[1] + to_y, 0 };

		setPoint(newPoint);
	}

	@Override
	public String toString() {
		return "기기 종류: GCS" + System.lineSeparator() + "기기 코드: " + getDevice_code() + System.lineSeparator()
				+ "현재 위치: x: " + getPoint()[0] + " y: " + getPoint()[1] + System.lineSeparator();
	}

}
