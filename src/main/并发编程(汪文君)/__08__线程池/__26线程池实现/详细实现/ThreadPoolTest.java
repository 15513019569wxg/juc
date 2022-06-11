package __08__线程池.__26线程池实现.详细实现;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/4-21:47
 * @quotes 小不忍则乱大谋
 */
public class ThreadPoolTest {
    private static final int NUMBER = 500;

    public static void main(String[] args) throws InterruptedException {
        //  定义线程池，初始化线程数量为2，核心数量为4, 最大线程数为6,任务队列最多允许1000个任务
        final BasicThreadPool basicThreadPool = new BasicThreadPool(2, 6, 4, 1000);
        for (int i = 0; i < NUMBER; i++) {
            basicThreadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            System.out.println("getActiveCount: " + basicThreadPool.getActiveCount());
            System.out.println("getQueueSize: " + basicThreadPool.getQueueSize());
            System.out.println("getCoreSize: " + basicThreadPool.getCoreSize());
            System.out.println("getMaxSize: " + basicThreadPool.getMaxSize());

            System.out.println("===============================================");
            TimeUnit.SECONDS.sleep(15);
            // 线程池在12s后将被shutdown
            // basicThreadPool.shutdown();
            // 使main线程join
            // Thread.currentThread().join();

        }
    }
}
