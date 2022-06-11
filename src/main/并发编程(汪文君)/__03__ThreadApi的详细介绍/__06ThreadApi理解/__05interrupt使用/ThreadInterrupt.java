package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread interrupt.
 *
 * @author wxg
 * @date 2022 /1/1-10:17
 * @quotes 小不忍则乱大谋
 */
public class ThreadInterrupt {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh, i am be interrupted" );
            }
        });

        thread.start();

        //  short block and make sure thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();

        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println(thread.isInterrupted());
    }
}
