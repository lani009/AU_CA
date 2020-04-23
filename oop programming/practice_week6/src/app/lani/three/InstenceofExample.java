package app.lani.three;

public class InstenceofExample {
    public static void method1(Parent p ) {
        if(p instanceof Child) {
            Child c = (Child) p;
            System.out.println("method1 - child로 변환 성공");
        } else {
            System.out.println("method1 - child로 변환 실패");
        }
    }

    public static void method2(Parent p) {
        Child c = (Child) p;
        System.out.println("method2 - child로 변환 성공");
    }

    public static void main(String[] args) {
        Parent parentA = new Child();

        method1(parentA);
        method2(parentA);

        Parent parentB = new Parent();

        method1(parentB);
        method2(parentB);
    }
}