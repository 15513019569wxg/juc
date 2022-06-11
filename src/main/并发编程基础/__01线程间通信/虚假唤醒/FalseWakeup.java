package __01线程间通信.虚假唤醒;/*
    @author wxg
    @date 2021/12/28-9:51
    */


import java.util.Collections;
import java.util.stream.IntStream;

class Share{
    private int number;

    /**
     *   +1
     */
    public synchronized void increase(){
        while(number != 0){
            try {
                wait(); //不是0，就等待; PS: 在哪里睡，就在哪里醒，如果是if判断，只能记住初始状态，之后的状态不再判断
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;   //  是0，就+1
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //  通知其他线程
        notifyAll();
    }

    /**
     *   -1
     */
    public synchronized void decrease(){
        while(number != 1){
            try {
                wait(); //不是1，就等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;   //  是1，就-1
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //  通知其他线程
        notifyAll();
    }
}


/**
 * @author capensis
 */
public class FalseWakeup {
    public static void main(String[] args) {
        Share share = new Share();
        Collections.singletonList("AA").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.increase()), s).start());
        Collections.singletonList("BB").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.decrease()), s).start());
        Collections.singletonList("CC").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.increase()), s).start());
        Collections.singletonList("DD").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.decrease()), s).start());

    }
}
