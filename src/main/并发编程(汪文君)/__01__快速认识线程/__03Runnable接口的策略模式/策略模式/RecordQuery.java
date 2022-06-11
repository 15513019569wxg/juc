package __01__快速认识线程.__03Runnable接口的策略模式.策略模式;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Record query.
 *
 * @author wxg
 * @date 2021 /12/31-11:16
 * @quotes 小不忍则乱大谋
 */
public class RecordQuery {

    private final Connection connection;


    /**
     * Instantiates a new Record query.
     *
     * @param connection 连接
     */
    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    /**
     * 该方法只负责数据的获取
     *
     * @param <T>     数据结构
     * @param handler 该接口中的handler()只负责数据的加工，输出不同类型的数据结构
     * @param sql     sql语句
     * @param params  对象
     * @return 数据 t
     * @throws SQLException 查询异常
     */
    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int index = 1;
        for (Object param : params) {
            preparedStatement.setObject(index++, param);
        }
        final ResultSet resultSet = preparedStatement.executeQuery();
        // 调用RowHandler接口
        return handler.handler(resultSet);
    }
}
