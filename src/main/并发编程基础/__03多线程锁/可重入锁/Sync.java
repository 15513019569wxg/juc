package __03多线程锁.可重入锁;/*
    @author wxg
    @date 2021/12/29-11:16
    */


/**
 * @author capensis
 */
public class Sync {

    /** 不是可重入锁，里面的add()就不会调用，也就不会出现栈溢出 */
//    public synchronized void add(){
//        add();
//    }

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {
        // new Sync().add();
        final Object o = new Object();
        new Thread(()->{
            synchronized(o){
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized(o){
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized(o){
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        },"t").start();
    }
}
