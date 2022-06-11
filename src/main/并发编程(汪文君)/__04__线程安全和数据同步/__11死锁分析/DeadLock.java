package __04__线程安全和数据同步.__11死锁分析;

/**
 * The type Dead lock.
 *
 * @author wxg
 * @date 2022 /1/2-10:25
 * @quotes 小不忍则乱大谋
 */
public class DeadLock {
    private final Object lock = new Object();
    private final OtherService otherService;

    /**
     * Instantiates a new Dead lock.
     *
     * @param otherService the other service
     */
    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    /**
     * M 1.
     */
    public void m1() {
        synchronized (lock) {
            System.out.println(" ==== m1 ====");
            otherService.s1();
        }
    }

    /**
     * M 2.
     */
    public void m2() {
        synchronized (lock) {
            System.out.println(" ==== m2 ====");
        }
    }
}
