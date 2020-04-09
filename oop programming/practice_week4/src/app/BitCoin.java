package app;

public class BitCoin {
    private String name;
    private double price;
    private int number;

    public BitCoin(String name, double price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public BitCoin setName(String name) {
        this.name = name;
        return this;
    }

    public BitCoin setPrice(double price) {
        this.price = price;
        return this;
    }

    public BitCoin setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public double calcPrice() {
        return price * number;
    }

    @Override
    public String toString() {
        return String.format("Coin 이름: %s, 가격: %f, 개수: %d, 총 가격: %f", getName(), getPrice(), getNumber(), calcPrice());
    }


}