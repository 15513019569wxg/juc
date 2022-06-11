package __21__线程上下文设计模式.__03ThreadLocal.案例;

import java.util.stream.IntStream;

/**
 * @author wxg
 * @date 2022/1/12-17:04
 * @quotes 小不忍则乱大谋
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1,5).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
