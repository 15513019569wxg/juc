package __05__线程通信.__16显示锁Lock;

import java.util.Collection;

/**
 * The interface My lock.
 *
 * @author wxg
 * @date 2022 /1/3-1:20
 * @quotes 小不忍则乱大谋
 */
public interface MyLock {
    /**
     * 定义一个允许中断的lock()
     *
     * @throws InterruptedException 中断异常
     */
    void lock() throws InterruptedException;

    /**
     * 定义一个超时异常
     *
     * @param mills 超时限制
     * @throws InterruptedException 中断异常
     * @throws TimeOutException     超时异常
     */
    void lock(long mills) throws InterruptedException, TimeOutException;

    /**
     * 释放锁
     *
     * @throws InterruptedException 中断异常
     */
    void unlock() throws InterruptedException;

    /**
     * 返回正在运行的线程集合
     *
     * @return 线程集合 blocked thread
     */
    Collection<Thread> getBlockedThread();

    /**
     * 查看正在运行的线程个数
     *
     * @return 正在运行的线程个数 blocked size
     */
    int getBlockedSize();

    /**
     * The type Time out exception.
     */
    class TimeOutException extends Exception {
        /**
         * Instantiates a new Time out exception.
         *
         * @param message the message
         */
        public TimeOutException(String message) {
            super(message);
        }
    }
}
