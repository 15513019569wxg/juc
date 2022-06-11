package __01__快速认识线程.__03Runnable接口的策略模式.策略模式;

import java.sql.ResultSet;

/**
 * The interface Row handler.
 *
 * @param <T> the type parameter
 * @author wxg
 * @date 2021 /12/31-11:11
 * @quotes 小不忍则乱大谋
 */
public interface RowHandler<T> {
    /**
     * 该接口只负责对从数据库中查询出来的结果进行操作，至于最终返回什么样的数据结构，
     * 需要子类自己去实现，类似于Runnable接口
     *
     * @param rs 结果集
     * @return the t
     */
    T handler(ResultSet rs);
}
