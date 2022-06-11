package __21__线程上下文设计模式.__03ThreadLocal.案例;

/**
 * @author wxg
 * @date 2022/1/12-16:44
 * @quotes 小不忍则乱大谋
 */
public class Context {

    private String name;
    private String cardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
