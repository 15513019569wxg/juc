package __06__ThreadGroup详细介绍.__22线程组销毁;

/**
 * The type Thread group destroy.
 *
 * @author wxg
 * @date 2022 /1/4-12:03
 * @quotes 小不忍则乱大谋
 */
public class ThreadGroupDestroy {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();

        group.destroy();

        System.out.println("group.isDestroyed=" + group.isDestroyed());
        mainGroup.list();

    }

}
