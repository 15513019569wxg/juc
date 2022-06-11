package __06__ThreadGroup详细介绍.__20线程组优先级;

import java.util.concurrent.TimeUnit;

/**
 * The type Thread group priority.
 *
 * @author wxg
 * @date 2022 /1/4-11:43
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ThreadGroupPriority {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
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

        System.out.println("group.getMaxPriority()=" + group.getMaxPriority());
        System.out.println("thread.getMaxPriority()=" + thread.getPriority());

        //①改变group的最大优先级
        group.setMaxPriority(3);

        System.out.println(" ====================================================");

        System.out.println("group.getMaxPriority()=" + group.getMaxPriority());
        System.out.println("thread.getMaxPriority()=" + thread.getPriority());
    }
}
