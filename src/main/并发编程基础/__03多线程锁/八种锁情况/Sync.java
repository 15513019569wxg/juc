package __03多线程锁.八种锁情况;/*
    @author wxg
    @date 2021/12/28-16:30
    */

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 1、标准访问：先打印短信还是邮件
 *  ----------------sendSMS
 *  ----------------sendEmail
 *2、停4秒在短信方法内，先打印短信还是邮件（停顿4s的方法必须要执行结束，同一个对象才能调用其他的普通同步方法）
 *  ----------------sendSMS
 *  ----------------sendEmail
 *3、新增普通的Hello(), 先打印短信还是Hello （此时完全取决于线程抢占资源的顺序）
 *  ------------Hello------------
 *  ----------------sendSMS
    对于1、2和3情况来说，如果同一个对象的多个普通方法被加锁，则执行顺序是必须确保某个普通同步方法执行结束，才能执行
 另外一个普通同步方法。如果某个方法没有被加锁，此时普通同步加锁方法和未加锁的方法的执行顺序取决于哪个线程先抢占资源

 *4、现在有两部手机，先打印短信还是邮件
 *  ============sendEmail
 *  ============sendSMS
 对于4情况来说，如果普通同步方法对应的对象是不同的对象，则执行顺序由对象的加载顺序决定。

 *5、两个静态同步方法，1步手机，先打印短信还是邮件
 *  ============sendSMS
 *  ============sendEmail
 *6、两个静态同步方法，2步手机，先打印短信还是邮件
 *  ============sendSMS
 *  ============sendEmail
 对于5、6情况来说，多个同步方法被设置为static，此时被上锁的对象是Class，也是同一个对象，哪个方法先执行取决于哪个线程先抢占资源。

 *7、1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *  ============sendEmail
 *  ============sendSMS
 *8、1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 *  ============sendEmail
 *  ============sendSMS
 对于7、8情况来说，一个是class对象被上锁，一个是实例对象被上锁，属于不同的对象被上锁，此时的情况和4情况一样

    一旦一个（实例）对象被上锁，那么该（实例）对象无论在哪个线程中调用哪个普通同步方法，该普通同步方法必须要被执行完，
 对象才可以执行其他普通同步方法。也就是说，此时不会出现线程间的资源抢占问题。线程间的资源抢占问题只出现一开始的对象加载中。
 对于类对象的情况也是如此。
 */



class Phone{
    public static synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        System.out.println("============sendSms");
    }

    public static synchronized void sendEmail() {
        System.out.println("============sendEmail");
    }

    public void getHello() {
        System.out.println("=======Hello=========");
    }
}


/**
 * @author capensis
 */
public class Sync {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        Collections.singletonList("AA").forEach(s ->new Thread(() -> IntStream.range(0, 1).forEach(i -> {
            try {
                Phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }),s).start());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collections.singletonList("BB").forEach(s->new Thread(() -> IntStream.range(0, 1).forEach(i -> {
            try {
                Phone.sendEmail();
                //phone.getHello();
                //phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }),s).start());
    }
}
