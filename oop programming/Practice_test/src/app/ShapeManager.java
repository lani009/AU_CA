package app;

import java.util.Scanner;


/**
 * 객프 작년 중간고사. 23분 소요.
 */
public class ShapeManager {
    public static void main(String[] args) throws Exception {
        Scanner key = new Scanner(System.in);
        System.out.print("1) Circle\nradius:");
        double radius = key.nextDouble();
        System.out.print("shape name:");
        String shapeName = key.next();

        Shape[] shape = new Shape[2];
        shape[0] = new Circle(shapeName, radius);

        System.out.print("2) Square\nlength:");
        double length = key.nextDouble();
        System.out.print("breadth:");
        double breadth = key.nextDouble();
        System.out.print("shape name:");
        shapeName = key.next();

        shape[1] = new Square(shapeName, length, breadth);

        for (Shape i : shape) {
            i.calculateArea();
        }

        TwoDimensionalShape temp1 = (TwoDimensionalShape) shape[0];
        TwoDimensionalShape temp2 = (TwoDimensionalShape) shape[1];

        if(temp1.getShapeArea() > temp2.getShapeArea()) {
            System.out.printf("Circle형의 면적이 Square형의 면적보다 %f (m^2) 더 넓습니다.", temp1.getShapeArea() - temp2.getShapeArea());
        } else {
            System.out.printf("Square형의 면적이 Circle형의 면적보다 %f (m^2) 더 넓습니다.", temp2.getShapeArea() - temp1.getShapeArea());
        }

        key.close();
    }
}