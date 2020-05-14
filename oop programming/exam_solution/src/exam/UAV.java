package exam;

public class UAV extends Vehicle implements attackable {
	private double[] rotation_center = new double[2];
	private double radius;
	private boolean status = true;

	public UAV(int code, double[] location, double[] center, double radius) {
		super(code, location);
		this.rotation_center = center;
		this.radius = radius;
	}

	@Override
	public void move(double to) {
		double[] newPoint = { rotation_center[0] + radius * Math.cos(to), rotation_center[1] + radius * Math.sin(to), getPoint()[2]};

		setPoint(newPoint);
	}

	public void move(double to, double phase) {
		double[] newPoint = { rotation_center[0] + radius * Math.cos(to + phase), rotation_center[1] + radius * Math.sin(to + phase), getPoint()[2] };

		setPoint(newPoint);
	}

	@Override
	public void attack(double[] point) {
		System.out.println("x: " + point[0] + "y: " + point[1] + "에 대해 공격");
	}

	@Override
	public String reportStatus() {
		if (isStatus()) {
			return "공격 가능";
		} else {
			return "공격 불가";
		}
	}

	@Override
	public String toString() {
		return "기기 종류: UAV\n"  + "기기 코드: " + getDevice_code() + System.lineSeparator()
				+ "현재 위치: x: " + getPoint()[0] + " y: " + getPoint()[1] + " z: " + getPoint()[2]
				+ System.lineSeparator() + "공격 가능상태: " + reportStatus();
	}

	
	public double[] getRotation_center() {
		return rotation_center;
	}

	public void setRotation_center(double[] rotation_center) {
		this.rotation_center = rotation_center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
