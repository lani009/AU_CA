package exam;

public abstract class Vehicle {
    private int device_code;
    private double[] point;

    public Vehicle(int code, double[] location) {
        device_code = code;
        point = location;
    }

    public abstract void move(double s);

    public abstract String toString();

    public int getDeviceCode() {
        return device_code;
    }

    public double[] getPoint() {
        return point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }

    public void setDeviceCode(int device_code) {
        this.device_code = device_code;
    }
}