package __08__线程池.__26线程池实现.接口和基本类;

/**
 * @author wxg
 * @date 2022/1/4-18:36
 * @quotes 小不忍则乱大谋
 */
@FunctionalInterface
public interface ThreadFactory {
    /**
     * 创建线程的工厂
     * @param task 任务
     * @return 线程
     */
    Thread createThread(Runnable task);
}
