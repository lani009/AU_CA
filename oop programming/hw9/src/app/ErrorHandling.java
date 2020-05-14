package app;

public class ErrorHandling {
    public static void main(String[] args) {
      
        try {
            String input1= args[0];
            String input2= args[1];
            
            int value1 = Integer.parseInt(input1);
            int value2 = Integer.parseInt(input2);
            
            int result =value1 +value2;

            System.out.println("result:"+ result);
    
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("배열의 인덱스가 잘못되었습니다.");
            e.printStackTrace();   
        } catch(NumberFormatException e) {
            System.out.println("입력된 값이 정수가 아닙니다.");
            e.printStackTrace();
        }
    }
}