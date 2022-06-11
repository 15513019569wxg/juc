package __16__Single_Thread_Execution设计模式.__02哲学家吃面.improve;

import __16__Single_Thread_Execution设计模式.__02哲学家吃面.Tableware;

/**
 * @author wxg
 * @date 2022/1/9-16:24
 * @quotes 小不忍则乱大谋
 */
public class TablewarePair {
    private final Tableware leftTool;

    private final Tableware rightTool;

    public TablewarePair(Tableware leftTool, Tableware rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public Tableware getLeftTool() {
        return leftTool;
    }

    public Tableware getRightTool() {
        return rightTool;
    }
}
