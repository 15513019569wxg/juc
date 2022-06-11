package __20__Guarded_Suspension设计模式.请求队列;

import java.util.LinkedList;

/**
 * @author wxg
 * @date 2022/1/11-11:33
 * @quotes 小不忍则乱大谋
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized(queue){
            while(queue.size() == 0){
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                   return null;
                }
            }

            return queue.removeFirst();
        }
    }

    public void putRequest(Request request){
        synchronized(queue){
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
