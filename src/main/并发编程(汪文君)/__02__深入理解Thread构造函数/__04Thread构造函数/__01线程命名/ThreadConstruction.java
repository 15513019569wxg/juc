package __02__深入理解Thread构造函数.__04Thread构造函数.__01线程命名;

import java.util.stream.IntStream;

/**
 * The type Thread construction.
 *
 * @author wxg
 * @date 2021 /12/31-17:29
 * @quotes 小不忍则乱大谋
 */
public class ThreadConstruction {
    private final static String PREFIX = "ALEX-";

    private static Thread createThread(final int intName){
        //noinspection AlibabaAvoidManuallyCreateThread
        return new Thread(() -> System.out.println(Thread.currentThread().getName()), PREFIX + intName);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        IntStream.range(0, 5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);
        modifyThreadName();
    }

    /**
     * Modify thread name thread.
     *
     * @return the thread
     */
    public static Thread modifyThreadName(){
        Thread original = new Thread(() ->{
            System.out.println(Thread.currentThread().getName());
        },"original");
        //  在线程启动之前，修改线程的名字
        original.setName("target");
        original.start();
        return original;

    }


}
