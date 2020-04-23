package app.lani.two;

import java.util.ArrayList;

public class ShapeTest {
    public static void main(String[] args) {
        ArrayList<Shape> array = new ArrayList<>();

        Shape s1 = new Rectangle();
        Shape s2 = new Triangle();
        Shape s3 = new Circle();

        array.add(s1);
        array.add(s2);
        array.add(s3);

        for (Shape i : array) {
            i.draw();
        }
    }
}