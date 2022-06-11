package __01__快速认识线程.__00线程状态;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/5-22:22
 * @quotes 小不忍则乱大谋
 */
public class ThreadStateTest {
    /**
     * 当线程处于BLOCKED, WAITING, TIMED_WAITING时，会抛出interruptException
     *      线程如何转变为BLOCKED, WAITING, TIMED_WAITING？
     *<p>
     *      如果线程没有睡眠, 调用它的interrupt会怎样？
     *<p>         线程只有在不活跃的状态下，才会被中断抛出interruptException, 被中断的异常会被wait(), sleep(), join()捕捉
     *          如果线程正在运行，其他线程调用该线程的interrupt()，该线程会怎样对待这个打断呢？
     *<p></p>      中断是否会产生效果与线程是否在活跃状态没有关系，之所以当线程处于不活跃的状态(wait(), sleep(), join())时，其他线程调用该线程的
     *          interrupt()会产生中断并且抛出异常，是因为如果不抛异常，该线程无法回到运行状态, 不回到运行状态，就没办法处理中断异常
     *<p></p>      ps：线程只有处于非活跃的状态下，才可能抛出异常；
     *<p></p>      ps: 线程如果在活跃状态，根本就不需要去捕获interruptException;
     *<p></p>      ps: 想要打断线程，只需要调用interrupt()，管它处于什么状态，管它活不活跃，只要调用interrupt(),线程就被打断了,线程并不是收到interrupt()
     *          才被中断。
     *<p></p>      ps: 中断并不一定就会收到interruptException, 中断只需要调用interrupt()即可，但是接收中断并不一定是抛出interruptException，
     *          interruptException只是接收中断的一种形式,是线程在非活跃状态收到中断的表现, 线程在活跃状态时收到中断，只是修改了一下线程的中断标记位
     *<p></p>      ps: 对于活跃的线程，如果此时被其他线程中断，该线程不会理睬其他线程中断自身的请求，继续做该做的事情；当然，如果想要处理中断，
     *          也是可以的。不过，一个运行的线程必须要知道自己是不是被其他线程请求中断了, 方法是调用自己的interrupted()和isInterrupted()
     *<p>
     *
     * @param args 参数
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread();
        System.out.println("1- " + thread.getState() );
        thread.start();
        System.out.println("2- " + thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3- " + thread.getState());
    }
}
