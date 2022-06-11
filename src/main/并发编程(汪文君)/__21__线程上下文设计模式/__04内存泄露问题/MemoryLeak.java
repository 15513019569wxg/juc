package __21__线程上下文设计模式.__04内存泄露问题;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @author wxg
 * @date 2022/1/12-15:09
 * @quotes 小不忍则乱大谋
 */
public class MemoryLeak {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal.set(new byte[1024*1024*100]);
        threadLocal.set(new byte[1024*1024*100]);
        // 最后存储的数据以最后一次存储的数据为主
        threadLocal.set(new byte[1024*1024*100]);

        threadLocal = null;
        currentThread().join(0);

    }


}
