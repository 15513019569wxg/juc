package __14__单例设计模式.__02懒汉式;

/**
 * 不允许被继承
 * @author wxg
 * @date 2022/1/6-15:28
 * @quotes 小不忍则乱大谋
 */
public final class Singleton {
    /** 实例变量 */
    private final byte[] data = new byte[1024];
    /** 定义实例，但是不直接初始化 */
    private static Singleton instance = null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }
}
