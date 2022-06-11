package __17__读写锁分离设计模式;

/**
 * @author wxg
 * @date 2022/1/9-16:50
 * @quotes 小不忍则乱大谋
 */
public interface ReadWriteLock {
    /**
     * 创建reader锁
     * @return 显式锁加锁
     */
    Lock readLock();

    /**
     * 创建write锁
     * @return 显式锁解锁
     */
    Lock writeLock();

    /**
     * 获取当前有多少个线程正在执行写操作, 最多有1个
     * @return 写操作的个数
     */
    int getWritingWriters();

    /**
     * 获取当前有多少个线程正在执行读操作
     * @return 读操作的个数
     */
    int getReadingReaders();

    /**
     * 获取当前有多少个线程正在等待获取写入锁
     * @return 等待获取写入锁的个数
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少个线程正在等待获取reader锁
     * @return 读操作的个数
     */
    int getWaitingReaders();


    /**
     * 工厂方法, 创建ReadWriteLock
     * @return  读写锁
     */
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }
    /**
     * 工厂方法，创建ReadWriteLock, 并且传入preferWriter
     * @param preferWriter true || false
     * @return 读写锁
     */
    static ReadWriteLock readWriterLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
