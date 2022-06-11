package __01线程间通信.定制化通信;/*
    @author wxg
    @date 2021/12/28-11:12
    */

import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 1、定义资源类
 */
class ShareSource{
    private int flag = 1;
    /**
     * 创建锁
     */
    private final ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * 创建condition
    */
    private final Condition condition1 = reentrantLock.newCondition();
    private final Condition condition2 = reentrantLock.newCondition();
    private final Condition condition3 = reentrantLock.newCondition();

    public void print5(int loop) throws InterruptedException {
        //  上锁
        reentrantLock.lock();
        try {
            while(flag != 1){
                condition1.await();
            }
            IntStream.range(1, 6).mapToObj(i -> Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop).forEach(System.out::println);
            //  通知BB线程
            flag = 2;
            condition2.signal();
        } finally {
            //  解锁
            reentrantLock.unlock();
        }
    }


    public void print10(int loop) throws InterruptedException {
        //  上锁
        reentrantLock.lock();
        try {
            while(flag != 2){
                condition2.await();
            }
            IntStream.range(1, 11).mapToObj(i -> Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop).forEach(System.out::println);
            //  通知CC线程
            flag = 3;
            condition3.signal();
        } finally {
            //  解锁
            reentrantLock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        //  上锁
        reentrantLock.lock();
        try {
            while(flag != 3){
                condition3.await();
            }
            IntStream.range(1, 16).mapToObj(i -> Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop).forEach(System.out::println);
            //  通知
            flag = 1;
            condition1.signal();
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
        ShareSource shareSource = new ShareSource();
        Collections.singletonList("AA").forEach(s -> new Thread(() -> IntStream.range(1, 11).forEach(i -> {
            try {
                shareSource.print5(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), s).start());

        Collections.singletonList("BB").forEach(s -> new Thread(() -> IntStream.range(1, 11).forEach(i -> {
            try {
                shareSource.print10(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), s).start());

        Collections.singletonList("CC").forEach(s -> new Thread(() -> IntStream.range(1, 11).forEach(i -> {
            try {
                shareSource.print15(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), s).start());
    }
}
