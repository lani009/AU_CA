package week2;

public class SwitchStringExample {
    public static void main(String[] args) {
        String position = "과장";
        int money;

        switch (position) {
            case "부장":
                money = 700;
                break;

            case "과장":
                money = 500;
                break;
        
            default:
                money = 300;
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(money);
        sb.append("만원");
        System.out.println(sb.toString());
    }
}