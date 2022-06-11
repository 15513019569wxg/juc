package __21__线程上下文设计模式.__03ThreadLocal;

import java.util.Random;

/**
 * @author wxg
 * @date 2022/1/12-16:29
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadLocalSimulatorTest {

    private final static ThreadLocalSimulator<String> THREAD_LOCAL = new ThreadLocalSimulator<>();

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            THREAD_LOCAL.set("Thread-1");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + THREAD_LOCAL.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            THREAD_LOCAL.set("Thread-2");
            try {
                Thread.sleep(RANDOM.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + THREAD_LOCAL.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(" ============================================= ");
        System.out.println(Thread.currentThread().getName() + ": " + THREAD_LOCAL.get());
    }


}
