package __08__线程池.__26线程池实现.接口和基本类;


/**
 * @author wxg
 * @date 2022/1/4-18:39
 * @quotes 小不忍则乱大谋
 */
@FunctionalInterface
public interface DenyPolicy {
    /**
     * 拒绝策略
     * @param task 任务
     * @param threadPool 线程池
     */
    void reject(Runnable task, ThreadPool threadPool);


    class DiscardDenyPolicy implements DenyPolicy{
        /**
         * 该拒绝策略会直接将任务丢掉
         * @param task 任务
         * @param threadPool 线程池
         */
        @Override
        public void reject(Runnable task, ThreadPool threadPool) {
            // ignore
        }
    }

    class AbortDenyPolicy implements DenyPolicy{
        /**
         * 直接向任务提交者抛出异常
         * @param task 任务
         * @param threadPool 线程池
         */
        @Override
        public void reject(Runnable task, ThreadPool threadPool) {
            throw new TaskDenyException("The task " + task + " will be abort.");
        }

    }

    class TaskQueueDenyPolicy implements DenyPolicy{
        /**
         * 该拒绝策略会使任务在提交者提交的线程中执行任务
         * @param task 任务
         * @param threadPool 线程池
         */
        @Override
        public void reject(Runnable task, ThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                task.run();
            }
        }
    }
}
