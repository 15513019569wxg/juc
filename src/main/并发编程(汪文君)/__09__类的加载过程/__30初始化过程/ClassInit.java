package __09__类的加载过程.__30初始化过程;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wxg
 * @date 2022/1/7-10:31
 * @quotes 小不忍则乱大谋
 */
public class ClassInit {
    static{
        System.out.println("The ClassInit static code block will be invoke");
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,5).forEach(i -> new Thread(ClassInit::new));
    }
}
