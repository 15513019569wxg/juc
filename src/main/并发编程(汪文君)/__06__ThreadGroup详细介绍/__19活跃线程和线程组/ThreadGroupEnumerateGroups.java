package __06__ThreadGroup详细介绍.__19活跃线程和线程组;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * The type Thread group enumerate groups.
 *
 * @author wxg
 * @date 2022 /1/4-11:15
 * @quotes 小不忍则乱大谋
 */
public class ThreadGroupEnumerateGroups {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ThreadGroup myGroup1 = new ThreadGroup("MyGroup1");
        new ThreadGroup(myGroup1, "MyGroup2");

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        final ThreadGroup[] list = new ThreadGroup[mainGroup.activeGroupCount()];

        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);
        Arrays.asList(list).forEach(System.out::println);

        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
    }
}
