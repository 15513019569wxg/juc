package __19__Future设计模式;

/**
 * @author wxg
 * @date 2022/1/10-14:04
 * @quotes 小不忍则乱大谋
 */
public class FutureImpl<T> implements Future<T> {
    /**
     * 计算结果
     */
    private T result;

    /**
     * 任务是否完成
     */
    private boolean isDone = false;

    /**
     * 定义对象锁
     */
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized(LOCK){
            //  当任务还没完成时, 调用get()会被挂起而进入阻塞
            while(!isDone){
                LOCK.wait();
            }
            // 返回最终计算结果
            return result;
        }
    }

    /**
     * 为FutureTask设置计算结果
     * @param result 计算结果
     */
    protected void finish(T result){
        synchronized(LOCK){
            // balking设计模式
            if(isDone){
                return;
            }
            //  计算完成, 为result指定结果, 并且将isDone设为true, 同时唤醒阻塞中的线程
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    /**
     * 返回当前任务是否已经完成
     * @return 任务是否完成
     */
    @Override
    public boolean done() {
        return isDone;
        }
}

