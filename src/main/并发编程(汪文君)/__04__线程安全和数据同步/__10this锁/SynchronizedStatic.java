package __04__线程安全和数据同步.__10this锁;


/**
 * The type Synchronized static.
 */
class SynchronizedStatic {
    static {
        synchronized (SynchronizedStatic.class) {
            System.out.println(" static " + Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * M 1.
     */
    public synchronized static void m1() {
        System.out.println(" m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * M 2.
     */
    public synchronized static void m2() {
        System.out.println(" m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * M 3.
     */
    public static void m3() {
        System.out.println(" m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
