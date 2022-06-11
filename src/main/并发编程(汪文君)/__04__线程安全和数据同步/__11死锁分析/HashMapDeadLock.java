package __04__线程安全和数据同步.__11死锁分析;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * The type Hash map dead lock.
 *
 * @author wxg
 * @date 2022 /1/2-11:56
 * @quotes 小不忍则乱大谋
 */
public class HashMapDeadLock {

    private final HashMap<String, String> map = new HashMap<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
        IntStream.range(0, 2).forEach(i -> new Thread(() ->
                IntStream.range(0, Integer.MAX_VALUE).forEach(j -> hashMapDeadLock.add(String.valueOf(j), String.valueOf(j))))
                .start());
    }

    /**
     * Add.
     *
     * @param key   the key
     * @param value the value
     */
    public void add(String key, String value) {
        this.map.put(key, value);
    }
}
