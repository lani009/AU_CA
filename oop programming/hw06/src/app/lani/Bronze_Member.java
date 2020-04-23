package app.lani;

public class Bronze_Member extends Member {
    public Bronze_Member(String name, String phone, int purchase) {
        super(name, phone, purchase);   //부모클래스 생성자에 인자 전달
    }

    @Override
    public double calcSales() {
        return purchase * 1000; //값 계산 하여 리턴
    }
}