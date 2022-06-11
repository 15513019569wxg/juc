package __06__ThreadGroup详细介绍.__21线程组打断;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread group interrupt.
 *
 * @author wxg
 * @date 2022 /1/4-11:53
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupInterrupt {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group, () -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1_000L);
                } catch (InterruptedException e) {
                    // received interrupt SIGNAL and clear quickly
                    break;
                }
            }
            System.out.println(" thread will exit.");
        },"thread").start();


        new Thread(group, () -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1_000L);
                } catch (InterruptedException e) {
                    // received interrupt SIGNAL and clear quickly
                    break;
                }
            }
            System.out.println(" thread2 will exit.");
        },"thread2").start();

        // make sure all of above threads started
        TimeUnit.MILLISECONDS.sleep(2_000L);

        group.interrupt();
    }
}
