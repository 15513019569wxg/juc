package __03多线程锁.可重入锁;/*
    @author wxg
    @date 2021/12/29-11:32
    */


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author capensis
 */
public class Lock {

    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(() -> {
            //  上锁
            reentrantLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "外层");
                //  上锁
                reentrantLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "中层");
                } finally {
                    //  解锁
                   reentrantLock.unlock();
                }
            } finally {
                //  解锁
                reentrantLock.unlock();
            }
        }, "t").start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("aaaa");
            } finally {
                reentrantLock.unlock();
            }
        }).start();


    }
}
