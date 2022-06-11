package __04__线程安全和数据同步.__10this锁;

/**
 * The type Synchronized this.
 *
 * @author wxg
 * @date 2022 /1/2-9:31
 * @quotes 小不忍则乱大谋
 */
public class SynchronizedThis {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        final ThisLock thisLock = new ThisLock();
        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();

        new Thread("T3") {
            @Override
            public void run() {
                thisLock.m3();
            }
        }.start();

        new Thread("T4") {
            @Override
            public void run() {
                thisLock.m4();
            }
        }.start();

        new Thread("T5") {
            @Override
            public void run() {
                thisLock.m5();
            }
        }.start();

        new Thread("T6") {
            @Override
            public void run() {
                thisLock.m6();
            }
        }.start();
    }

}


/**
 * The type This lock.
 */
class ThisLock {
    private final Object lock  = new Object();

    /**
     * m1 = m2 = m6 , m3 = m4, m5
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * M 2.
     */
    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * M 3.
     */
    public void m3() {
        synchronized(lock) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * M 4.
     */
    public void m4() {
        synchronized(lock) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * M 5.
     */
    public void m5() {
        synchronized(SynchronizedThis.class) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * M 6.
     */
    public void m6() {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}