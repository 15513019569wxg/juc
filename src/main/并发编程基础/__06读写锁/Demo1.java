package __06读写锁;/*
    @author wxg
    @date 2021/12/29-21:38
    */


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author capensis
 */
public class Demo1 {
    public static void main(String[] args) {
        //  可重入读写锁对象
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        // 锁降级
        //  1、获取写锁
        writeLock.lock();
        System.out.println("atguigu");
        //  2、获取读锁
        readLock.lock();
        System.out.println("------read");
        //  3、释放写锁
        writeLock.unlock();
        //  4、释放读锁
        readLock.unlock();

    }
}
