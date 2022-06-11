package __08__线程池.__26线程池实现.接口和基本类;

/**
 * @author wxg
 * @date 2022/1/4-19:06
 * @quotes 小不忍则乱大谋
 */
public class InternalTask implements Runnable {
    private final TaskQueue taskQueue;
    private volatile boolean running  = true;

    public InternalTask(TaskQueue taskQueue) {
       this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        // 如果当前任务为running并且没有被中断，则其将不断地从queue中获取task，然后执行run方法
        while(running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    /**
     * 停止当前任务，主要会在线程池的shutdown方法中使用
     */
    public void stop(){
        this.running = false;
    }
}
