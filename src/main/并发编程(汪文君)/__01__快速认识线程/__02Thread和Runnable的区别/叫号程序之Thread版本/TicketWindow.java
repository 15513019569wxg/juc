package __01__快速认识线程.__02Thread和Runnable的区别.叫号程序之Thread版本;

/**
 * The type Ticket window.
 *
 * @author wxg
 * @date 2021 /12/31-10:39
 * @quotes 小不忍则乱大谋
 */
public class TicketWindow extends Thread{
    /**  柜台名称 */
    private final String name;

    /** 最多受理50笔业务 */
    private static final int MAX = 50;

    /** 这里的index没有共享 */
    private int index = 1;

    /**
     * Instantiates a new Ticket window.
     *
     * @param name the name
     */
    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(" 柜台： " + name + " 当前的号码是： " + index++ );
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final TicketWindow ticketWindow1 = new TicketWindow("一号柜台");
        final TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        final TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();

    }
}
