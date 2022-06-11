package __24__latch设计模式;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/14-13:16
 * @quotes 小不忍则乱大谋
 */
public abstract class Latch {
    /**
     * 用于控制多少个线程完成任务时才能打开阀门
     */
    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    /**
     * 该方法会使得当前线程一直等待, 直到所有线程都完成工作, 被阻塞的线程是允许被中断的
     * @throws InterruptedException 中断异常
     */
    public abstract void await() throws InterruptedException;

    /**
     * 当任务完成工作之后调用该方法使得计数器减一
     */
    public abstract void countDown();

    /**
     * 获取当前还有多少个线程没有完成任务
     * @return 尚未完成任务的线程数量
     */
    public abstract int getUnarrived();

    /**
     * 超时自动关闭
     * @param unit 超时时间
     * @param time 时间单位
     * @throws InterruptedException 中断异常
     * @throws WaitTimeoutException 超时异常
     */
    public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException;
}
