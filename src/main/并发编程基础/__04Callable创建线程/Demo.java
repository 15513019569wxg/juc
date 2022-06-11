package __04Callable创建线程;/*
    @author wxg
    @date 2021/12/29-13:54
    */


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable {
    @Override
    public void run() {}
}


class MyThread2 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 0;
    }
}



/**
 * @author capensis
 */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread(new MyThread(),"runnable").start();

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        //  使用lambda表达式
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() ->{
                System.out.println(Thread.currentThread().getName() + " come in callable");
                return 1024;
        });

        //  使用Callable创建线程
        new Thread(integerFutureTask,"Lucy").start();
        new Thread(futureTask,"Mary").start();

//        while(!integerFutureTask.isDone()){
//            System.out.println("wait...........");
//        }

        //  获取Callable的返回值
        System.out.println(integerFutureTask.get());
        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + " over ");
    }
}
