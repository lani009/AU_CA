package app;

public class PairTest {
    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("박효범", 123);
        Integer val = MyUtil.getValue(pair, "박효범");
        System.out.println(val);

        ChildPair<String, Integer> pair2 = new ChildPair<>("박효범", 123);
        Integer val2 = MyUtil.getValue(pair2, "이장근");
        System.out.println(val2);
    }
}