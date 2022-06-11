package __03__ThreadApi的详细介绍.__06ThreadApi理解.__03join方法;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The type Thread join.
 *
 * @author wxg
 * @date 2022 /1/1-14:29
 * @quotes 小不忍则乱大谋
 */
public class ThreadJoin {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        //  ①定义两个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
        //  ②启动这两个线程
        threads.forEach(Thread::start);
        //  ③执行这两个线程的join方法
        for (Thread thread : threads) {
            thread.join();
        }
        //  main线程循环输出
        IntStream.range(0, 10).mapToObj(i -> Thread.currentThread().getName() + " # " + i).forEach(System.out::println);
    }

    /**
     * 创建一个简单的线程，每个线程只是简单的循环输出
     * @param seq 线程的序号
     * @return  创建好的线程
     */
    private static Thread create(int seq) {
        //noinspection AlibabaAvoidManuallyCreateThread
        return new Thread(() -> IntStream.range(0, 10).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + " # " + i);
            shortSleep();
        }), String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
