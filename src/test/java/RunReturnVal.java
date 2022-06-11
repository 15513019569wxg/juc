/**
 * @author wxg
 * @date 2022/1/1-18:54
 * @quotes 小不忍则乱大谋
 */
public class RunReturnVal {
    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {});
        thread.start();
    }
}
