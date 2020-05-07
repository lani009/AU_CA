package app;

public class Square extends TwoDimensionalShape {
    private double length, breadth;

    public Square(String shapeName, double length, double breadth) {
        super(shapeName);
        this.length = length;
        this.breadth = breadth;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public double getLength() {
        return length;
    }

    public double getBreadth() {
        return breadth;
    }

    @Override
    public void calculateArea() {
        setShapeArea(length * breadth);
    }
}