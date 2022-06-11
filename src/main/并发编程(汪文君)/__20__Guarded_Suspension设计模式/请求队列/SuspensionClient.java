package __20__Guarded_Suspension设计模式.请求队列;

import java.util.Random;

/**
 * @author wxg
 * @date 2022/1/11-11:53
 * @quotes 小不忍则乱大谋
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        // 共享资源
        final RequestQueue requestQueue = new RequestQueue();
        // 启动请求者
        new ClientThread(requestQueue,String.valueOf(new Random().nextInt(100))).start();
        // 启动服务者
        final ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();

        Thread.sleep(1);
        /*
            关闭服务器,服务器关闭的早晚会影响关闭serverThread的方式：
                1、如果该语句在serverThread执行run()进行flag的判断时执行，此时flag被修改为true,while(!flag)就不执行,
                   run()结束，serverThread死掉，这时interrupt()不理睬；
                2、如果该语句执行时，serverThread已经都已经进入到了阻塞状态，那么此时serverThread被打断(flag也被修改为true),
                    继续往下执行，将null返回,线程开始进行continue,这时轮询到while(!flag),此时run()结束, 线程执行结束，死掉；
                3、如果该语句执行时, serverThread正在睡眠(处理任务), flag变为了true, 睡眠状态被打断, 捕获中断异常后，直接return,
                    while(!flag)直接结束，run()执行完成, 线程死掉。
         */
        serverThread.close();
    }
}
