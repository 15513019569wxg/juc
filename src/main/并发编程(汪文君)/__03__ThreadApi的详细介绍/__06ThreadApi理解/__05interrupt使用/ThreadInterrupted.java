package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread interrupted.
 *
 * @author wxg
 * @date 2022 /1/1-11:09
 * @quotes 小不忍则乱大谋
 */
public class ThreadInterrupted {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        final Thread thread = new Thread(() -> {
//            while (true) {
//                System.out.println(Thread.interrupted());
//            }
//        });
//
//        thread.setDaemon(true);
//        thread.start();
//
//        //  shortly block make sure the thread is started
//        TimeUnit.MILLISECONDS.sleep(2);
//        //  该方法中断线程后，Thread.interrupted()结果为true, 并同时擦除中断标记，之后如果不发生中断，判断结果均为false
//        thread.interrupt();

        System.out.println(" =========================================================  ");

        // ①判断当前Main线程是否被中断  false
        System.out.println("Main thread is interrupted? " + Thread.interrupted());
        // ②中断当前Main线程
        Thread.currentThread().interrupt();
        // ③判断当前线程是否已经被中断 true
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted() + "\n");

        //  这里中断标记被擦除了，第一次调用会被判断为true
        System.out.println("Main thread is interrupted? " + Thread.interrupted());
        //  第二次判断为false
        System.out.println("Main thread is interrupted? " + Thread.interrupted() + "\n");


       System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            //④判断当前线程是否已经被中断,如果中断标记被擦除，这里只会进行sleep()操作,不会捕获中断信号
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
           //⑤捕获中断信号
            System.out.println("I will be interrupted still.");
        }

    }
}
