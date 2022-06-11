package __05辅助类;/*
    @author wxg
    @date 2021/12/29-15:34
    */


import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author capensis
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //  创建一个计数器,设置一个初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        IntStream.rangeClosed(1, 6).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 号同学离开了教室 ");
            //  计数
            countDownLatch.countDown();
        }, String.valueOf(i)).start());
        // 等待
       countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }
}
