package __19__Future设计模式;

/**
 * @author wxg
 * @date 2022/1/10-14:00
 * @quotes 小不忍则乱大谋
 */
@FunctionalInterface
public interface Task<IN, OUT> {
    /**
     * 给定一个参数, 经过计算返回结果
     * @param input 输入参数
     * @return 计算结果
     */
    OUT get(IN input);
}
