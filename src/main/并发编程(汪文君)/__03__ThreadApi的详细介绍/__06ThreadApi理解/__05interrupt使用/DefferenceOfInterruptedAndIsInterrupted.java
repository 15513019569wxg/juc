package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

/**
 * @author wxg
 * @date 2022/1/5-23:49
 * @quotes 小不忍则乱大谋
 */
public class DefferenceOfInterruptedAndIsInterrupted {
    public static void main(String[] args) {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread thread = new Thread(() -> {
            final long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 3) {
                /*
                    该方法会判断出自己是否被中断，如果被中断，就把自己的中断标识记为true，并一直保存下去，
                 不会清除这次的中断请求，即一次请求，终生铭记
                 */
                //if (Thread.currentThread().isInterrupted()) {
                if(Thread.interrupted()){
                    /*
                        interrupted()则不仅会判断自己有没有收到其他线程的中断请求,如果收到，会返回true，执行中断请求，
                     当这一次的中断请求结束后，会重新将自己的中断标记记为false，即一次请求，一次回应。
                     */
                    System.out.println("向左开1米");
                } else {
                    System.out.println("往前开1米");
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
