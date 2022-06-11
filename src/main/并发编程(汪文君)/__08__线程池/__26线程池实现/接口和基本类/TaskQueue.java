package __08__线程池.__26线程池实现.接口和基本类;

/**
 * @author wxg
 * @date 2022/1/4-18:31
 * @quotes 小不忍则乱大谋
 */
public interface TaskQueue {
    /**
     * 当有新的任务进来时首先会offer到队列中
     * @param task 任务
     */
    void offer(Runnable task);


    /**
     * 工作线程通过take方法获取task
     * @return 任务
     * @throws InterruptedException 中断异常
     */
    Runnable take() throws InterruptedException;

    /**
     * 获取任务队列中任务的数量
     * @return 任务数量
     */
    int size();

}
