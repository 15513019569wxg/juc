package __02__深入理解Thread构造函数.__04Thread构造函数.__02ThreadGroup;

/**
 * The type Thread construction.
 *
 * @author wxg
 * @date 2021 /12/31-17:56
 * @quotes 小不忍则乱大谋
 */
public class ThreadConstruction {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final Thread t1 = new Thread("t1");
        //  构建一个线程组testGroup
        ThreadGroup testGroup = new ThreadGroup("TestGroup");
        //  把t2线程放入testGroup线程组中
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") Thread t2 = new Thread(testGroup, "t2");
        //  获取的是main的线程组
        final ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        //  查看main()的线程组的名字
        System.out.println("Main thread belong group: " + mainThreadGroup.getName());
        //  查看t1线程的线程组的名字
        System.out.println("t1 thread belong to the group：" + t1.getThreadGroup().getName());
        //  查看t2线程的线程组的名字
        System.out.println("t2 thread belong to the group：" + t2.getThreadGroup().getName());

        System.out.println("t1 and main belong the same group: " + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 and main not belong the same group: " + (mainThreadGroup == t2.getThreadGroup()));

    }
}
