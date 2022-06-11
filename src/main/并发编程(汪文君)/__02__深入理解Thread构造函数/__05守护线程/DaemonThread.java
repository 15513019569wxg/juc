package __02__深入理解Thread构造函数.__05守护线程;

/**
 * The type Daemon thread.
 *
 * @author wxg
 * @date 2021 /12/31-21:31
 * @quotes 小不忍则乱大谋
 */
public class DaemonThread {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread thread = new Thread(() -> {
            try {
                while (true) {
                    //noinspection BusyWait
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2_000L);
        System.out.println("Main thread finished lifecycle. ");



    }
}
