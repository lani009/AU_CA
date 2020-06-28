package app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadText <T, M> extends IO<T, M> {

    private String textString = "";
    private int numberOfSpace = 0;

    public ReadText(T input, M output) {
        super(input, output);
    }

    public String getTextString() {
        return this.textString;
    }

    public int getNumberOfSpace() {
        return this.numberOfSpace;
    }

    public void readData(T input, String from) {
        setInput(input);
        if (input instanceof String) {
            File file = new File(from);
            try (FileReader fr = new FileReader(file)) {
                char[] charBuffer = new char[10];
                int cnt = 0;
                while(fr.read(charBuffer) != -1) {
                    for (int i = 0; i < charBuffer.length; i++) {
                        cnt++;
                        if('a' <= charBuffer[i] && charBuffer[i] <= 'z') {
                            textString += charBuffer[i];
                        } else {
                            numberOfSpace++;
                        }
                    }
                }
                System.out.printf("text 파일의 총 글자 수: %d\n", cnt);
                System.out.printf("text 파일의 알파벳 수: %d\n", cnt - getNumberOfSpace());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("알 수 없는 데이터 타입입니다.");
        }
    }
}