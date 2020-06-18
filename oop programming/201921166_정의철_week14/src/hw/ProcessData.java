package hw;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessData {
    private List<Student> list;

    public void readData() throws IOException {
        File file = new File("input.txt");

        Stream<String> lines = Files.lines(file.toPath());
        list = lines.map(str -> {
            StringTokenizer strtok = new StringTokenizer(str, ",");
            return new Student(strtok.nextToken(), Integer.parseInt(strtok.nextToken()),
                    Integer.parseInt(strtok.nextToken()), Integer.parseInt(strtok.nextToken()));
        }).collect(Collectors.toList());

        lines.close();
    }

    public void sortData() {
        list.sort((o1, o2) -> {
            return o1.getId().compareTo(o2.getId());
        }); // 학번 오름차순

        list.sort((o1, o2) -> {
            return o2.getKoreanScore() - o1.getKoreanScore();
        }); // 국어 내림차순

        list.sort((o1, o2) -> {
            return o2.getMathScore() - o1.getMathScore();
        }); // 수학 내림차순

        list.sort((o1, o2) -> {
            return o2.getEnglishScore() - o1.getEnglishScore();
        }); // 영어 내림차순
    }

    public void printData() {
        list.stream().forEach(System.out::println);
    }
}
