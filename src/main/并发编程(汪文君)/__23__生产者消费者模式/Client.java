package __23__生产者消费者模式;

import java.util.stream.IntStream;

/**
 * @author wxg
 * @date 2022/1/13-17:39
 * @quotes 小不忍则乱大谋
 */
public class Client {
    public static MessageQueue MESSAGE_QUEUE = new MessageQueue();

    public static void main(String[] args) {
        IntStream.range(1,5).forEach(i -> new ProduceThread(MESSAGE_QUEUE, 1).start());
        IntStream.range(1,10).forEach(i -> new ConsumerThread(MESSAGE_QUEUE, 1).start());
    }

}
