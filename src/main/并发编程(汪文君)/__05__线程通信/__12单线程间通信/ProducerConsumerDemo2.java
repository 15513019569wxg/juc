package __05__线程通信.__12单线程间通信;

import java.util.stream.Stream;

/**
 * The type Producer consumer demo 2.
 *
 * @author wxg
 * @date 2022 /1/2-17:49
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ProducerConsumerDemo2 {
    private int number = 0;
    private volatile boolean isProduced = false;

    /**
     * 多线程通信出现了问题, 所有线程都放弃了CPU的执行权，线程陷入了假死状态
     *
     * @param args null
     */
    public static void main(String[] args) {
        final ProducerConsumerDemo2 producerConsumerDemo2 = new ProducerConsumerDemo2();
        Stream.of("P-1", "P-2").forEach(i ->
                new Thread(() -> {
                    while (true) {
                        producerConsumerDemo2.produce();
                    }
                }, i).start()
        );

        Stream.of("C-1", "C-2").forEach(i ->
                new Thread(() -> {
                    while (true) {
                        producerConsumerDemo2.consumer();
                    }
                }, i).start()
        );

    }

    /**
     * Produce.
     */
    public void produce() {
        synchronized (ProducerConsumerDemo2.class) {
            if (isProduced) {
                try {
                    ProducerConsumerDemo2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                number++;
                System.out.println(" p: " + Thread.currentThread().getName() + " -- " + number);
                ProducerConsumerDemo2.class.notify();
                isProduced = true;
            }
        }

    }

    /**
     * Consumer.
     */
    public void consumer() {
        synchronized (ProducerConsumerDemo2.class) {
            if (isProduced) {
                System.out.println("C: " + Thread.currentThread().getName() + " -- " + number);
                /*
                    notify()如果唤醒己方（不是对方）会导致唤醒线程不当，使得所有线程都处于wait状态
                    原因如下：
                        因为notify每次只唤醒一个线程,因此并不确定他唤醒的是哪一个线程，所以消费1唤醒的是消费者2,此时刚好没有数据被生产,
                    消费者2也进入等待,并唤醒生产者2，生产者2生产完数据之后进入wait同时唤醒线程,此时唤醒的是生产者1 ,因为数据不为空,
                    因此两生产者都进入等待状态, 此时四个线程全部wait,即假死状态。
                 */
                ProducerConsumerDemo2.class.notify();
                isProduced = false;
            } else {
                try {
                    ProducerConsumerDemo2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
