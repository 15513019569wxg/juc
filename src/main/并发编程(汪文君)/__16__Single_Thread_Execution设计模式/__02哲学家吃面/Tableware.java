package __16__Single_Thread_Execution设计模式.__02哲学家吃面;

/**
 * @author wxg
 * @date 2022/1/9-16:02
 * @quotes 小不忍则乱大谋
 */
public class Tableware {
    /**
     * 餐具名称
     */
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool: " + toolName;
    }
}
