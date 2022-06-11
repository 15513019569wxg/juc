package __01__快速认识线程.__02Thread和Runnable的区别.叫号程序之Thread版本;

/**
 * The type Ticket window ver 2.
 *
 * @author wxg
 * @date 2021 /12/31-10:52
 * @quotes 小不忍则乱大谋
 */
public class TicketWindowVer2 extends Thread {
    /**
     * 柜台名称
     */
    private final String name;

    /**
     * 最多受理50笔业务
     */
    private static final int MAX = 500;

    /**
     *     通过对index进行static进行修饰，做到了多线程下共享资源的唯一性（存在线程安全问题），但是只有index共享资源，如果
     * 共享资源很多呢？共享资源要经过一些比较复杂的计算呢？不可能都使用static修饰，而且static修饰的变量生命周期很长，
     * 所以java提供了一个接口Runnable专门用于解决该问题，将线程的控制和业务逻辑的运行彻底分离开来。
     */
    private static int index = 1;

    /**
     * Instantiates a new Ticket window ver 2.
     *
     * @param name the name
     */
    public TicketWindowVer2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(" 柜台： " + name + " 当前的号码是： " + index++);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final TicketWindowVer2 ticketWindowVer1 = new TicketWindowVer2("一号柜台");
        final TicketWindowVer2 ticketWindowVer2 = new TicketWindowVer2("二号柜台");
        final TicketWindowVer2 ticketWindowVer3 = new TicketWindowVer2("三号柜台");
        ticketWindowVer1.start();
        ticketWindowVer2.start();
        ticketWindowVer3.start();

    }
}
