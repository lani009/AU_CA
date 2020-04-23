package app.lani.two;

public class Rectangle extends Shape{
    private int width, height;

    @Override
    public void draw() {
        System.out.println("Rectangle draw");
    }
}