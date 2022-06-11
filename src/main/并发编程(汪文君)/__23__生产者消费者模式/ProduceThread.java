package __23__生产者消费者模式;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author wxg
 * @date 2022/1/13-17:20
 * @quotes 小不忍则乱大谋
 */
public class ProduceThread extends Thread {

    private final static AtomicInteger COUNTER = new AtomicInteger(0);
    private final static Random RANDOM = new Random(System.currentTimeMillis());
    private final MessageQueue messageQueue;

    public ProduceThread(MessageQueue messageQueue, int seq) {
        super("PRODUCER-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message-" + COUNTER.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
                sleep(RANDOM.nextInt(1000));
            } catch (Exception e) {
                break;
            }
        }
    }
}
