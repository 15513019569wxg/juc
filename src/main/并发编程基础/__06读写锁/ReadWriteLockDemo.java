package __06读写锁;/*
    @author wxg
    @date 2021/12/29-20:12
    */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;


/**
 * @author capensis
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycache myCache = new Mycache();
        //  创建线程存数据
        IntStream.range(0, 5).forEach(i -> {
            final int number = i;
            new Thread(() -> myCache.put(number + "", number + ""), String.valueOf(i)).start();
        });
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  创建线程取数据
        IntStream.range(0, 5).forEach(i -> {
            final int number = i;
            new Thread(() -> myCache.get(number + ""), String.valueOf(i)).start();
        });
    }
}


class Mycache{
    /** 创建map集合 */
    private volatile Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    /** 放数据 */
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        //  暂停一会
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作" + key);
            TimeUnit.SECONDS.sleep(30);
            //  放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    /** 取数据 */
    public Object get(String key){
        reentrantReadWriteLock.readLock().lock();
        Object result = null;
        //  暂停一会
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取操作" + key);
            TimeUnit.SECONDS.sleep(30);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 已经读完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            reentrantReadWriteLock.readLock().unlock();
        }
        return result;

    }
}