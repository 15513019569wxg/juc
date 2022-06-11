package __04__线程安全和数据同步.__11死锁分析;

import java.util.concurrent.Semaphore;

/**
 * The type Philosophers test.
 *
 * @author wxg
 * @date 2022 /1/2-11:36
 * @quotes 小不忍则乱大谋
 */
class PhilosophersTest {
    /**
     * 控制最多允许四位哲学家同时进餐
     */
    static final Semaphore COUNT = new Semaphore(4);
    /**
     * 初始化信号量
     */
    static final Semaphore[] MUTEX = {new Semaphore(1), new Semaphore(1),
            new Semaphore(1), new Semaphore(1), new Semaphore(1)};

    /**
     * The type Philosopher.
     */
    static class Philosopher extends Thread {
        /**
         * Instantiates a new Philosopher.
         *
         * @param name the name
         */
        Philosopher(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //  思考
                    COUNT.acquire();    //判断是否超过四人准备进餐
                    int i = Integer.parseInt(super.getName());
                    MUTEX[i].acquire(); //判断缓冲池中是否仍有空闲的缓冲区
                    MUTEX[(i + 1) % 5].acquire();   //判断是否可以进入临界区（操作缓冲池）
                    System.out.println("哲学家 " + i + " 号 吃了通心粉");
                    //  进餐
                    MUTEX[i].release(); //退出临界区，允许别的进程操作缓冲池
                    MUTEX[(i + 1) % 5].release();   //缓冲池中非空的缓冲区数量加1，可以唤醒等待的消费者进程
                    COUNT.release();    //用餐完毕，别的哲学家可以开始进餐
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("哲学家执行时产生异常！");
                }
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Philosopher p0 = new Philosopher("0");
        Philosopher p1 = new Philosopher("1");
        Philosopher p2 = new Philosopher("2");
        Philosopher p3 = new Philosopher("3");
        Philosopher p4 = new Philosopher("4");

        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

    }
}


