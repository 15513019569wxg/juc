package __10__JVM类加载器.__31根加载器;

/**
 * @author wxg
 * @date 2022/1/7-11:22
 * @quotes 小不忍则乱大谋
 */
public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap: " + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
