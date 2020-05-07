package app;

public abstract class TwoDimensionalShape extends Shape {
    private double shapeArea;

    public TwoDimensionalShape(String shapeName) {
        super(shapeName);
        shapeArea = 0;
    }

    public void setShapeArea(double shapeArea) {
        this.shapeArea = shapeArea;
    }

    public double getShapeArea() {
        return shapeArea;
    }
}