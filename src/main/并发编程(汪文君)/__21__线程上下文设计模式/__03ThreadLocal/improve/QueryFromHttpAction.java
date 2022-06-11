package __21__线程上下文设计模式.__03ThreadLocal.improve;

/**
 * @author wxg
 * @date 2022/1/12-16:52
 * @quotes 小不忍则乱大谋
 */
public class QueryFromHttpAction {
    /**
     * 属于main线程，不属于执行线程
     */
    //Context context = ActionContext.getActionContext().getContext();

    public void execute(){
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("QueryFromHttpAction: " + context.hashCode()
                + " " + Thread.currentThread().getName()
        );
        String cardId = getCardId();
        context.setCardId(cardId);
    }

    private String getCardId() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "37893452" + Thread.currentThread().getName();
    }
}
