package __01线程间通信.lock;/*
    @author wxg
    @date 2021/12/28-10:18
    */


import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

class Share{
    private int number;
    private final Lock reentrantLock =  new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();
    /**
     *    +1
     */
    public  void increase(){
        //  上锁
        reentrantLock.lock();
        try {
            //  判断
            while(number != 0){
                try {
                    condition.await(); //不是0，就等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //  干活
            number++;   //  是0，就+1
            System.out.println(Thread.currentThread().getName() + "::" + number);
            //  通知其他线程
            condition.signalAll();
        } finally {
            //  解锁
            reentrantLock.unlock();
        }
    }

    /**
     *  -1
     */
    public  void decrease(){
        //  上锁
        reentrantLock.lock();
        try {
            //  判断
            while(number != 1){
                try {
                    condition.await(); //不是1，就等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //  干活
            number--;   //  是1，就-1
            System.out.println(Thread.currentThread().getName() + "::" + number);
            //  通知其他线程
            condition.signalAll();
        } finally {
            //  解锁
            reentrantLock.unlock();
        }
    }
}



/**
 * @author capensis
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Share share = new Share();
        Collections.singletonList("AA").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.increase()), s).start());
        Collections.singletonList("BB").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.decrease()), s).start());
        Collections.singletonList("CC").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.increase()), s).start());
        Collections.singletonList("DD").forEach(s -> new Thread(() -> IntStream.range(0, 10).forEach(i -> share.decrease()), s).start());
    }
}
