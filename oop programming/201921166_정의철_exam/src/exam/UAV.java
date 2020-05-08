package exam;

public class UAV extends Vehicle implements Attackable {
    private double[] rotation_center;
    private double radius;
    private boolean status;

    public UAV(int code, double[] location, double[] center, double radius) {
        super(code, location);
        this.radius = radius;
        rotation_center = center;
        status = true;
    }


    @Override
    public void attack(double[] point) {
        System.out.printf("[ x: %f, y: %f, 에 대해 공격]", point[0], point[1]);
    }

    @Override
    public String reportStatus() {
        if(status) return "공격 가능";
        else return "공격 불가";
    }

    public String toString() {
        double[] point = getPoint();
        return String.format("기기 종류: %s\n기기 코드: %d\n현재 위치: x: %f y: %f z: %f\n공격 가능 상태: %s", "UAV", getDeviceCode(), point[0], point[1], point[2], reportStatus());
    }

    public void move(double to) {
        double[] temp = new double[3];
        temp[0] = rotation_center[0] + radius * Math.cos(to);
        temp[1] = rotation_center[1] + radius * Math.sin(to);
        temp[2] = getPoint()[2];
        setPoint(temp);
    }

    public void move(double to, double phase) {
        move(to + phase);
    }

    public double getRadius() {
        return radius;
    }

    public boolean getStatus() {
        return status;
    }

    public double[] getCenter() {
        return rotation_center;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCenter(double[] center) {
        rotation_center = center;
    }
    
}