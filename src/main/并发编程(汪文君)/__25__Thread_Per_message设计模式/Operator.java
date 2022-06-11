package __25__Thread_Per_message设计模式;

/**
 * Operator代表了接线员, 当有电话打进来时，话务员会将客户的请求封装成一个工单，然后开辟一个线程（工作人员）去处理
 * @author wxg
 * @date 2022/1/31-20:51
 * @quotes 小不忍则乱大谋
 */
public class Operator {
    public void call(String business){
        //  为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        new Thread(taskHandler).start();
    }
}
