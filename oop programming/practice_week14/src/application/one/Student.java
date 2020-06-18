package application.one;

import java.util.Objects;

public class Student {
    private int stuNum;
    private String name;

    public Student(int stuNum, String name) {
        this.stuNum = stuNum;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d :: %s", stuNum, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNum);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            return getStuNum() == (((Student) obj).getStuNum());
        } else {
            return false;
        }
    }

    public int getStuNum() {
        return this.stuNum;
    }

    public String getName() {
        return this.name;
    }
    
}