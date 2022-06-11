package __03__ThreadApi的详细介绍.__07关闭线程.正常关闭;

import java.util.concurrent.TimeUnit;

/**
 * The type Interrupt thread exit.
 *
 * @author wxg
 * @date 2022 /1/1-16:08
 * @quotes 小不忍则乱大谋
 */
public class InterruptThreadExit {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread t = new Thread(() -> {
            System.out.println("I will start work");
            //  第一种退出方式
//                while (!isInterrupted()) {
//                    // working.
//                }

            //  第二种退出方式
            for(; ;){
                //  working
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("I will be exiting.");
        });
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown");
        t.interrupt();
    }
}
