package app.org.lani;

import java.util.Scanner;

public class PolygonTest {
    public static void main(String[] args) throws Exception {
        int point;
        Scanner input = new Scanner(System.in);

        System.out.println("꼭짓점 개수를 입력해 주세요");
        point = input.nextInt();

        Polygon poly = new Polygon(point);

        poly.print();
        input.close();
    }
}