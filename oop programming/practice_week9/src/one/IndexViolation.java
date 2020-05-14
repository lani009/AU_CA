package one;

public class IndexViolation {
    public static void main(String[] args) {
        int[] array = {1,1,1};
        int value = 0;

        try {
            value = array[5];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열의 인덱스가 잘못되었습니다.");
        } finally {
            System.out.println("try/catch 통과");
        }

        System.out.println(value);
    }
}