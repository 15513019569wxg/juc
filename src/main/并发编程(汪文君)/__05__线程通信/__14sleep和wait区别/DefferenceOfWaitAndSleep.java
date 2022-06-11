package __05__线程通信.__14sleep和wait区别;

import java.util.stream.Stream;

/**
 * The type Defference of wait and sleep.
 *
 * @author wxg
 * @date 2022 /1/2-21:24
 * @quotes 小不忍则乱大谋
 */
public class DefferenceOfWaitAndSleep {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Stream.of("sleep-1", "sleep-2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m1();
                    }
                }.start()
        );

        Stream.of("wait-1", "wait-2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m2();
                    }
                }.start()
        );
    }

    /**
     * M 1.
     */
    public static void m1() {
        synchronized (DefferenceOfWaitAndSleep.class) {
            System.out.println(" The Thread " + Thread.currentThread().getName() + " enter. ");
            try {
                Thread.sleep(20_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * M 2.
     */
    public static void m2() {
        synchronized (DefferenceOfWaitAndSleep.class) {
            System.out.println(" The Thread " + Thread.currentThread().getName() + " enter. ");
            try {
                DefferenceOfWaitAndSleep.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
