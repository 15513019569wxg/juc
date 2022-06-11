package __17__读写锁分离设计模式;

import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author wxg
 * @date 2022/1/9-19:30
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ReadWriteLockTest {
    /**
     *  This is the example for read write lock
     */
    private final static String TEXT = "Thisisthewxampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        IntStream.range(0, 2).forEach(i -> new Thread(() -> {
            for (int index = 0; index < TEXT.length(); index++) {
                try {
                    char c = TEXT.charAt(index);
                    shareData.write(c);
                    System.out.println(currentThread() + " write " + c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());

        // 创建10个线程进行数据读操作
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            while (true) {
                try {
                    System.out.println(currentThread() + " read " + new String(shareData.read()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());
    }
}
