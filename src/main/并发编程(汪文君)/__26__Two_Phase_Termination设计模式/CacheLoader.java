package __26__Two_Phase_Termination设计模式;

/**
 * @author wxg
 * @date 2022/2/2-10:30
 * @quotes 小不忍则乱大谋
 */

@FunctionalInterface
public interface CacheLoader<K, V> {
    /**
     * 定义加载数据的方法
     * @param k 输入参数
     * @return V    输出参数
     */
    V load(K k);
}
