package __05辅助类;/*
    @author wxg
    @date 2021/12/29-16:09
    */


import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author capensis
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //  创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);
        //  模拟6辆汽车
        IntStream.range(0, 6).forEach(i -> new Thread(() -> {
            // 抢占车位
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " 抢到了车位");
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                System.out.println(Thread.currentThread().getName() + " 离开了车位");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }, String.valueOf(i)).start());
    }
}
