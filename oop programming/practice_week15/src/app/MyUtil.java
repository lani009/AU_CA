package app;

public class MyUtil {
    public static <P extends Pair<K, V>, K, V> V getValue(P pair, K key) {
        if(pair.getKey().equals(key)) {
            return pair.getValue();
        } else {
            return null;
        }
    }
}