package __23__生产者消费者模式;

/**
 * @author wxg
 * @date 2022/1/13-17:03
 * @quotes 小不忍则乱大谋
 */
public class Message {
    private final String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
