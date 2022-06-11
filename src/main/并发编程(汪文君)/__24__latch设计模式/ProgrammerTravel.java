package __24__latch设计模式;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 程序测试齐心协力打开门阀
 * @author wxg
 * @date 2022/1/14-13:42
 * @quotes 小不忍则乱大谋
 */
public class ProgrammerTravel extends Thread{
    /**
     * 门阀
     */
    private final Latch latch;

    /**
     * 程序员
     */
    private final String programmer;

    /**
     * 交通工具
     */
    private final String transportation;

    /**
     * 通过构造函数传入Latch, programmer, transportation
     * @param latch 门阀
     * @param programmer 程序员
     * @param transportation 交通工具
     */
    public ProgrammerTravel(Latch latch, String programmer, String transportation) {
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation [" + transportation + "]");
        // 程序员乘坐交通工具花费在路上的实际时间（使用随机数字模拟）
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation );
        // 完成任务时使计数器减一
        latch.countDown();
    }


}
