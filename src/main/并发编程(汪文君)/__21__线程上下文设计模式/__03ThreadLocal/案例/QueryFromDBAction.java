package __21__线程上下文设计模式.__03ThreadLocal.案例;

/**
 * @author wxg
 * @date 2022/1/12-16:43
 * @quotes 小不忍则乱大谋
 */
public class QueryFromDBAction {
    public void execute(Context context){
        try {
            Thread.sleep(2000);
            String name = "Alex " + Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
