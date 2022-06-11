package __23__生产者消费者模式;

import java.util.LinkedList;

/**
 * @author wxg
 * @date 2022/1/13-17:04
 * @quotes 小不忍则乱大谋
 */
public class MessageQueue {
    /**
     * 消息长度
     */
    private final LinkedList<Message> queue;

    /**
     * 队列最大长度
     */
    private final static int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue(){
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit){
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    /**
     * 从队列中取出数据
     * @param message 消息
     * @throws InterruptedException 中断异常
     */
    public void put(Message message) throws InterruptedException {
      synchronized(queue){
          while(queue.size() > limit){
              queue.wait();
          }
          queue.addLast(message);
          queue.notifyAll();
      }
    }

   public Message take() throws InterruptedException {
        synchronized(queue){
            while(queue.isEmpty()){
                queue.wait();
            }
            final Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
   }

   public int getMaxLimit() {
        return limit;
   }

    /**
     * 获取队列中元素的个数
     * @return 队列中元素的个数
     */
   public int getMessageSize(){
        synchronized(queue){
            return queue.size();
        }
   }
}
