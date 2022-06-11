package __05__线程通信.__13多线程间通信;



import java.util.LinkedList;
import static java.lang.Thread.currentThread;

/**
 * The type Event queue.
 *
 * @author wxg
 * @date 2022 /1/2-16:27
 * @quotes 小不忍则乱大谋
 */
public class EventQueue {
    private final static int DEFAULT_MAX_EVENT = 10;
    private final int max;
    private final LinkedList<Event> eventQueue = new LinkedList<>();

    /**
     * The type Event.
     */
    static class Event {}

    /**
     * Instantiates a new Event queue.
     */
    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    /**
     * Instantiates a new Event queue.
     *
     * @param max the max
     */
    public EventQueue(int max) {
        this.max = max;
    }

    /**
     * 客户端提交 Event 到 eventQueue 队列中
     *
     * @param event 事件
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            //  如果事件队列满了
            while(eventQueue.size() >= max) {
                try {
                    console(" the queue is full. ");
                    //  提交线程陷入阻塞
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            //  唤醒其他提交线程
            eventQueue.notifyAll();
        }
    }

    /**
     * 服务端从 eventQueue 队列中获取 Event
     */
    public void take() {
        synchronized (eventQueue) {
            //  如果事件队列为空
            while(eventQueue.isEmpty()) {
                try {
                    console(" the queue is empty.");
                    //  工作线程陷入阻塞
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            //  唤醒某个工作线程
            eventQueue.notifyAll();
            console(" the event " + event + " is handed.");
        }
    }

    private void console(String message) {
        System.out.printf("%s: %s\n", currentThread().getName(), message);
    }
}
