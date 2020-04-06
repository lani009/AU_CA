package org.lani;

public class Employee {
    private String name;    //이름
    private int type;    //직급
    private double salary;  //연봉

    public Employee() {

    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Employee setType(int type) {
        this.type = type;
        return this;
    }

    public Employee setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public double getSalary() {
        return salary;
    }
}