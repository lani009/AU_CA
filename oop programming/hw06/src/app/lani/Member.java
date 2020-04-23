package app.lani;

public class Member {
    protected String name;    //고객이름
    protected String phone;   //연락처
    protected int purchase;   //구매 개수

    public Member(String name, String phone, int purchase) {
        this.name = name;
        this.phone = phone;
        this.purchase = purchase;
    }

    public double calcSales() {
        System.out.println("등급이 정해지지 않았습니다.");
        return 0;
    }
}