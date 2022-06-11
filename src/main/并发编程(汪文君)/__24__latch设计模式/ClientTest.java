package __24__latch设计模式;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/14-13:58
 * @quotes 小不忍则乱大谋
 */
public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        // 定义一个门阀
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Arrays.asList(new ProgrammerTravel(countDownLatch, "Alex", "Bus"),
                new ProgrammerTravel(countDownLatch, "Gavin", "Walking"),
                new ProgrammerTravel(countDownLatch, "Jack", "Subway"),
                new ProgrammerTravel(countDownLatch, "Dillon", "Bicycle"))
                .forEach(Thread::start);
        // 当前线程（main 线程会进入阻塞, 直到四个程序员全部都到达目的地）
        countDownLatch.await();
        System.out.println("================= all of programmer arrived =================");

        System.out.println("========================================================");

        CountDownLatch countDownLatch2 = new CountDownLatch(4);
        Arrays.asList(new ProgrammerTravel(countDownLatch2, "Alex", "Bus"),
                new ProgrammerTravel(countDownLatch2, "Gavin", "Walking"),
                new ProgrammerTravel(countDownLatch2, "Jack", "Subway"),
                new ProgrammerTravel(countDownLatch2, "Dillon", "Bicycle"))
                .forEach(Thread::start);
        // 当前线程（main 线程会进入阻塞, 直到四个程序员全部都到达目的地）
        try {
            countDownLatch2.await(TimeUnit.SECONDS, 5);
            System.out.println("================= all of programmer arrived =================");
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
            System.out.println("================= The plan decided to cancel =================");
        }

    }
}
