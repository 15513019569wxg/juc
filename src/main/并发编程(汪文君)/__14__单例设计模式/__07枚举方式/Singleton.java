package __14__单例设计模式.__07枚举方式;

/**
 * 使用枚举
 * @author wxg
 * @date 2022/1/6-16:19
 * @quotes 小不忍则乱大谋
 */
public enum Singleton{
    /** 放在第一行 */
    INSTANCE;

    /** 实例变量 */
    private final byte[] data = new byte[1024];

    Singleton(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method(){
        // 调用该方法则会主动使用Singleton, INSTANCE将会被实例化
    }

    public static Singleton getInstance(){
        return INSTANCE;
    }
}
