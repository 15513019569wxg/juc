package __08__线程池.__26线程池实现.详细实现;


import __08__线程池.__26线程池实现.接口和基本类.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxg
 * @date 2022/1/4-19:43
 * @quotes 小不忍则乱大谋
 */
public class BasicThreadPool extends Thread implements ThreadPool {
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
    /**
     * 初始化线程数量
     */
    private final int initSize;
    /**
     * 线程池最大线程数量
     */
    private final int maxSize;
    /**
     * 线程池核心数量
     */
    private final int coreSize;
    /**
     * 工作线程队列
     */
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    /**
     * 线程创建工厂
     */
    private final ThreadFactory threadFactory;
    private final long keepAliveTime;
    /**
     * 任务队列
     */
    private final TaskQueue taskQueue;
    /**
     * 线程池是否已经被shutdown
     */
    private volatile boolean isShutdown = false;
    /**
     * 当前活跃的线程数量
     */
    private int activeCount;
    private final TimeUnit timeUnit;

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup GROUP = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndIncrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable task) {
            //noinspection AlibabaAvoidManuallyCreateThread
            return new Thread(GROUP, task, "thread-pool" + COUNTER.getAndDecrement());
        }
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;

        }
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    /**
     *  构造线程池需要传入的参数
     * @param initSize 初始线程池大小
     * @param maxSize 线程池最大数量
     * @param coreSize 活跃线程
     * @param threadFactory 线程工厂
     * @param queueSize 任务数量
     * @param denyPolicy 拒绝策略
     * @param keepAliveTime 活跃事件
     * @param timeUnit 计时机器
     */
    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.keepAliveTime = keepAliveTime;
        this.taskQueue = new LinkedTaskQueue(queueSize, denyPolicy, this);
        this.timeUnit = timeUnit;
        this.init();
    }

    /**
     * 初始化时，先创建initSize个线程
     */
    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    /**
     * 提交任务
     * @param task 任务
     */
    @Override
    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("The thread poll is destroy");
        }
        // 提交任务只是简单地往任务队列中插入Runnable
        taskQueue.offer(task);
    }

    @Override
    public int getInitSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown) {
            throw new IllegalStateException("The thread pool is destroy");
        }
        return taskQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (BasicThreadPool.class) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    /**
     * 线程池自动维护
     */
    private void newThread() {
        // 创建任务线程，并且启动
        InternalTask internalTask = new InternalTask(taskQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        // 从线程池中移除某个线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    /** run() */
    @Override
    public void run(){
        // run()继承自Thread, 主要用于维护线程数量，比如扩容、回收等工作
        while(!isShutdown && !isInterrupted()){
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized(BasicThreadPool.class){
                if(isShutdown){
                    break;
                }
                // 当前的队列中有任务尚未处理，并且activeCount < coreSize 则继续扩展
                if(taskQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    // 不让线程的扩容直接达到maxSize;
                    continue;
                }
                if(taskQueue.size() >0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }
                // 如果任务队列中没有任务，则需要回收，回收至coneSize即可
                if(taskQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }
    /**
     * 线程池销毁
     */
    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

}
