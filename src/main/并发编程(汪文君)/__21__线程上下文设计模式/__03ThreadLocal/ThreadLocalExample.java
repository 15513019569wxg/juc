package __21__线程上下文设计模式.__03ThreadLocal;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author wxg
 * @date 2022/1/12-12:59
 * @quotes 小不忍则乱大谋
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        // 创建ThreadLocal实例
        ThreadLocal<Integer> tlocal = new ThreadLocal<>();
        // 创建十个线程,使用tlocal
        IntStream.range(0,10).forEach(i -> new Thread(
                () -> {
                    // 每个线程都会设置tlocal, 但是彼此之间的数据是独立的
                    tlocal.set(i);
                    System.out.println(currentThread().getName() + " set i " + " = "  + tlocal.get());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currentThread().getName() + " get i " + " = " + tlocal.get());
                }
        ).start());

    }

}
