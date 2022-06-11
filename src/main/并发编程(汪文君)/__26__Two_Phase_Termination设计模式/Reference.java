package __26__Two_Phase_Termination设计模式;

/**
 * @author wxg
 * @date 2022/2/2-10:18
 * @quotes 小不忍则乱大谋
 */
public class Reference {
    /**
     * 1M
     */
    private final byte[] data = new byte[2<<19];

    @Override
    protected void finalize() {
        System.out.println("the reference will be GC...");
    }
}
