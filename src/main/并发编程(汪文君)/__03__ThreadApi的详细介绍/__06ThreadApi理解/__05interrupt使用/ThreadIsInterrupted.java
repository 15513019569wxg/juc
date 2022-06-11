package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread is interrupted.
 *
 * @author wxg
 * @date 2022 /1/1-10:29
 * @quotes 小不忍则乱大谋
 */
public class ThreadIsInterrupted {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        Thread thread = new Thread(() -> {
            while (true) {
                // do nothing, just empty loop
            }
        });
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());


        System.out.println("========================  sleep捕获异常会清除中断标记 =============================");


        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        //  ignore the Exception
                        // here the interrupt flag will be clear, flag变为false
                        System.out.printf("I am be interrupted ? %s\n", this.isInterrupted());
                    }
                }
            }
        };
        thread2.setDaemon(true);
        thread2.start();

        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread2.isInterrupted());

        thread2.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread2.isInterrupted());

    }

}
