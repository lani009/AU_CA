package app.org.lani;

import java.util.Scanner;

public class TriangleTest {
    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        
        Triangle triangle1;
        while(true) {
            System.out.println("첫번째 삼각형의 높이와 너비를 입력하세요");
            int input1 = key.nextInt();
            int input2 = key.nextInt();
            if(input1 < 0 || input2 < 0) {
                System.out.println("입력값이 음수가 될 수 없습니다.");
                continue;
            }
            triangle1 = new Triangle(input1, input2);
            break;
        }
        
        Triangle triangle2;
        while(true) {
            System.out.println("두번째 삼각형의 높이와 너비를 입력하세요");
            int input1 = key.nextInt();
            int input2 = key.nextInt();
            if(input1 < 0 || input2 < 0) {
                System.out.println("입력값이 음수가 될 수 없습니다.");
                continue;
            }
            triangle2 = new Triangle(input1, input2);
            break;
        }

        double area1 = triangle1.calculateArea();
        double area2 = triangle2.calculateArea();
        
        System.out.println(String.format("첫번째 삼각형의 넓이는 %.1f입니다.", area1));
        System.out.println(String.format("두번째 삼각형의 넓이는 %.1f입니다.", area2));

        System.out.println(String.format("가장 큰 넓이는 %.1f입니다.", Double.max(area1, area2)));
        key.close();
    }
}