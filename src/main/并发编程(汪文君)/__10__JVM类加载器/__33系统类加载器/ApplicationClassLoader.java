package __10__JVM类加载器.__33系统类加载器;

/**
 * @author wxg
 * @date 2022/1/7-12:16
 * @quotes 小不忍则乱大谋
 */
public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
