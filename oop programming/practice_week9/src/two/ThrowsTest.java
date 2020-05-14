package two;

public class ThrowsTest {
    public static void main(String[] args) {
        try {
            method1();
        } catch(ClassNotFoundException e) {
            System.out.println("해당 클래스가 존재하지 않습니다.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    public static void method1() throws ClassNotFoundException {
        Class myClass = Class.forName("java.lang.String2");
    }
}
