package application.one;

import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();

        set.add(new Student(201721160, "구대영"));
        set.add(new Student(201721163, "이장근"));
        set.add(new Student(201721160, "박효범"));

        set.stream().forEach(System.out::println);
    }
}