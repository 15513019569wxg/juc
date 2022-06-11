package __05__线程通信.__16显示锁Lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * The type Boolean lock.
 *
 * @author wxg
 * @date 2022 /1/3-1:25
 * @quotes 小不忍则乱大谋
 */
public class BooleanLock implements MyLock {
    private final Collection<Thread> blockedThreadCollection = new ArrayList<>();
    /**
     * If the value is false, it means that  no other threads are holding the lock and the thread can obtain the lock;
     * <p>
     * If the value is true, it means that other threads are holding the lock and the thread cannot obtain the lock;
     */
    private boolean initValue;
    /**
     * 记录此时的当前线程
     */
    private Thread currentThread;

    /**
     * Instantiates a new Boolean lock.
     */
    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            // If cannot obtain the lock, the current thread is out of the blocking state, joins the blocking queue;
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        //   If the blocked thread got the lock, then deleted from the blocking queue;
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        //  记录获得锁的线程
        this.currentThread = Thread.currentThread();
    }

    /**
     * 对于正在阻塞的线程，如果超时还未抢到锁（打断该阻塞线程时不行的，因为抢到线程的锁还未释放锁），就报超时异常。
     * <p>
     * 该方法执行完,synchronize这个锁就消失了，其他线程就可以开始抢锁
     * @param mills 超时限制
     * @throws InterruptedException 中断机制
     * @throws TimeOutException     超时异常
     */
    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException { // ① 这里会发生阻塞，阻塞状态是否结束显示取决于lock()有没有执行完
        if (mills <= 0) {
            lock();
        }
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        /*
            一旦其他线程获得了锁，该线程就会处于阻塞状态，一旦处于阻塞状态线程就要立刻判断自己还可以允许阻塞多长时间。
         另外，intValue的变化由unlock()决定，即其他线程能否是否在这里阻塞取决与当前线程有没有调用unlock()
         */
        while (initValue) {
            if (hasRemaining <= 0) {
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            // ② 线程在这里也会发生阻塞，阻塞状态取决与unlock()是否执行完
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " -----> " + hasRemaining);
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();

    }

    /**
     * 该方法只能由获得锁的线程释放该锁，其他线程不允许调用该方法
     */
    @Override
    public synchronized void unlock() {
        // 这个currentThread 是指 调用unlock()的线程（T1, T2, T3, T4, 不可以是main）
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " release the lock monitor").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
