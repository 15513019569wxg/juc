package __10__JVM类加载器.__36运行时包;

import __10__JVM类加载器.__34自定义类加载器.双亲委派机制.BrokeDelegateClassLoader;

/**
 * @author wxg
 * @date 2022/1/8-9:35
 * @quotes 小不忍则乱大谋
 */
public class LoadSimpleClass {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        final BrokeDelegateClassLoader brokeDelegateClassLoader = new BrokeDelegateClassLoader("E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)");
        final Class<?> aClass = brokeDelegateClassLoader.loadClass("__10__JVM类加载器.__36运行时包.SimpleClass");
        System.out.println(brokeDelegateClassLoader.getParent());
        aClass.newInstance();

    }
}
