package __05__线程通信.__13多线程间通信;

import java.util.stream.Stream;

/**
 * The type Producer consumer demo 3.
 *
 * @author wxg
 * @date 2022 /1/2-17:49
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ProducerConsumerDemo3 {
    private int number = 0;
    private volatile boolean isProduced = false;

    /**
     * 多线程通信出现了问题, 所有线程都放弃了CPU的执行权，线程陷入了假死状态
     * @param args null
     */
    public static void main(String[] args) {
        final ProducerConsumerDemo3 producerConsumerDemo3 = new ProducerConsumerDemo3();
        Stream.of("P-1", "P-2","P-3","P-4").forEach(i ->
                new Thread(() -> {
                    while (true) {
                        producerConsumerDemo3.produce();
                    }
                }, i).start()
        );

        Stream.of("C-1", "C-2","C-3","C-4","C-5","C-6").forEach(i ->
                new Thread(() -> {
                    while (true) {
                        producerConsumerDemo3.consumer();
                    }
                }, i).start()
        );

    }

    /**
     * Produce.
     */
    public void produce() {
        synchronized (ProducerConsumerDemo3.class) {
            if (isProduced) {
                try {
                    ProducerConsumerDemo3.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                number++;
                System.out.println(" p: " + Thread.currentThread().getName() + " -- " + number);
                //  改为notifyAll()
                ProducerConsumerDemo3.class.notifyAll();
                isProduced = true;
            }
        }

    }

    /**
     * Consumer.
     */
    public void consumer() {
        synchronized (ProducerConsumerDemo3.class) {
            if (isProduced) {
                System.out.println("C: " + Thread.currentThread().getName() + " -- " + number);
                //  改为notifyAll()
                ProducerConsumerDemo3.class.notifyAll();
                isProduced = false;
            } else {
                try {
                    ProducerConsumerDemo3.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
