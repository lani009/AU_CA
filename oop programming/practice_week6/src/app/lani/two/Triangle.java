package app.lani.two;

@SuppressWarnings("unused")
public class Triangle extends Shape {
    private int base, height;

    @Override
    public void draw() {
        System.out.println("Triangle draw");
    }
}