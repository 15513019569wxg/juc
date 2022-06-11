package __22__balking设计模式;

/**
 * @author wxg
 * @date 2022/1/13-1:34
 * @quotes 小不忍则乱大谋
 */
public class BalkingTest {
    public static void main(String[] args) {
        new DocumentEditThread(
                "E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)\\第二十二章__balking设计模式",
                "balking.txt").start();
    }
}
