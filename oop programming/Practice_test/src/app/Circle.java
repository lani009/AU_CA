package app;

public class Circle extends TwoDimensionalShape {
    private double radius;
    
    public Circle(String shapeName, double radius) {
        super(shapeName);
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void calculateArea() {
        setShapeArea(radius * radius * 3.14);
    }
}