package __04__线程安全和数据同步.__09synchronized关键字;

/**
 * The type Synchronized runnable method.
 *
 * @author wxg
 * @date 2022 /1/2-8:43
 * @quotes 小不忍则乱大谋
 */
public class SynchronizedRunnableMethod implements Runnable{

    private int index = 1;
    private final static int MAX = 500;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        final SynchronizedRunnableMethod synchronizedRunnableMethod = new SynchronizedRunnableMethod();

        final Thread sync1 = new Thread(synchronizedRunnableMethod,"一号线程");
        final Thread sync2 = new Thread(synchronizedRunnableMethod,"二号线程");
        final Thread sync3 = new Thread(synchronizedRunnableMethod,"三号线程");
        sync1.start();
        sync2.start();
        sync3.start();
    }

    @Override
    public synchronized void run() {
        while (index <= MAX) {
            try {
                //noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 的号码是 " + index++);
        }
    }
}
