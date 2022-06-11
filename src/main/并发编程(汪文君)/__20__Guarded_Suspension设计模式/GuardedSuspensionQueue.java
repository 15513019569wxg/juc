package __20__Guarded_Suspension设计模式;

import java.util.LinkedList;

/**
 * @author wxg
 * @date 2022/1/11-10:46
 * @quotes 小不忍则乱大谋
 */
public class GuardedSuspensionQueue {
    /**
     * 定义存放Integer类型的queue
     */
    private final LinkedList<Integer> queue = new LinkedList<>();

    /**
     * 定义queue的最大容量为100
     */
    private final int LIMIT = 100;

    /**
     * 往queue中插入数据, 如果queue中的元素超过了最大容量,则会陷入阻塞
     * @param data 任务
     */
    public void offer(Integer data){
        synchronized (this){
            while(queue.size() >= LIMIT){
                // 挂起当前线程, 使其陷入阻塞
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
                // 插入元素并且唤醒take线程
                queue.addLast(data);
                this.notifyAll();
            }
        }
    }

    public Integer take(){
        synchronized(this){
            // 判断如果列队为空
            try {
                // 则挂起当前线程
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
