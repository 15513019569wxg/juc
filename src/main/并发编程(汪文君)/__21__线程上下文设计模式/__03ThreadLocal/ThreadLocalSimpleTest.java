package __21__线程上下文设计模式.__03ThreadLocal;

/**
 * @author wxg
 * @date 2022/1/12-16:16
 * @quotes 小不忍则乱大谋
 */
public class ThreadLocalSimpleTest {
    private final static ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "Alex");

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        final String value = THREAD_LOCAL.get();
        System.out.println(value);
    }

}
