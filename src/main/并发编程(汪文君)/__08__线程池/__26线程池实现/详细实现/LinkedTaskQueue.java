package __08__线程池.__26线程池实现.详细实现;

import __08__线程池.__26线程池实现.接口和基本类.DenyPolicy;
import __08__线程池.__26线程池实现.接口和基本类.TaskQueue;
import __08__线程池.__26线程池实现.接口和基本类.ThreadPool;
import java.util.LinkedList;

/**
 * @author wxg
 * @date 2022/1/4-19:18
 * @quotes 小不忍则乱大谋
 */
public class LinkedTaskQueue implements TaskQueue {

    /**
     * 任务队列的最大容量，在构造时传入
     */
    private final int limit;

    /**
     * 若任务队列中的任务已经满了， 则需要执行拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列（客户端和服务端都使用，属于共享资源）
     */
    private final LinkedList<Runnable> taskList = new LinkedList<>();

    /**
     * 线程池
     */
    private final ThreadPool threadPool;

    public LinkedTaskQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable task) {
        synchronized(LinkedTaskQueue.class){
            if(taskList.size() >= limit){
                // 无法容纳新的任务及时执行拒绝策略
                denyPolicy.reject(task, threadPool);
            }else{
                // 将任务加入到队尾，并且唤醒阻塞中的线程
                taskList.addLast(task);
                LinkedTaskQueue.class.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized(LinkedTaskQueue.class){
            while(taskList.isEmpty()){
                // 如果任务队列中没有可执行任务，则当前线程将会挂起，进入taskList关联的monitor waitset中等待唤醒（新的任务加入）
                taskList.wait();
            }
            // 从任务队列头部移除一个任务
            return taskList.removeFirst();
        }
    }

    @Override
    public int size() {
        // 返回当前任务队列中的任务数
        return taskList.size();
    }
}
