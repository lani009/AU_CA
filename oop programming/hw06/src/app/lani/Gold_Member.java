package app.lani;

public class Gold_Member extends Member {
    private double discount;    //할인율

    public Gold_Member(String name, String phone, int purchase, double discount) {
        super(name, phone, purchase);   //부모클래스 생성자에 인자 전달
        this.discount = discount;
    }

    @Override
    public double calcSales() {
        return purchase * 1000 * (1-discount);  //할인율 적용하여 계산
    }
}