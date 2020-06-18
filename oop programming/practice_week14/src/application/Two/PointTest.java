package application.Two;

import java.util.LinkedList;
import java.util.List;

public class PointTest {
    public static void main(String[] args) {
        
        List<Point> pointList = new LinkedList<>();
        
        pointList.add(new Point(2,3));
        pointList.add(new Point(4,6));
        pointList.add(new Point(7,2));
        pointList.add(new Point(5,5));

        System.out.println("before sorting");
        pointList.stream().forEach(System.out::println);

        pointList.sort((o1, o2) -> {return (o1.getY() < o2.getY()) ? 1 : ((o1.getY() == o2.getY()) ? 0 : -1);});

        System.out.println("after sorting");
        pointList.stream().forEach(System.out::println);
    }
}