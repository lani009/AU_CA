package exam;

public class GCS extends Vehicle {

    public GCS(int code, double[] location) {
        super(code, location);
    }

    @Override
    public void move(double to) {
        double[] point = new double[3];
        point[0] += to;
        point[1] += to;
        point[2] = 0;
        setPoint(point);
    }

    @Override
    public String toString() {
        double[] point = getPoint();
        return String.format("기기종류: %s\n기기코드: %d\n현재위치: x: %f y: %f z: %f", "GCS", getDeviceCode(), point[0], point[1], point[2]);
    }
    
}