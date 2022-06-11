package __14__单例设计模式.__07枚举方式;

/**
 * @author wxg
 * @date 2022/1/6-16:50
 * @quotes 小不忍则乱大谋
 */
public class SingletonHolder {
    /** 实例变量 */
    private final byte[] data = new byte[1024];

    private SingletonHolder(){}

    /** 使用枚举类充当Holder */
    private enum EnumHolder{
        /** 静态类变量 */
        INSTANCE;
        private final SingletonHolder instance;
        EnumHolder(){
            this.instance = new SingletonHolder();
        }

        private SingletonHolder getSingletonHolder(){
            return instance;
        }
    }

    public static SingletonHolder getInstance(){
        return EnumHolder.INSTANCE.getSingletonHolder();
    }
}
