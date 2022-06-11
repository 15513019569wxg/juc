package __05__线程通信.__13多线程间通信;

import java.util.stream.Stream;

/**
 * The type Producer consumer demo 4.
 *
 * @author wxg
 * @date 2022 /1/2-17:49
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ProducerConsumerDemo4 {
    private int number = 0;
    private volatile boolean isProduced = false;

    /**
     * 多线程通信出现了问题, 所有线程都放弃了CPU的执行权，线程陷入了假死状态
     * @param args null
     */
    public static void main(String[] args) {
        final ProducerConsumerDemo4 producerConsumerDemo3 = new ProducerConsumerDemo4();
        Stream.of("P-1", "P-2", "P-3", "P-4").forEach(i ->
                new Thread(() -> {
                    while (true) {
                        producerConsumerDemo3.produce();
                    }
                }, i).start()
        );

        Stream.of("C-1", "C-2", "C-3", "C-4", "C-5", "C-6").forEach(i ->
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
        synchronized (ProducerConsumerDemo4.class) {
            //  除非isProduced = false, 否则必须不同的判断
            while(isProduced) {
            //if(isProduced) {
                try {
                    /*
                        生产者在这里阻塞，也在这里被唤醒（被唤醒时isProduced=true），唤醒之后继续往下执行。所以, 必须确保接下来的number++操作满足
                    isProduced = false。如果将while改为if, 会导致被唤醒的线程继续往下执行，借助被唤醒时isProduced=true，再次产生相同的number值，
                    被消费者再次消费到,即有可能出现“消费一次，生产多次或者生产一次，消费多次”的现象。
                     */
                    ProducerConsumerDemo4.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(" p: " + Thread.currentThread().getName() + " -- " + number);
            //  改为notifyAll()
            ProducerConsumerDemo4.class.notifyAll();
            isProduced = true;
        }

    }

    /**
     * Consumer.
     */
    public void consumer() {
        synchronized (ProducerConsumerDemo4.class) {
            while(!isProduced) {
            //if(!isProduced) {
                try {
                    ProducerConsumerDemo4.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C: " + Thread.currentThread().getName() + " -- " + number);
            //  改为notifyAll()
            ProducerConsumerDemo4.class.notifyAll();
            isProduced = false;

        }
    }

}
