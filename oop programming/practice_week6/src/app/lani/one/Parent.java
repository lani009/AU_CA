package app.lani.one;

public class Parent {
    public String name;

    public Parent(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("부모 클래스의 print() 메소드");
        System.out.println("부모 이름: " + name);
    }
}