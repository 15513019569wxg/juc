package __12__volatile关键字的介绍.__37volatileDefect;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/8-10:59
 * @quotes 小不忍则乱大谋
 */
public class VolatileTeFoo {
    /**
     * init_value 的最大值
     */
    final static int MAX = 50;
    /**
     * init_value 的初始值
     */
    static volatile int init_value = 0;

    public static void main(String[] args) {
        // 启动一个Reader线程, 当发现local_value和init_value不同时, 则输出init_value被修改的信息
        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    System.out.printf("The init_value is updated to [%d] \n", init_value);
                    // 对localValue进行重新赋值
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        // 启动一个Update线程, 主要用于对init_value的修改，当local_value>=5的时候则退出生命周期
        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                // 修改init_value
                System.out.printf("The init_value will be changed to [%d] \n", ++init_value);
                localValue = init_value;
                try {
                    // 短暂休眠，目的是为了使Reader线程能够来得及输出变化内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updated").start();
    }
}
