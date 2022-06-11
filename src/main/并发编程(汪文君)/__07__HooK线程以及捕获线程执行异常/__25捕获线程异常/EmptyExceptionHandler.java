package __07__HooK线程以及捕获线程执行异常.__25捕获线程异常;

import java.util.concurrent.TimeUnit;

/**
 * The type Empty exception handler.
 *
 * @author wxg
 * @date 2022 /1/3-17:04
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class EmptyExceptionHandler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //  get current thread's thread group
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  here will throw unchecked exception
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
}
