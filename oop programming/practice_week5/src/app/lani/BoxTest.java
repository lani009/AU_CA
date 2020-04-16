package app.lani;

public class BoxTest {
    public static void main(String[] args) {
        Box box1 = new Box(17, 50, 30);
        Box box2 = new Box(17, 50, 30);
        System.out.println(box1.isSameBox(box2));

        Box box3 = new Box(17, 50, 3);
        Box box4 = new Box(1, 5, 78);
        System.out.println(box3.isSameBox(box4));
    }
}