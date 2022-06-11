package __02__深入理解Thread构造函数.__04Thread构造函数.__03StackSize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Thread counter.
 *
 * @author wxg
 * @date 2021 /12/31-19:57
 * @quotes 小不忍则乱大谋
 */
public class ThreadCounter extends Thread{
    /**
     * The constant COUNTER.
     */
    final static AtomicInteger COUNTER = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println(" The " + COUNTER.getAndIncrement() + "thread be created." );
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            //noinspection InfiniteLoopStatement
            while(true) {
                new ThreadCounter().start();
            }
        } catch (Exception e) {
            System.out.println(" failed At => " + COUNTER.get());
        }
    }


}
