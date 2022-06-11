package __24__latch设计模式;

/**
 * 当子任务线程执行超时的时候会抛出异常
 * @author wxg
 * @date 2022/1/14-14:22
 * @quotes 小不忍则乱大谋
 */
public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String message){
        super(message);
    }
}
