package __17__读写锁分离设计模式;

/**
 * 包可见,创建时使用ReadWriterLock的create()
 * @author wxg
 * @date 2022/1/9-16:58
 * @quotes 小不忍则乱大谋
 */
class ReadWriteLockImpl implements ReadWriteLock {
    /**
     * 定义对象锁
     */
    private final Object MUTEX = new Object();

    /**
     * 当前有多少个线程正在写入
     */
    private int writingWriters = 0;

    /**
     * 当前有多少个线程正在等待写入
     */
    private int waitingWriters = 0;

    /**
     * 当前有多少个线程正在读
     */
    private int readingReaders = 0;

    /**
     * read和write的偏好
     */
    private boolean preferWriter;

    /**
     * 默认情况下preferWriter为true
     */
    public ReadWriteLockImpl() {
        this(true);
    }

    /**
     * 构造ReadWriteLockImpl 并且传入preferWriter
     * @param preferWriter read和write的偏好
     */
    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 创建读锁
     * @return 读锁
     */
    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    /**
     * 使写入线程的数量增加
     */
    void incrementWritingWriters() {
        writingWriters++;
    }

    /**
     * 使等待写入线程的数量增加
     */
    void incrementWaitingWriters(){
        waitingWriters++;
    }

    /**
     * 使读线程的数量增加
     */
    void incrementReadingReaders(){
        readingReaders++;
    }

    /**
     * 使写线程的数量减少
     */
    void decrementWritingWriters(){
        waitingWriters--;
    }

    /**
     * 使等待获取写入锁的数量减一
     */
    void decrementWaitingWriters(){
        waitingWriters--;
    }

    /**
     * 使读取线程的数量减一
     */
    void decrementReadingReaders(){
        readingReaders--;
    }

    /**
     * 获取当前有多少个线程正在进行写操作
     * @return 正在写线程的个数
     */
    @Override
    public int getWritingWriters() {
        return writingWriters;
    }

    /**
     * 获取当前多少个线程正在进行读操作
     * @return 正在进行读操作线程的个数
     */
    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    /**
     * 获取当前有多少个线程正在等待获取写入锁
     * @return 等待获取写入锁线程的个数
     */
    @Override
    public int getWaitingWriters() {
        return waitingWriters;
    }


    @Override
    public int getWaitingReaders() {
        return 0;
    }

    /**
     * 获取对象锁
     * @return 对象锁
     */
    Object getMutex(){
        return MUTEX;
    }

    /**
     * 获取当前是否偏向写锁
     * @return 读或写
     */
    boolean getPreferWriter() {
        return preferWriter;
    }

    /**
     * 设置写锁偏好
     * @param preferWriter 读或写
     */
    void changePrefer(boolean preferWriter){
        this.preferWriter = preferWriter;
    }
}
