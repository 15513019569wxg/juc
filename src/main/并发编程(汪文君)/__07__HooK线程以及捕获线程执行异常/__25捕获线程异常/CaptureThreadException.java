package __07__HooK线程以及捕获线程执行异常.__25捕获线程异常;

import java.util.concurrent.TimeUnit;

/**
 * The type Capture thread exception.
 *
 * @author wxg
 * @date 2022 /1/3-16:51
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class CaptureThreadException {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //  ①设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e) ->{
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //  ② 这里会出现unchecked异常
            System.out.println(1 / 0);
        }, "Test-Thread");

        thread.start();
    }
}
