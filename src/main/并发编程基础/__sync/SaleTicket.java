package __sync;/*
    @author wxg
    @date 2021/12/27-21:48
    */

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author capensis
 */
class Ticket{
    private int number  = 30;
   public synchronized void sale(){
        if( number > 0) {
            System.out.println(Thread.currentThread().getName() + ": 卖出 " + number-- + " 剩下 " + number);
        }
    }
}






/**
 * @author capensis
 */
public class SaleTicket {
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        Arrays.asList("AA", "BB", "CC").forEach(s -> new Thread(() -> IntStream.range(0, 40).forEach(i -> ticket.sale()), s).start());

    }
}
