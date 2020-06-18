package application.Two;

public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }    
}