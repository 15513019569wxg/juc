package __01__快速认识线程.__01线程的创建和启动;

/**
 * The type Current thread.
 *
 * @author wxg
 * @date 2022 /1/1-9:00
 * @quotes 小不忍则乱大谋
 */
public class CurrentThread {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        Thread thread = new Thread(){
            @Override
            public void run() {
                //  always true
                System.out.println("当前对象是否与当前创建的线程相等: " + (Thread.currentThread() == this));
            }
        };

        thread.start();


        final String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));




    }
}
