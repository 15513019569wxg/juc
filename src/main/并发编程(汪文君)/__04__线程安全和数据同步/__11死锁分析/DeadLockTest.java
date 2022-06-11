package __04__线程安全和数据同步.__11死锁分析;

/**
 * The type Dead lock test.
 *
 * @author wxg
 * @date 2022 /1/2-10:40
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class DeadLockTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final OtherService otherService = new OtherService();
        final DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();
    }
}
