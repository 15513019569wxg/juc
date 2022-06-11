package __06__ThreadGroup详细介绍.__19活跃线程和线程组;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * The type Thread group enumerate threads.
 *
 * @author wxg
 * @date 2022 /1/4-10:45
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupEnumerateThreads {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        //  创建一个ThreadGroup
        final ThreadGroup myGroup = new ThreadGroup("MyGroup");
        //  创建线程传入ThreadGroup
        Thread thread = new Thread(myGroup, () -> {
            while(true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "MyThread");
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        // 父线程
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        // 创建一个普通的线程数组
        Thread[] list = new Thread[5];

        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);
        Arrays.asList(list).forEach(System.out::println);

        System.out.println(" ============================================ ");

        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
        Arrays.asList(list).forEach(System.out::println);


    }
}
