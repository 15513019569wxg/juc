package __24__latch设计模式;

import java.util.concurrent.TimeUnit;

/**
 * 无限等待 CountDownLatch实现
 *
 * @author wxg
 * @date 2022/1/14-13:31
 * @quotes 小不忍则乱大谋
 */
public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            // 当limit > 0, 当前线程进入阻塞状态
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void await(TimeUnit unit, long time) throws WaitTimeoutException, InterruptedException {
        if (time <= 0) {
            throw new IllegalArgumentException("The time is invalid...");
        }
        // 将time转移为纳秒
        long remainingNanos = unit.toNanos(time);
        // 等待任务将在endNanos后超时
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this) {
            while (limit > 0) {
                // 如果超时则抛出WaitTimeoutException异常
                if (TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0) {
                    throw new WaitTimeoutException("The wait time over specify time...");
                }
                // 等待remainingNanos, 在等待的过程中有可能会被中断,需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalArgumentException("all of task already arrived");
            }
            // 使limit减一, 并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        // 返回有多少个线程还未完成任务
        return limit;
    }


}
