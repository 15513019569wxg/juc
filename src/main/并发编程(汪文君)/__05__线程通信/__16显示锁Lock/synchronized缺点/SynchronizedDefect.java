package __05__线程通信.__16显示锁Lock.synchronized缺点;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/6-17:18
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class SynchronizedDefect {
    public synchronized void syncMethod(){
        try {
            System.out.println(Thread.currentThread().getName() + " is running ");
            // ①无法控制阻塞时长
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final SynchronizedDefect synchronizedDefect = new SynchronizedDefect();
        final Thread t1 = new Thread(synchronizedDefect::syncMethod, "T1");
        // make sure the t1 started
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Thread t2 = new Thread(synchronizedDefect::syncMethod, "T2");
        t2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            // 这句话不会打印
            System.out.println(Thread.currentThread().getName() + " am waking up");

        }
        // ②阻塞不能被中断
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }
}
