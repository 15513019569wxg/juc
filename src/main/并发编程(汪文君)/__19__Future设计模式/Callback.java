package __19__Future设计模式;

/**
 * @author wxg
 * @date 2022/1/10-18:42
 * @quotes 小不忍则乱大谋
 */
public interface Callback<T> {
    /**
     * 任务完成后会调用该方法，其中T为任务执行后的结果
     * @param t 计算结果
     */
    void call(T t);
}
