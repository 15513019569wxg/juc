package __19__Future设计模式;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/10-17:14
 * @quotes 小不忍则乱大谋
 */
public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------------------- 不需要返回值 -------------------------");
        // 定义不需要返回值的FutureService
        FutureService<Void, Void> service = FutureService.newService();
        // submit()为立即返回的方法
        Future<?> future = service.submit(
                // 重写Runnable的run()
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("I am finish done");
                });
        // get() 会使当前线程进入阻塞
        System.out.println(future.get());

        TimeUnit.SECONDS.sleep(5);
        System.out.println("------------------------- 使用get()接受返回值 -------------------------");

        // 定义有返回值的FutureService
        FutureService<String, Integer> service2 = FutureService.newService();
        // submit()为立即返回的方法
        Future<Integer> future2 = service2.submit(
                // 重写Task的get()
                input -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 返回的计算结果
                    return input.length();
                }, "Hello");
        // get() 会使当前线程进入阻塞,最终会返回计算的结果
        System.out.println(future2.get());

        TimeUnit.SECONDS.sleep(5);
        System.out.println("------------------------- 使用Callback()接受返回值 -------------------------");

        // 定义有返回值的FutureService
        FutureService<String, Integer> service3 = FutureService.newService();
        // submit()为立即返回的方法
        final Future<Integer> future3 = service3.submit(
                // 重写Task的get()
                input -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 返回的计算结果
                    return input.length();
                }, "Hello",
                // 重写了call()  x -> System.out.println(x), 不需要使用get()方法再去主动询问
                System.out::println);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("任务状态：" + future3.done());

    }
}
