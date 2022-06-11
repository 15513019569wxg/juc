package __07__HooK线程以及捕获线程执行异常.__23方法的堆栈追踪;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type Test 2.
 *
 * @author wxg
 * @date 2022 /1/3-20:32
 * @quotes 小不忍则乱大谋
 */
public class Test2 {
    /**
     * Test.
     */
    public void test() {
        Arrays.stream(Thread.currentThread().getStackTrace())
                .filter(element -> !element.isNativeMethod())
                .forEach(element -> Optional.of(element.getClassName() + "::" + element.getMethodName() + "::" + element.getLineNumber())
                        .ifPresent(System.out::println));
    }
}
