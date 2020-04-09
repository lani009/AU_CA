package app.org.lani;

public class Polygon {
    private final int vertex;
    private double angle;

    public Polygon(int vertex) {
        this.vertex = vertex;
    }

    public void print() {
        angle = 180 * (vertex - 2) / vertex;
        System.out.println(angle);
    }
}