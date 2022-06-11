package __14__单例设计模式.__04Double__Ckeck;

import java.net.Socket;
import java.sql.Connection;

/**
 * @author wxg
 * @date 2022/1/6-15:41
 * @quotes 小不忍则乱大谋
 */
public final class Singleton {
    /** 实例变量 */
    private final byte[] data = new byte[1024];

    /** 加入volatile 可以防止jvm指令重排序*/
    private static volatile Singleton instance = null;

    Connection conn;

    Socket socket;

    private Singleton(){
        // 初始化conn
        // 初始化socket
    }

    public static Singleton getInstance(){
        // 当instance为null时，进入同步代码块，同时该判断避免了每次都需要进入同步代码块，可以提高效率
        if(null == instance){   // 拦住之后的其他线程
            // 只有一个线程能够获得Singleton.class管来奶的monitor
            synchronized(Singleton.class){
                // 判断如果instance为null了则创建
                if(null == instance){  // 拦住刚进来的几个线程
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
