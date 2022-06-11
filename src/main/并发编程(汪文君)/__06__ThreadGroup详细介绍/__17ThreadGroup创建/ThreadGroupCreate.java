package __06__ThreadGroup详细介绍.__17ThreadGroup创建;


/**
 * The type Thread group create.
 *
 * @author wxg
 * @date 2022 /1/3-20:56
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupCreate {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //  use the name
        final ThreadGroup tg1 = new ThreadGroup("TG1");
        // TG1
        System.out.println(tg1.getName());
        System.out.println(tg1.getParent().getName());
        // main
       ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        //  获取tg1的父线程
        ThreadGroup tg1Parent = tg1.getParent();
        // true
        System.out.println(tg1Parent.equals(mainGroup));


        final Thread thread = new Thread(tg1, "thread") {
            @Override
            public void run() {
                if(true) {
                    try {
                        System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent().getName());
                        System.out.println(getThreadGroup().getParent().activeCount());
                        Thread.sleep(10_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        System.out.println(" ========================================================= ");

        final ThreadGroup tg2 = new ThreadGroup("TG2");
        final Thread thread2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                System.out.println(tg1.getName());
                final int number = tg1.activeCount();
                System.out.println(tg2.getName());
                System.out.println(tg2.getParent().getName());
                final Thread[] threads = new Thread[number];
                tg1.enumerate(threads);
                System.out.println(" ---------------------------------------------------------------- ");
                for (Thread thread : threads) {
                    System.out.println(thread);
                }
            }
        };
        thread2.start();


        try {
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" ************************************************************************************ ");

        // use the parent and group name
        final ThreadGroup tg3 = new ThreadGroup(tg1, "TG3");
        System.out.println(tg3.getName());
        System.out.println(tg3.getParent().getName());
    }
}
