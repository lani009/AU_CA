package app.lani.two;

@SuppressWarnings("unused")
public class Circle extends Shape {
    private int radius;

    @Override
    public void draw() {
        System.out.println("Circle draw");
    }
}