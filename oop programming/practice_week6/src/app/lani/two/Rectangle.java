package app.lani.two;

@SuppressWarnings("unused")
public class Rectangle extends Shape{
    private int width, height;

    @Override
    public void draw() {
        System.out.println("Rectangle draw");
    }
}