package __16__Single_Thread_Execution设计模式.__02哲学家吃面;

/**
 * 交叉锁引起死锁
 * @author wxg
 * @date 2022/1/9-16:04
 * @quotes 小不忍则乱大谋
 */
public class EatNoodleThread extends Thread{

    private final String name;
    /**
     * 左手边的餐具
     */
    private final Tableware leftTool;

    /**
     * 右手边的餐具
     */
    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while(true){
            eat();
        }
    }

    /**
     * 吃面条的问题
     */
    private void eat() {
        synchronized(leftTool){
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized(rightTool){
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating now...");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }
    }

    public static void main(String[] args) {
        final Tableware fork = new Tableware("fork");
        final Tableware knife = new Tableware("knife");
        new EatNoodleThread("A", fork, knife).start();
        new EatNoodleThread("B", knife, fork).start();
    }
}
