package __03__ThreadApi的详细介绍.__07关闭线程.强制关闭;

/**
 * The type Thread close force.
 *
 * @author wxg
 * @date 2022 /1/1-17:10
 * @quotes 小不忍则乱大谋
 */
public class ThreadCloseForce {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final ThreadService threadService = new ThreadService();

        final long start = threadService.execute(() -> {
//            while (true) {
//                //  load a very heavy resource
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadService.shutDown(10000, start);
    }
}
