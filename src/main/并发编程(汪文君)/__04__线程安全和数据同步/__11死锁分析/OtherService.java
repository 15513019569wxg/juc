package __04__线程安全和数据同步.__11死锁分析;

/**
 * The type Other service.
 *
 * @author wxg
 * @date 2022 /1/2-10:24
 * @quotes 小不忍则乱大谋
 */
public class OtherService {
    private final Object lock = new Object();

    private DeadLock deadLock;

    /**
     * Sets dead lock.
     *
     * @param deadLock the dead lock
     */
    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    /**
     * S 1.
     */
    public void s1() {
        synchronized (lock) {
            System.out.println("=========== s1 ===========");
        }
    }

    /**
     * S 2.
     */
    public void s2() {
        synchronized (lock) {
            System.out.println("=========== s2 ===========");
            deadLock.m2();
        }
    }
}
