package __20__Guarded_Suspension设计模式.请求队列;

import java.util.Random;

/**
 * @author wxg
 * @date 2022/1/11-11:43
 * @quotes 小不忍则乱大谋
 */
public class ServerThread extends Thread{
    private final RequestQueue queue;

    private final Random random;

    private volatile boolean flag = false;

    public ServerThread(RequestQueue queue){
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        System.out.println("======== I am killed when executing the run() =========");
        while(!flag){
            Request request = queue.getRequest();
            if(null == request){
                System.out.println("========= Received the empty request ===========");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                //noinspection BusyWait
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                System.out.println("========= sleep is interrupted, I am killed by return========");
                return;
            }
        }
    }

    public void close() {
        this.flag = true;
        this.interrupt();
    }
}
