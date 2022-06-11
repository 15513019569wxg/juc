package __08__线程池.__26线程池实现.接口和基本类;

/**
 * @author wxg
 * @date 2022/1/4-18:22
 * @quotes 小不忍则乱大谋
 */
public interface ThreadPool {
    /**
     * 提交任务到线程池，供客户端调用
     * @param task 任务
     */
    void execute(Runnable task);

    /**
     * 关闭线程
     */
    void shutdown();

    /**
     * 获取线程池的初始化大小
     * @return 线程数量
     */
    int getInitSize();
    /**
     * 获取线程池最大的线程个数
     * @return 最大线程个数
     */
    int getMaxSize();

    /**
     * 获取线程池的核心线程数量
     * @return 核心线程个数
     */
    int getCoreSize();

    /**
     * 获取线程池中用于缓存任务队列的大小
     * @return 任务数量
     */
    int getQueueSize();

    /**
     * 获取线程池中活跃线程的数量
     * @return 活跃线程数量
     */
    int getActiveCount();

    /**
     * 查看线程池是否已经被shutdown
     * @return boolean
     */
    boolean isShutdown();
}
