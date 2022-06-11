package __15__监控任务的生命周期;

/**
 * @author wxg
 * @date 2022/1/8-23:46
 * @quotes 小不忍则乱大谋
 */
public interface TaskLifecycle<T> {
    /**
     * 任务启动时会触发onStart方法
     * @param thread 传入线程
     */
    void onStart(Thread thread);

    /**
     * 任务正在运行时会触发onRunning方法
     * @param thread 传入线程
     */
    void onRunning(Thread thread);

    /**
     * 任务运行时会触发onFinish方法，其中result是任务执行结束后的结果
     * @param thread 传入线程
     * @param result 运行结果
     */
    void onFinish(Thread thread, T result);

    /**
     * 任务执行时会触发onError方法
     * @param thread 传入线程
     * @param e 异常
     */
    void onError(Thread thread, Exception e);


    /**
     * 生命周期接口的空实现（Adapter）
     * @param <T>  泛型类型
     */
    class EmptyLifecycle<T> implements TaskLifecycle<T>{
        @Override
        public void onStart(Thread thread) {
            // do nothing
        }

        @Override
        public void onRunning(Thread thread) {
            // do nothing
        }

        @Override
        public void onFinish(Thread thread, T result) {
            // do nothing
        }

        @Override
        public void onError(Thread thread, Exception e) {
            // do nothing
        }
    }
}
