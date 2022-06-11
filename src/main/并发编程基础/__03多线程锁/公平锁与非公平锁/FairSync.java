package __03多线程锁.公平锁与非公平锁;


import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 第一步，创建资源类，定义属性和方法
 */
class Ticket{
    /**
     *  第二步
     */
    private int number  = 30;
    private final ReentrantLock lock = new ReentrantLock(false);

    public void sale(){
        //  上锁
        lock.lock();
        try {
            if( number > 0) {
                System.out.println(Thread.currentThread().getName() + ": 卖出 " + number-- + " 剩下 " + number);
            }
        } finally {
            //  解锁
            lock.unlock();
        }
    }
}



/**
 * @author capensis
 */
public class FairSync {
    public static void main(String[] args) {
        // 第三步
        Ticket ticket = new Ticket();
        Arrays.asList("AA", "BB", "CC").forEach(s -> new Thread(() -> IntStream.range(0, 40).forEach(i -> ticket.sale()), s).start());
    }
}
