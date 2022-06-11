package __03__ThreadApi的详细介绍.__06ThreadApi理解.__01优先级;

/**
 * The type Thread priority.
 *
 * @author wxg
 * @date 2022 /1/1-9:14
 * @quotes 小不忍则乱大谋
 */
public class ThreadPriority {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread t = new Thread(() -> {
            while (true) {
                System.out.println("t");
            }
        }
        );
        t.setPriority(3);

        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("t2");
            }
        }
        );
        t.setPriority(10);

        //t.start();
        //t2.start();

        System.out.println("------------------------------------------------------------------------------------------");


        //  定义一个线程组
        final ThreadGroup group = new ThreadGroup("test");
        //  将线程组的优先级指定为7
        group.setMaxPriority(7);
        // 定义一个线程，将其加入到group中
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread thread = new Thread(group, "test-thread");
        //  企图将线程的优先级设定为10
        thread.setPriority(10);
        // 企图失败
        System.out.println(thread.getPriority());


        System.out.println(" =================================================================");


        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread thread1 = new Thread();
        System.out.println(" thread1 priority " + thread1.getPriority());
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread thread2 = new Thread(() -> {
            final Thread thread3 = new Thread();
            System.out.println(" thread3 priority " + thread3.getPriority());
        });

        thread2.setPriority(6);
        thread2.start();
        System.out.println(" thread2 priority " + thread2.getPriority());


    }
}
