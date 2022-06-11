package __15__监控任务的生命周期;

/**
 * @author wxg
 * @date 2022/1/8-23:38
 * @quotes 小不忍则乱大谋
 */
public interface Observable {
    enum Cycle{
        /**
         * 任务生命周期的枚举类型
         */
        STARTED, RUNNING, DONE, ERROR
    }

    /**
     * 获取当前任务的生命周期状态
     * @return 生命周期
     */
    Cycle getCycle();

    /** 定义启动线程的方法， 主要作用是为了屏蔽Thread的其他方法*/
    default void start(){
        System.out.println(" Observable's start() is called...");
    }

    /**
     * 定义线程的打断方法， 作用与start方法一样， 也是为了屏蔽Thread的其他方法
     */
    void interrupt();
}
