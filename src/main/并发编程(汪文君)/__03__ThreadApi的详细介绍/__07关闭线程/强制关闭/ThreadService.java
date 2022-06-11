package __03__ThreadApi的详细介绍.__07关闭线程.强制关闭;


/**
 * The type Thread service.
 *
 * @author wxg
 * @date 2022 /1/1-16:38
 * @quotes 小不忍则乱大谋
 */
public class ThreadService {
    private Thread executeThread;
    private boolean finished = false;

    /**
     * Execute long.
     *
     * @param task the task
     * @return the long
     */
    public long execute(Runnable task) {
        long start;
        //  用户执行线程只是创建了一个守护线程runner
        //noinspection AlibabaAvoidManuallyCreateThread
        executeThread = new Thread(() -> {

            //  任务交给守护线程完成
            Thread runner = new Thread(task);
            /*
                如果守护线程在这个时间段内不能完成，用户执行线程也只能被迫退出。如果不将执行任务的线程设置为守护线程，
             那么执行任务的线程会一直执行下去，这样显然浪费资源（说明任务的执行逻辑可能写得太差）。所以，这里最好把
             执行任务的线程设置为守护线程。一旦用户执行线程死掉，剩下的线程都是守护线程，jvm自动退出。
             */
            runner.setDaemon(true);
            runner.start();
            try {
                /*
                     用户执行线程必须要给守护线程执行任务的时间（这个时间间隔也是用户执行线程拥有的最大时间权限）。
                  不然，用户执行线程创建完守护线程就死了，剩下的线程是执行任务的守护线程，jvm自动就退出了，守护线程
                  来不及执行任务就死掉了。
                 */
                runner.join();
                /*
                    守护线程还没执行结束，此时finished仍然为false，一旦调用shutdown(), 用户执行线程就开始判断守护线程执行
                 任务是否超时，超时就要中断用户执行线程，此时jvm退出，守护线程也就退出了。
                 */
                finished = true;
            } catch (InterruptedException ignored) {
            }
        });
        executeThread.start();
        start = System.currentTimeMillis();
        return start;
    }

    /**
     * 用户调用该函数判断任务执行情况
     *
     * @param mills 守护线程执行任务的最大时长
     * @param start the start
     */
    public void shutDown(long mills, long start) {
        // 判断
        while (!finished) {
            final long end = System.currentTimeMillis();
            try {
                /*
                    守护线程刚开始执行任务的时候，时间没有超时，用户执行线程进入到这里，此时用户执行线程继续等待即可，
                 可以考虑让用户执行线程sleep(),如果守护线程执行任务超时，就打断叫醒用户执行线程，执行interrupt()
                 */
                //noinspection BusyWait
                Thread.sleep(1000);
                System.out.println("spend " + (end - start) + " ms on task, " + " runner still continues");
            } catch (InterruptedException e) {
                System.out.println("executeThread is interrupted");
                break;
            }

            if (end - start >= mills) {
                System.out.println("spend " + (end - start) + " ms on task, " + " need executeThread to stop compulsorily");
                executeThread.interrupt();
                break;
            }
            if (finished) {
                System.out.println("spend " + (end - start) + " ms on task,  " + " executeThread stop successfully");
            }
        }
        finished = false;
    }
}
