package __19__Future设计模式;


/**
 * 用于提交任务
 * @author wxg
 * @date 2022/1/10-13:50
 * @quotes 小不忍则乱大谋
 */
public interface FutureService<IN, OUT> {

    /**
     * 提交不需要返回值的任务, Future.get()的返回值将会是null
     * @param runnable 任务
     * @return 任务计算结果
     */
    Future<?> submit(Runnable runnable);

    /**
     * 提交需要返回值的任务, 其中Task接口代替了Runnable接口
     * @param task 任务
     * @param input 输入
     * @return 计算结果
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    /**
     * 增加回调接口Callback, 当任务执行结束之后，Callback会得到执行
     * @param task 计算任务
     * @param input 输入参数
     * @param callback 回调接口
     * @return Future
     */
    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    /**
     * 使用静态方法创建一个FutureService的实现
     * @param <T> 输入参数
     * @param <R> 返回结果
     * @return 输入参数， 输出结果
     */
    static <T, R> FutureService<T, R> newService(){
        return new FutureServiceImpl<>();
    }
}
