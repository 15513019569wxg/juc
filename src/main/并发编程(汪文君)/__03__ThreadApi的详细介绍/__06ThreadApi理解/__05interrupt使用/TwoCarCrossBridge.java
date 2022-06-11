package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/5-22:32
 * @quotes 小不忍则乱大谋
 */
public class TwoCarCrossBridge {
    public static void main(String[] args) {

        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread carTwo = new Thread(() -> {
            System.out.println("卡丁2号 准备过桥");
            System.out.println("发现1号在过, 开始等待");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("卡丁2号 开始过桥");
            }
            System.out.println("卡丁2号 过桥完毕");
        });


        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread carOne = new Thread(() -> {
            System.out.println("卡丁1号 开始过桥");
            final int timeSpend = new Random().nextInt(500) + 1000;
            try {
                TimeUnit.MILLISECONDS.sleep(timeSpend);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("卡丁1号 过桥完毕 耗时：" + timeSpend);

            carTwo.interrupt();
        });

        carOne.start();
        carTwo.start();
    }
}
