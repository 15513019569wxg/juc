package __05__线程通信.__16显示锁Lock;

import java.util.Optional;

/**
 * The type Synchronized problem.
 *
 * @author wxg
 * @date 2022 /1/3-12:10
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class SynchronizedProblem {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(SynchronizedProblem::run).start();
        Thread.sleep(1_000L);
        final Thread thread2 = new Thread(SynchronizedProblem::run);
        thread2.start();
        //  等待thread2 2s
        Thread.sleep(2_000L);
        thread2.interrupt();
        //  打断操作无济于事  true
        Optional.of(thread2.getName() + " " + thread2.isInterrupted()).ifPresent(System.out::print);


    }

    /**
     * t1线程抢到锁之后，除非任务完成，否额不会释放锁。这样一来，thread2线程就只能一直处于阻塞状态，即使想要提早
     * 结束thread2线程，利用interrupt()打断也无法做到（因为t1不释放锁，thread2还是要阻塞），此时怎么解决这个问题呢？
     * 这时，可以考虑使用自定义显示锁。
     */
    private static synchronized void run() {
        System.out.println(Thread.currentThread().getName());
        while(true){

        }
    }
}
