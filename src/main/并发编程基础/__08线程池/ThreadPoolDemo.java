package __08线程池;/*
    @author wxg
    @date 2021/12/30-10:12
    */


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


/**
 * @author capensis
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //  一池五线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //  一池一线程
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        //  一池可扩容线程
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        //  10个顾客请求
        try {
            //  执行
            IntStream.range(0, 10).<Runnable>mapToObj(i -> () -> System.out.println(Thread.currentThread().getName() + " 业务处理 " + i)).forEach(executorService2::execute);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService2.shutdown();
        }

    }
}
