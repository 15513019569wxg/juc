package __15__监控任务的生命周期;

import java.util.concurrent.TimeUnit;

/**
 * @author wxg
 * @date 2022/1/9-0:15
 * @quotes 小不忍则乱大谋
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;
    private Cycle cycle;

    /**
     * 指定Task的实现, 默认情况下使用EmptyLifecycle
     *
     * @param task 具体任务
     */
    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    /**
     * 基本构造器
     *
     * @param lifecycle 任务的监控者，响应不同状态的事件并作出反映
     * @param task      具体任务
     */
    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        // task不允许为null
        if (task == null) {
            throw new IllegalStateException("The task is required.");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    /**
     * 监控任务在执行过程中的各个生命周期阶段，任务每经过一个阶段相当于发生一次事件
     */
    @Override
    public final void run() {
        // 在执行线程逻辑单元的时候，分别触发相应的事件
        update(Cycle.STARTED, null, null);
        try {
            update(Cycle.RUNNING, null, null);
            T result = task.call();
            update(Cycle.DONE, result, null);
        } catch (Exception e) {
            // 任务执行过程中出现了异常
            update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cycle);
            }
        } catch (Exception ex) {
            //但是如果任务执行过程中出现错误并且抛出了异常,那么update()就不能忽略该异常,需要继续抛出异常，保持与call()同样的意图。
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
            /*
                响应某个事件的过程中出现了意外，则会导致任务的正常执行受到影响。因此，需要进行异常捕获，并忽略这些异常
            信息以保证TaskLifecycle的实现不影响任务的正常运行。
             */
            // ignore
        }
    }

    /**
     * 获取任务的生命周期状态
     * @return 生命周期状态
     */
    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    public static void main(String[] args) throws InterruptedException {

        Observable objectObservableThread = new ObservableThread<>(
                // 传入Task<> task 接口, 重写call()
                () -> {
                    try {
                        // 任务执行有可能失败
                        TimeUnit.SECONDS.sleep(2);
                        // int i = 1/0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("finished done.");
                    return null;
                });
        // 这里调用的是父类Thread的start()
        objectObservableThread.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("================================================");

        // 传入具体的实现类
        TaskLifecycle.EmptyLifecycle<String> emptyLifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };

        Observable observableThread = new ObservableThread<>(emptyLifecycle, () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished done;");
            return "Hello Observer";
        });
        observableThread.start();
    }
}
