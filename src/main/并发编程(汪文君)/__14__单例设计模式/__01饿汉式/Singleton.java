package __14__单例设计模式.__01饿汉式;

/**
 * * 不允许继承，饿汉式创建
 * @author wxg
 * @date 2022/1/6-11:23
 * @quotes 小不忍则乱大谋
 */
public final class Singleton {
    /** 实例变量*/
    private final byte[] date = new byte[1024];
    /** 在定义实例对象的时候直接初始化*/
    private static final Singleton INSTANCE = new Singleton();
    /** 私有构造函数， 不允许外部new*/
    private Singleton(){

    }
    public static Singleton getSingleton(){
        return INSTANCE;
    }
}
