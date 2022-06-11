package __05__线程通信.__13多线程间通信;

import java.util.concurrent.TimeUnit;

/**
 * The type Event client.
 *
 * @author wxg
 * @date 2022 /1/2-16:50
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class EventClient {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        // 生产者
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();
        // 消费者
        new Thread(() -> {
            for (; ; ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Comsumer").start();
    }
}
