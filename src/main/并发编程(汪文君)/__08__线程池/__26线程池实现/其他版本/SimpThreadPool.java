package __08__线程池.__26线程池实现.其他版本;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


/**
 * @author wxg
 * @date 2022/1/5-8:55
 * @quotes 小不忍则乱大谋
 */
public class SimpThreadPool extends Thread {
    /**
     * 任务队列中默认可以存放多少个任务
     */
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;
    /**
     * 工作线程名称的前缀
     */
    private final static String THREAD_NAME_PREFIX = "SIMPLE_THREAD_POOL-";
    /**
     * 工作线程所在的线程组
     */
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    /**
     * 存储创建好的工作线程
     */
    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();
    /**
     * 任务队列
     */
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    /**
     * 默认拒绝策略
     */
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException(" discard the task");
    };
    /**
     * 工作线程名称的前缀初始序号
     */
    private static volatile int seq = 0;
    /**
     * 最小线程数量
     */
    private final int minThreadCount;
    /**
     * 最大线程数量
     */
    private final int maxThreadCount;
    /**
     * 活跃线程数量
     */
    private final int activeThreadCount;
    /**
     * 任务数量
     */
    private final int taskSize;
    /**
     * 拒绝策略
     */
    private final DiscardPolicy discardPolicy;
    /**
     * 初始化size个线程
     */
    private int threadInitSize;
    /**
     * 线程池死亡状态
     */
    private volatile boolean isDestroyed = false;

    public SimpThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    /**
     * 初始化线程池
     *
     * @param minThreadCount    线程池最小线程
     * @param activeThreadCount 线程池活跃线程
     * @param maxThreadCount    线程池最大线程
     * @param taskSize          任务队列中可以存放的任务数量
     * @param discardPolicy     默认拒绝策略
     */
    public SimpThreadPool(int minThreadCount, int activeThreadCount, int maxThreadCount, int taskSize, DiscardPolicy discardPolicy) {
        this.minThreadCount = minThreadCount;
        this.activeThreadCount = activeThreadCount;
        this.maxThreadCount = maxThreadCount;
        this.taskSize = taskSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public static void main(String[] args) throws InterruptedException {
        SimpThreadPool simpThreadPool = new SimpThreadPool();
//      SimpThreadPool simpThreadPool = new SimpThreadPool();
        IntStream.range(0, 40).<Runnable>mapToObj(i -> () -> {
            System.out.println("The task " + i + " be serviced by  " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("================ MAIN CLOSED ===================");
                e.printStackTrace();
            }
            System.out.println("The task " + i + " serviced by  " + Thread.currentThread().getName() + "  has been finished already");
        }).forEach(simpThreadPool::submitTaskToTaskQueue);

        //给线程执行任务足够的时间
        Thread.sleep(30_000L);
        //关闭线程池
        simpThreadPool.shutdown();
        // 再次提交任务
        //simpThreadPool.submitTaskToTaskQueue(() -> System.out.println("================"));
    }

    public int getThreadInitSize() {
        return threadInitSize;
    }

    public int getTaskSize() {
        return taskSize;
    }

    public int getMinThreadCount() {
        return minThreadCount;
    }

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public int getActiveThreadCount() {
        return activeThreadCount;
    }

    /**
     * 为客户端提供提交任务到任务队列的接口
     */
    public void submitTaskToTaskQueue(Runnable task) {
        if (isDestroyed) {
            throw new IllegalStateException(" The thread pool already destroy and not allow submit task");
        }
        synchronized (TASK_QUEUE) {
            // 如果线程池里的任务+正在执行的任务 > 10, 告警一下
            if (TASK_QUEUE.size() > taskSize) {
                discardPolicy.discard();
            }
            //else {// 只有此时线程池里的任务+正在执行的任务 < 10 时, 才允许添加任务
            TASK_QUEUE.addLast(task);
            // 通知线程池中阻塞的工作线程
            TASK_QUEUE.notifyAll();
            //}
        }
    }

    /**
     * 记录线程池相关的参数信息
     */
    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isDestroyed) {
            System.out.printf("Pool#Min: %d, Active: %d, Max: %d, Current: %d, queueTaskSize: %d\n",
                    minThreadCount, activeThreadCount, maxThreadCount, threadInitSize, TASK_QUEUE.size());
            try {
                //noinspection BusyWait
                Thread.sleep(500L);
                if (TASK_QUEUE.size() > activeThreadCount && threadInitSize < activeThreadCount) {
                    for (int i = threadInitSize; i < activeThreadCount; i++) {
                        createWorkerTask();
                    }
                    System.out.println("================ THE POOL INCREMENTED================");
                    threadInitSize = activeThreadCount;
                } else if (TASK_QUEUE.size() > maxThreadCount && threadInitSize < maxThreadCount) {
                    for (int i = threadInitSize; i < maxThreadCount; i++) {
                        createWorkerTask();
                    }
                    System.out.println("=========== THE POOL INCREMENTED TO MAXTHREADCOUNT ============");
                    threadInitSize = maxThreadCount;
                }
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && threadInitSize > activeThreadCount) {
                        System.out.println("============== START TO REDUCE ==============");
                        int releaseSize = threadInitSize - activeThreadCount;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            final WorkerTask next = it.next();
                            next.close();
                            next.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        threadInitSize = activeThreadCount;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始循环创建工作线程, 默认创建size个工作线程
     */
    private void init() {
        for (int i = 0; i < minThreadCount; i++) {
            createWorkerTask();
        }
        this.threadInitSize = minThreadCount;
        // 类本身也是一个线程
        this.start();
    }

    /**
     * 创建工作线程
     */
    private void createWorkerTask() {
        final WorkerTask threadTask = new WorkerTask(GROUP, THREAD_NAME_PREFIX + seq++);
        threadTask.start();
        // 将线程加入到线程队列中
        THREAD_QUEUE.add(threadTask);
    }

    /**
     * 关闭线程
     */
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            //noinspection BusyWait
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask thread : THREAD_QUEUE) {
                    if (thread.getTaskState() == TaskState.BLOCKED) {
                        thread.interrupt();
                        thread.close();
                        initVal--;
                    } else {
                        // 等其他线程take()时，变为阻塞状态
                        Thread.sleep(10);
                    }
                }
            }
        }
        System.out.println(GROUP.activeCount());
        isDestroyed = true;
        System.out.println("The thread pool disposed");
    }

    /**
     * 任务状态
     */
    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    private enum TaskState {
        BLOCKED, DEAD, FREE, RUNNING
    }

    /**
     * 定义一个拒绝任务接口
     */
    private interface DiscardPolicy {
        /**
         * 拒绝方法
         *
         * @throws DiscardException 拒绝异常
         */
        void discard() throws DiscardException;
    }

    /**
     * 定义一个静态内部拒绝异常类
     */
    private static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 内部工作线程
     */
    private static class WorkerTask extends Thread {
        // 初始状态
        private volatile TaskState taskState = TaskState.FREE;

        private WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        private TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                // 获取任务队列中的任务
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        taskState = TaskState.BLOCKED;
                        try {
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("================ RUN CLOSED ===================");
                            break OUTER;
                        }
                    }
                    // 将TASK_QUEUE中的任务赋给Runnable(多态)
                    runnable = TASK_QUEUE.removeFirst();
                }
                // 执行任务的run(),修改任务的状态
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    // 执行任务花费时间很长，不要放在锁内,这里执行的就是传入的Runnable task
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        // 任务完成
        private void close() {
            taskState = TaskState.DEAD;
        }
    }

}
