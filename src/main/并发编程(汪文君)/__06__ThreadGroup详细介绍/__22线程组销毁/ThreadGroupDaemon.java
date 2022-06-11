package __06__ThreadGroup详细介绍.__22线程组销毁;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread group daemon.
 *
 * @author wxg
 * @date 2022 /1/4-12:11
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupDaemon {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("Group1");
        new Thread(group1, () -> {
            try {
                TimeUnit.SECONDS.sleep(50);
                System.out.println("group1-thread1 will be finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"group1-thread1").start();

        // destroy group1 when there are active threads in group1
        try {
            group1.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Determine whether thread group 1 is destroyed   ---> false
        System.out.println("group1.isDestroyed() " + group1.isDestroyed());

        ThreadGroup group2 = new ThreadGroup("Group2");
        new Thread(group2, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("group2-thread2 will be finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"group2-thread1").start();

        // 设置daemon为true, not need to destroy group2, group2 will be not exist when thread2 ends
        group2.setDaemon(true);

        TimeUnit.SECONDS.sleep(100L);

        // Determine whether thread group 2 is destroyed, group2 has disappeared
        System.out.println("group2.isDestroyed() "+ group2.isDestroyed());

        //Although thread1 ends, group1 still exists，need manually destroy group1
        group1.destroy();
        // true
        System.out.println("group1.isDestroyed() " + group1.isDestroyed());


        // Group1 and Group2 do not exist in mainGroup
        group1.getParent().list();

    }
}
