package __19__Future设计模式;

/**
 * @author wxg
 * @date 2022/1/10-13:43
 * @quotes 小不忍则乱大谋
 */
public interface Future<T> {

    /**
     * 返回计算后的结果, 该方法会陷入阻塞状态
     * @return 计算结果
     * @throws InterruptedException 中断异常
     */
   T get() throws InterruptedException;

    /**
     * 判断任务是否已经被执行完成
     * @return 是否完成
     */
   boolean done();
}
