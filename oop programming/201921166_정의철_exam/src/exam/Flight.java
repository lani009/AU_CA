package exam;

public class Flight extends Vehicle {

    public Flight(int code, double[] location) {
        super(code, location);
    }

    @Override
    public void move(double to) {
        double[] point = getPoint();
        point[0] += to;
        point[1] += to;
        // point[2] = 0;
        setPoint(point);
    }

    @Override
    public String toString() {
        double[] point = getPoint();
        return String.format("Flight 위치 x: %f y: %f z: %f)", point[0], point[1], point[2]);
    }

    public void communication(Vehicle v) {
        double[] vehiclePoint = v.getPoint();
        double[] point = getPoint();
        double distence = 0;
        distence = Math.sqrt(Math.pow(vehiclePoint[0] - point[0], 2) +
                    Math.pow(vehiclePoint[1] - point[1], 2) + Math.pow(vehiclePoint[2] - point[2], 2));
        
        if(distence < 20) {
            System.out.println(v);
        }
    }
    
}