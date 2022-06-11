package __08线程池.自定义线程池;/*
    @author wxg
    @date 2021/12/30-11:19
    */


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author capensis
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 5,
                5L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //  10个顾客请求
        try {
            //  执行
            IntStream.range(0, 10).<Runnable>mapToObj(i ->
                    () -> System.out.println(Thread.currentThread().getName() + " 业务处理 " + i))
                    .forEach(threadPoolExecutor::execute);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
