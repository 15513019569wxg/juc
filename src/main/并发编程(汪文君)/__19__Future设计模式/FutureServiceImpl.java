package __19__Future设计模式;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FutureServiceImpl的主要作用在于当提交任务时创建一个新的线程来受理该任务, 进而达到任务异步执行的效果
 * @author wxg
 * @date 2022/1/10-14:23
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {
    /**
     * 为执行的线程指定名字前缀
     */
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    /**
     * 获取线程的名字
     * @return 线程名字
     */
    private String getName(){
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureImpl<Void> futureImpl = new FutureImpl<>();
        new Thread(() -> {
            runnable.run();
            // 任务执行结束之后, 将null作为结果传输给future
            futureImpl.finish(null);
        },getName()).start();
        return futureImpl;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureImpl<OUT> futureImpl = new FutureImpl<>();
        new Thread(() -> {
            final OUT result = task.get(input);
            // 任务执行结束之后,将真实的结果通过finish()传输给future
            futureImpl.finish(result);
        },getName()).start();
        return futureImpl;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureImpl<OUT> futureImpl = new FutureImpl<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束之后,将真实的结果通过finish()传输给future
            futureImpl.finish(result);
            // 执行回调接口,不在需要任务提交者主动询问
            if(null != callback){
                callback.call(result);
            }
        },getName()).start();
        return futureImpl;
    }
}
