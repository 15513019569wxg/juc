package __15__监控任务的生命周期;

/**
 * @author wxg
 * @date 2022/1/9-0:07
 * @quotes 小不忍则乱大谋
 */
@FunctionalInterface
public interface Task<T> {
    /**
     * 任务执行接口，该接口允许有返回值
     * @return 运行结果
     */
    T call();
}
