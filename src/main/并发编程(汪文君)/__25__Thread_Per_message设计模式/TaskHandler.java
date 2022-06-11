package __25__Thread_Per_message设计模式;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * TaskHandler用于处理每一个提交的Request请求，由于TaskHandler将被thread执行，因此需要实现Runnable接口
 * 代表每一个工作人员收到任务后的处理逻辑
 * @author wxg
 * @date 2022/1/31-20:34
 * @quotes 小不忍则乱大谋
 */
public class TaskHandler implements Runnable{
    /**
     * 需要处理的Request请求
     */
    public final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle " + request);
        slowly();
        System.out.println("End handle " + request);

    }

    /**
     * 模拟请求处理比较耗时，使线程进入短暂的休眠阶段
     */
    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
