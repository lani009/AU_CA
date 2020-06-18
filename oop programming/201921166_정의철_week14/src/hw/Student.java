package hw;

public class Student {
    private String id;
    private int koreanScore;
    private int mathScore;
    private int englishScore;

    public Student(String id, int koreanScore, int mathScore, int englishScore) {
        this.id = id;
        this.koreanScore = koreanScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    public String getId() {
        return this.id;
    }

    public int getKoreanScore() {
        return this.koreanScore;
    }

    public int getMathScore() {
        return this.mathScore;
    }

    public int getEnglishScore() {
        return this.englishScore;
    }

    @Override
    public String toString() {
        return String.format("학번: %s, 국어: %d, 수학: %d, 영어: %d", id, koreanScore, mathScore, englishScore);
    }

}