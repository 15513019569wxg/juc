package __07阻塞队列;/*
    @author wxg
    @date 2021/12/30-9:31
    */


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author capensis
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //  创建阻塞队列
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //  第一组
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
        /* System.out.println(blockingQueue.add("d")); */

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        /*System.out.println(blockingQueue.remove()); */

        // 第二组
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        /*System.out.println(blockingQueue.offer("d")); */

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        /* 不报错*/
        System.out.println(blockingQueue.poll());

        //  第三组
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        /* blockingQueue.put("d"); */  //阻塞
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //blockingQueue.take();   //  阻塞


        //  第四组
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d", 3L, TimeUnit.SECONDS));

    }
}
