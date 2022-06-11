package __17__读写锁分离设计模式;

/**
 * @author wxg
 * @date 2022/1/9-16:46
 * @quotes 小不忍则乱大谋
 */
public interface Lock {
    /**
     * 获取显式锁, 没有获得锁的线程将被阻塞
     * @throws InterruptedException 中断异常
     */
    void lock() throws InterruptedException;

    /**
     * 释放锁
     */
    void unlock();
}
