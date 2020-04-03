package week2;

public class EnhancedForExample {
    public static void main(String[] args) {
        int[] scores = {95, 71, 84, 93, 87};

        int sum = 0;
        for (int i : scores) {
            sum += i;
        }

        System.out.println(sum);
    }
}