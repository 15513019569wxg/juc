package __03__ThreadApi的详细介绍.__06ThreadApi理解.__05interrupt使用;

/**
 * @author wxg
 * @date 2022/1/6-0:10
 * @quotes 小不忍则乱大谋
 */
public class BeforeHandInterrupt {
    public static void main(String[] args) {
        /*
         * "开始睡眠"和”结束睡眠“会同时打印（节省了睡眠时间），中断异常也会接收，并打印"发生中断"
         */
        Thread.currentThread().interrupt();
        System.out.println("开始睡眠");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("发生中断");
        }

        System.out.println("结束睡眠");
    }
}
