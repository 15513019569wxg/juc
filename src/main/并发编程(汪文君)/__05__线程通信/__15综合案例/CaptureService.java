package __05__线程通信.__15综合案例;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The type Capture service.
 *
 * @author wxg
 * @date 2022 /1/2-23:29
 * @quotes 小不忍则乱大谋
 */
public class CaptureService {
    /**
     * 存放一个标记量（代表已经启动的线程）
     */
    private static final LinkedList<Control> CONTROLS = new LinkedList<>();

    private static final int MAX_WORKER = 5;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final List<Thread> worker = new ArrayList<>();

        Stream.of("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        worker.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //  main()最后执行
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name) {
        //noinspection AlibabaAvoidManuallyCreateThread
        return new Thread(() -> {

            Optional.of("==========The worker [" + Thread.currentThread().getName() + "] BEGIN capture data===============").ifPresent(System.out::println);

            synchronized (CaptureService.class) {
                while (CONTROLS.size() > MAX_WORKER) {
                    System.out.println("============ 出现 [" + Thread.currentThread().getName() + "] 线程 "  + (CONTROLS.size()+1) +"th" + " 阻塞的情况===========");
                    try {
                        CaptureService.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("==================The worker [" + Thread.currentThread().getName() + "] STARTS store " + (CONTROLS.size()+1) + "th" + " Control==============");
                try {
                    Thread.sleep(10_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //  放入容器中
                CONTROLS.addLast(new Control());
                System.out.println("================ The worker [" + Thread.currentThread().getName() + "] ENDS store " + (CONTROLS.size()+1) + "th" + " Control=================");
            }
            Optional.of("The worker  [" + Thread.currentThread().getName() + "]  is working...").ifPresent(System.out::println);
            //  模拟正在工作
            try {
                Thread.sleep(60_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CaptureService.class) {
                Optional.of("============= The worker  [" + Thread.currentThread().getName() + "]  END capture data ===============").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CaptureService.class.notifyAll();
            }
        }, name);
    }

    private static class Control {
    }
}
