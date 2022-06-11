package __10__JVM类加载器.__32扩展加载器;

/**
 * @author wxg
 * @date 2022/1/7-11:28
 * @quotes 小不忍则乱大谋
 */
public class ExtClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("java.ext.dirs"));
        final Class<?> aClass = Class.forName("__10__JVM类加载器.__32扩展加载器.Hello");
        System.out.println(aClass.getClassLoader());
        System.out.println("Hello");
    }
}
