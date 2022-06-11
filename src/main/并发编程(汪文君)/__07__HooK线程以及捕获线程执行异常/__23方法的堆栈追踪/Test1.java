package __07__HooK线程以及捕获线程执行异常.__23方法的堆栈追踪;

/**
 * The type Test 1.
 *
 * @author wxg
 * @date 2022 /1/3-20:32
 * @quotes 小不忍则乱大谋
 */
public class Test1 {
    private final Test2 test2 = new Test2();

    /**
     * Test.
     */
    public void test(){
        test2.test();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Test1().test();
    }
}
