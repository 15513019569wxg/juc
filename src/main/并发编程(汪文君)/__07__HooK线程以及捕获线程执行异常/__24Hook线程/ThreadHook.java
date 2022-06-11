package __07__HooK线程以及捕获线程执行异常.__24Hook线程;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread hook.
 *
 * @author wxg
 * @date 2022 /1/3-17:14
 * @quotes 小不忍则乱大谋
 */
public class ThreadHook {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //  为应用程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("The hook thread 1 is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 1 will exit.");
        }));


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("The hook thread 2 is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 2 will exit.");
        }));

        System.out.println("The program will stopping");
    }
}
