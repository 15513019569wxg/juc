package __04__线程安全和数据同步.__09synchronized关键字;

/**
 * The type Synchronized runnable code block.
 *
 * @author wxg
 * @date 2022 /1/2-8:58
 * @quotes 小不忍则乱大谋
 */
public class SynchronizedRunnableCodeBlock implements Runnable{

    private int index = 1;
    private final static int MAX = 500;

    /**
     * The constant MONITOR.
     */
    final static Object MONITOR = new Object();

    @Override
    public void run() {
        synchronized(MONITOR){
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        final SynchronizedRunnableCodeBlock synchronizedRunnableCodeBlock = new SynchronizedRunnableCodeBlock();
        final Thread sync1 = new Thread(synchronizedRunnableCodeBlock, "一号线程");
        final Thread sync2 = new Thread(synchronizedRunnableCodeBlock, "二号线程");
        final Thread sync3 = new Thread(synchronizedRunnableCodeBlock, "三号线程");
        sync1.start();
        sync2.start();
        sync3.start();
    }
}
