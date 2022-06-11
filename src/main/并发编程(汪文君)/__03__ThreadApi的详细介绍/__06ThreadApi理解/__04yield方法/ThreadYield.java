package __03__ThreadApi的详细介绍.__06ThreadApi理解.__04yield方法;

import java.util.stream.IntStream;

/**
 * The type Thread yield.
 *
 * @author wxg
 * @date 2022 /1/1-9:58
 * @quotes 小不忍则乱大谋
 */
public class ThreadYield {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        IntStream.range(0,2).mapToObj(ThreadYield::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        //noinspection AlibabaAvoidManuallyCreateThread
        return new Thread(()->{
           // ①注释部分
           if(index == 0){
               Thread.yield();
           }
           System.out.println(index);
       });
    }
}
