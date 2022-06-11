package __03__ThreadApi的详细介绍.__06ThreadApi理解.__02sleep方法;

/**
 * The type Thread sleep.
 *
 * @author wxg
 * @date 2022 /1/1-9:49
 * @quotes 小不忍则乱大谋
 */
public class ThreadSleep {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread(() -> {
            final long startTime = System.currentTimeMillis();
            sleep(2_000L);
            final long endTime = System.currentTimeMillis();
            System.out.printf("Total spend %d ms %n", endTime - startTime);

        }).start();

        final long startTime = System.currentTimeMillis();
        sleep(3_000L);
        final long endTime = System.currentTimeMillis();
        System.out.printf("Main's Total spend %d ms %n", endTime - startTime);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
