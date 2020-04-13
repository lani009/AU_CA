package org.lani;

public class Employee {
    private String name;    //이름
    private int type;    //직급
    private double salary;  //연봉

    /**
     * @param name 이름
     */
    public Employee setName(String name) {
        this.name = name;   //이름 초기화
        return this;    //자신의 참조값 리턴
    }

    public Employee setType(int type) {
        this.type = type;   //직급 초기화
        return this;    //자신의 참조값 리턴
    }

    public Employee setSalary(double salary) {
        this.salary = salary;   //연봉 초기화
        return this;    //자신의 참조값 리턴
    }

    public String getName() {
        return name;    //employee의 이름 리턴
    }

    public int getType() {
        return type;    //employee의 직급 리턴
    }

    public double getSalary() {
        return salary;  //employee의 연봉 리턴
    }
}