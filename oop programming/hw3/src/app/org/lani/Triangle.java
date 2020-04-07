package app.org.lani;

public class Triangle {
    private final int width;
    private final int height;

    public Triangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double calculateArea() {
        return width * height / 2.0;
    }
}