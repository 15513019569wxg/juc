package __21__线程上下文设计模式.__03ThreadLocal.案例;


/**
 * @author wxg
 * @date 2022/1/12-16:52
 * @quotes 小不忍则乱大谋
 */
public class QueryFromHttpAction {
    public void execute(Context context){
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "37893452" + Thread.currentThread().getName();
    }
}
