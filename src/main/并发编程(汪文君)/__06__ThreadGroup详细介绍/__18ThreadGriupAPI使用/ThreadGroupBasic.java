package __06__ThreadGroup详细介绍.__18ThreadGriupAPI使用;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread group basic.
 *
 * @author wxg
 * @date 2022 /1/4-11:25
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupBasic {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("Group1");
        Thread thread = new Thread(group, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread");
        thread.setDaemon(true);
        thread.start();

        // make sure the thread is started
        TimeUnit.MILLISECONDS.sleep(1);

        final ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println("activeCount=" + mainGroup.activeCount());
        System.out.println("activeGroupCount=" + mainGroup.activeGroupCount());
        System.out.println("getMaxPriority=" + mainGroup.getMaxPriority());
        System.out.println("getName=" + mainGroup.getName());
        //getParent=java.lang.ThreadGroup[name=system,maxpri=10]
        System.out.println("getParent=" + mainGroup.getParent());
        mainGroup.list();

        System.out.println(" ========================😊😊😊 ===========================");

        System.out.println("ParentOf=" + mainGroup.parentOf(group));
        System.out.println("ParentOf=" + mainGroup.parentOf(mainGroup));

    }
}
