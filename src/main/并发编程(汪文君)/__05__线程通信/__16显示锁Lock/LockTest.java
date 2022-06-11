package __05__线程通信.__16显示锁Lock;

import java.util.Optional;
import java.util.stream.Stream;

import static __05__线程通信.__16显示锁Lock.MyLock.TimeOutException;

/**
 * The type Lock test.
 *
 * @author wxg
 * @date 2022 /1/3-2:06
 * @quotes 小不忍则乱大谋
 */
public class LockTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(name -> new Thread(() -> {
            try {
                /*
                     注意这里，该方法调用结束之后，当前线程已经失去了锁，但是其他线程仍然处于阻塞状态(因为
                 this.initValue=true,导致其他线程仍然出于wait状态),直到当前线程调用unlock()之后this.initValue=false,
                 notifyAll()之后，其他阻塞线程才可以争抢锁
                 */
                booleanLock.lock(5_000L); // ("T1", "T2", "T3", "T4")
                Optional.of(Thread.currentThread().getName() + " have the lock Monitor").ifPresent(System.out::println);
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            //  捕捉超时异常，线程结束
            } catch (TimeOutException e) {
               Optional.of(Thread.currentThread().getName() + " Time out").ifPresent(System.out::println);
            } finally {
                booleanLock.unlock();
            }
        }, name).start());
//        Thread.sleep(100);
        /*
            如果main线程调用unlock(),会修改initValue为false,同时将其他阻塞线程激活，造成当前线程并未释放锁，
         其他线程就有机会获得锁，这是不可以的(因为此时当前线程并未调用unlock())。
            通过进行线程判断，使得该语句失效就可以了。
         */
//        booleanLock.unlock();   // main
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is Working...").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
