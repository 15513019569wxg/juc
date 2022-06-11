package __25__Thread_Per_message设计模式;

import __08__线程池.__26线程池实现.接口和基本类.ThreadPool;
import __08__线程池.__26线程池实现.详细实现.BasicThreadPool;

/**
 * @author wxg
 * @date 2022/1/31-21:10
 * @quotes 小不忍则乱大谋
 */
public class Operator2 {
    /**
     * 使用线程池替代为每一个请求创建线程
     */
    private final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

    public void call(String business){
        final TaskHandler taskHandler = new TaskHandler(new Request(business));
        threadPool.execute(taskHandler);
    }

}
