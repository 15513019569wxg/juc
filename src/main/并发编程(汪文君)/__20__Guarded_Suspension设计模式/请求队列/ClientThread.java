package __20__Guarded_Suspension设计模式.请求队列;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author wxg
 * @date 2022/1/11-11:37
 * @quotes 小不忍则乱大谋
 */
public class ClientThread extends Thread{
    private final RequestQueue queue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue){
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        IntStream.range(0, 10).forEachOrdered(i -> {
            System.out.println("Client -> request " + sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
