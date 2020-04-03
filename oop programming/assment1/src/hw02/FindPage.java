package hw02;

import java.util.Arrays;
import java.util.Scanner;

public class FindPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		
		// 정수하나를 입력받아 변수에 대입
		System.out.print("전체 page개수를 입력하세요 ");
        int totalPage = scanner.nextInt();
        
        boolean[] pageArr = new boolean[totalPage];
        Arrays.fill(pageArr, false);

        for (int i = 0; i < pageArr.length - 1; i++) {
            pageArr[scanner.nextInt() - 1] = true;
        }

        for (int i = 0; i < pageArr.length; i++) {
            if(!pageArr[i]) {
                System.out.println((i + 1) + "번째 page가 빠졌습니다.");
                break;
            }
        }
        scanner.close();
    }
}