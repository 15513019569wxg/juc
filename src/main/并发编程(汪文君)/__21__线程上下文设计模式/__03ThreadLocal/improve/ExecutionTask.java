package __21__线程上下文设计模式.__03ThreadLocal.improve;

/**
 * @author wxg
 * @date 2022/1/12-16:42
 * @quotes 小不忍则乱大谋
 */
public class ExecutionTask implements Runnable{

    private final QueryFromDBAction queryFromDbAction = new QueryFromDBAction();
    private final QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        queryFromDbAction.execute();
        System.out.println("The name query successfully");
        queryFromHttpAction.execute();
        System.out.println("The card id query successfully");
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("ExecutionTask: " + context.hashCode());
        System.out.println("The Name is " + context.getName() + " and the CardId " + context.getCardId());

    }
}
