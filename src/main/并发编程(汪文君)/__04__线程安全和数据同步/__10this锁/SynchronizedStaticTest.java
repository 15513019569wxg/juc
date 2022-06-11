package __04__线程安全和数据同步.__10this锁;

/**
 * The type Synchronized static test.
 *
 * @author wxg
 * @date 2022 /1/2-9:49
 * @quotes 小不忍则乱大谋
 */
public class SynchronizedStaticTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        new Thread("T1") {
            @Override
            public void run() {
                SynchronizedStatic.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                SynchronizedStatic.m2();
            }
        }.start();

        new Thread("T3") {
            @Override
            public void run() {
                SynchronizedStatic.m3();
            }
        }.start();
    }
}
