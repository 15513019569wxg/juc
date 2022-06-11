package __10异步回调;/*
    @author wxg
    @date 2021/12/30-20:59
    */


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author capensis
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //  同步调用
        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 同步调用");
        });
        voidCompletableFuture.get();

        //  异步调用
        final CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 异步调用");
           // int i = 10 / 0;
            return 1024;
        });
        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("------" + t);
            System.out.println("------" + u);
        }).get();
    }
}
