package app;

public class Main {
    private static final String from = "input.txt";
    private static final String to = "output.txt";
    public static void main(String[] args) {
        ReadText<String, String> rText = new ReadText<String,String>(from, to);
        WriteText<String, String> wText = new WriteText<String,String>(from, to);
        rText.readData("input", from);

        wText.writeData("output", to, rText);
    }
}