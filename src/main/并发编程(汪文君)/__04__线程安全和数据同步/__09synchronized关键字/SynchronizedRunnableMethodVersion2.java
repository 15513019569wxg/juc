package __04__线程安全和数据同步.__09synchronized关键字;

/**
 * The type Synchronized runnable method version 2.
 *
 * @author wxg
 * @date 2022 /1/2-9:08
 * @quotes 小不忍则乱大谋
 */
public class SynchronizedRunnableMethodVersion2 implements Runnable {
    private final static int MAX = 500;
    private int index = 1;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        final SynchronizedRunnableMethodVersion2 synchronizedRunnableMethodVersion2 = new SynchronizedRunnableMethodVersion2();
        final Thread sync1 = new Thread(synchronizedRunnableMethodVersion2, "一号线程");
        final Thread sync2 = new Thread(synchronizedRunnableMethodVersion2, "二号线程");
        final Thread sync3 = new Thread(synchronizedRunnableMethodVersion2, "三号线程");
        sync1.start();
        sync2.start();
        sync3.start();
    }

    @Override
    public void run() {
        while (true) {
            //  采用这种方法可以避免出现锁饥饿现象
            if (ticket()) {
                break;
            }
        }
    }

    /**
     * 该方法保证“在进行一次判断并且打印结果之后，某个线程就释放锁。然后，三个线程继续争抢锁”
     * @return index > MAX 是否成立
     */
    private synchronized boolean ticket() {
        //  get fields
        if (index > MAX) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       /*
           1、get field index
           2、index = index + 1
           3、put index to memory
        */
        System.out.println(Thread.currentThread().getName() + " 的号码是 " + index++);
        return false;
    }
}
