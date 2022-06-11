package __01线程间通信.sync;/*
    @author wxg
    @date 2021/12/28-9:34
    */

import java.util.Collections;
import java.util.stream.IntStream;

class Share{
    private int number;

    /**
     *   +1
     */
    public synchronized void increase(){
        if(number != 0){
            try {
                wait(); //不是0，就等待
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
        if(number != 1){
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
public class ThreadDemo {
    public static void main(String[] args) {
        //  最后一步
        Share share = new Share();
        Collections.singletonList("AA").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.increase()), s).start());
        Collections.singletonList("BB").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.decrease()), s).start());

    }


}
