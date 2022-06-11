package __05辅助类;/*
    @author wxg
    @date 2021/12/29-15:57
    */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author capensis
 */
public class CycleBarrierDemo {
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐7颗龙珠就可以召唤神龙");
        });

        //  集齐7颗龙珠
        IntStream.range(0, 7).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 龙珠被收集了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, String.valueOf(i)).start());
    }
}
