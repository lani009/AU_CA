package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText <T, M> extends IO<T, M> {

    public WriteText(T input, M output) {
        super(input, output);
    }

    public void writeData(M output, String to, ReadText<T, M> rt) {
        setOutput(output);
        if (output instanceof String) {
            File file = new File(to);
            try (FileWriter fw = new FileWriter(file)) {
                fw.append(rt.getTextString());
                System.out.println("출력이 완료되었습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("알 수 없는 데이터 타입입니다.");
        }
    }
    
}