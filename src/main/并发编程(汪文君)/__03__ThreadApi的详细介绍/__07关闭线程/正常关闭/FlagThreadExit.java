package __03__ThreadApi的详细介绍.__07关闭线程.正常关闭;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * The type Flag thread exit.
 *
 * @author wxg
 * @date 2022 /1/1-19:57
 * @quotes 小不忍则乱大谋
 */
public class FlagThreadExit {
    /**
     * The type My task.
     */
    static class MyTask extends Thread{
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while(!closed && !isInterrupted()){
                // 正在运行
                IntStream.range(0, 10).forEach(System.out::println);
            }
            System.out.println("I will be exiting");
        }

        /**
         * Close.
         */
        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        final MyTask myTask = new MyTask();
        myTask.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown");
        myTask.close();
    }
}
