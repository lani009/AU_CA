package org.lani;

public class Employee {
    private String name;    //이름
    private int type;    //직급
    private double salary;  //연봉

    /**
     * 생성자를 통해 인스턴스 변수 초기화
     * @param name 이름
     * @param type 직급
     * @param salary 연봉
     */
    public Employee(String name, int type, double salary) {
        this.name = name;
        this.type = type;
        this.salary = salary;
    }

    public Employee() { }   //setter를 통해 인스턴스 변수를 초기화 하는 경우.

    /**
     * @param name 이름
     */
    public void setName(String name) {
        this.name = name;   //이름 초기화
    }

    public void setType(int type) {
        this.type = type;   //직급 초기화
    }

    public void setSalary(double salary) {
        this.salary = salary;   //연봉 초기화
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