package __09分支合并;/*
    @author wxg
    @date 2021/12/30-11:59
    */


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

class MyTask extends RecursiveTask<Integer>{
    private static final int INTERVAL = 10;
    private final int begin;
    private final int end;
    private int result;

    public MyTask(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    /**
     * 拆分和合并过程
     */
    @Override
    protected Integer compute() {
        //  将左右差距小于等于10以内的数据相加
        if(end - begin <= INTERVAL) {
            IntStream.rangeClosed(begin, end).forEach(i -> result += i);
        } else{
            //  获取中间值
            int middle = (begin + end)/2;
            // 拆分左边
            MyTask myTaskLeft = new MyTask(begin, middle);
            //  拆分右边
            MyTask myTaskRight = new MyTask(middle+1, end);
            //  调用fork()方法拆分
            myTaskLeft.fork();
            myTaskRight.fork();
            //  合并结果
            result = myTaskLeft.join() + myTaskRight.join();
        }
        return result;
    }
}


/**
 * @author capensis
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        MyTask myTask = new MyTask(1, 100);
        //  创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //  将任务提交
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        Integer result = null;
        try {
            //  获取任务的结果
            result = submit.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        //  关闭池对象
        forkJoinPool.shutdown();
    }
}
