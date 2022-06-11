package __23__生产者消费者模式;

import java.util.Random;

/**
 * @author wxg
 * @date 2022/1/13-17:33
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("BusyWait")
public class ConsumerThread extends Thread{

    private final static Random RANDOM = new Random(System.currentTimeMillis());
    private final MessageQueue messageQueue;

    public  ConsumerThread(MessageQueue messageQueue, int seq) {
        super("CONSUMER-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " get a message " + message.getData());
                sleep(RANDOM.nextInt(100));
            } catch (Exception e) {
                break;
            }
        }
    }
}
