package __01__快速认识线程.__03Runnable接口的策略模式.叫号程序之Runnable版本;


/**
 * The type Ticket window runnable.
 *
 * @author wxg
 * @date 2021 /12/31-11:30
 * @quotes 小不忍则乱大谋
 */
public class TicketWindowRunnable implements Runnable {
    private final static int MAX = 50;
    private int index = 1;

    @Override
    public void run() {
        while(index <= MAX){
            System.out.println(Thread.currentThread().getName() + " 的号码是： " + index++);
            try {
                //noinspection BusyWait
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread thread1 = new Thread(ticketWindowRunnable, "一号柜台");
        thread1.start();
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread thread2 = new Thread(ticketWindowRunnable, "二号柜台");
        thread2.start();
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread") final Thread thread3 = new Thread(ticketWindowRunnable, "三号柜台");
        thread3.start();
    }
}
