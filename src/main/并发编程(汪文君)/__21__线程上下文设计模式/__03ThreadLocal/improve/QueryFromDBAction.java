package __21__线程上下文设计模式.__03ThreadLocal.improve;

/**
 * @author wxg
 * @date 2022/1/12-16:43
 * @quotes 小不忍则乱大谋
 */
public class QueryFromDBAction {
    /** 属于main线程的, 不属于执行线程 */
    //Context context = ActionContext.getActionContext().getContext();

    public void execute(){
        Context context = ActionContext.getActionContext().getContext();
        System.out.println(" QueryFromDBAction: " + context.hashCode()
               + " " + Thread.currentThread().getName()
        );
        try {
            Thread.sleep(2000);
            String name = "Alex " + Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
