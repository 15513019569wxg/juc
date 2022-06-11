package __14__单例设计模式.__06Holder方式;

/**
 * @author wxg
 * @date 2022/1/6-16:17
 * @quotes 小不忍则乱大谋
 */
public  final class Singleton {
    /** 实例变量 */
    private final byte[] data = new byte[1024];

    private Singleton(){

    }

    /** 定义一个内部静态类中持有Singleton的实例, 并且可以被直接初始化*/
    private static class Holder{
        private static final Singleton INSTANCE = new Singleton();
    }

    /** 调用getInstance方法, 事实上是获得Holder的instance静态属性*/
    public static Singleton getInstance(){
        return Holder.INSTANCE;
    }
}
