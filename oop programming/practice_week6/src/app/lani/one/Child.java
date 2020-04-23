package app.lani.one;

public class Child extends Parent {
    private String childName;

    public Child(String name, String childName) {
        super(name);
        this.childName = childName;
    }
    
    @Override
    public void print() {
        System.out.println("자식 클래스의 print() 메소드");
        System.out.println("자식 이름: " + childName);
    }

    public void print_parent() {
        super.print();
    }
}