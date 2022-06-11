package __16__Single_Thread_Execution设计模式.__02哲学家吃面.improve;

import __16__Single_Thread_Execution设计模式.__02哲学家吃面.Tableware;

/**
 * @author wxg
 * @date 2022/1/9-16:26
 * @quotes 小不忍则乱大谋
 */
public class EatNoodleThread extends Thread{
    private final String name;
    private final TablewarePair tablewarePair;

    public EatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while(true) {
          eat();
        }
    }

    private void eat() {
        // 在同一个时间只能有一个线程获得刀和叉
        synchronized(tablewarePair){
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now...");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left)");
        }
    }

    public static void main(String[] args) {
        final Tableware fork = new Tableware("fork");
        final Tableware knife = new Tableware("knife");
        // 装到一把锁里面
        final TablewarePair tablewarePair = new TablewarePair(fork, knife);
        new EatNoodleThread("A", tablewarePair).start();
        new EatNoodleThread("B", tablewarePair).start();
    }
}
