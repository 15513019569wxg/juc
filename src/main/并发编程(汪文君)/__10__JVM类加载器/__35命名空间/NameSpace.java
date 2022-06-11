package __10__JVM类加载器.__35命名空间;

import __10__JVM类加载器.__34自定义类加载器.双亲委派机制.BrokeDelegateClassLoader;
import __10__JVM类加载器.__34自定义类加载器.自定义类加载器.MyClassLoader;

/**
 * @author wxg
 * @date 2022/1/7-17:27
 * @quotes 小不忍则乱大谋
 */
public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException {
        // 一个系统类加载器只有一个命名空间
        final ClassLoader classLoader = NameSpace.class.getClassLoader();
        final Class<?> aClass = classLoader.loadClass("__10__JVM类加载器.__35命名空间.Test");
        final Class<?> bClass = classLoader.loadClass("__10__JVM类加载器.__35命名空间.Test");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
        System.out.println(aClass == bClass);


        final Class<?> cClass = classLoader.loadClass("__10__JVM类加载器.__34自定义类加载器.自定义类加载器.HelloWorld");
        final Class<?> dClass = classLoader.loadClass("__10__JVM类加载器.__34自定义类加载器.自定义类加载器.HelloWorld");
        System.out.println(cClass.hashCode());
        System.out.println(dClass.hashCode());
        System.out.println(cClass == dClass);


        System.out.println("====================== 相同的类加载器加载同一个class ====================");
        final MyClassLoader myClassLoader1 = new MyClassLoader("E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)", null);
        final MyClassLoader myClassLoader2 = new MyClassLoader("E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)",null);
        final Class<?> myaClass = myClassLoader1.loadClass("__10__JVM类加载器.__35命名空间.Test");
        final Class<?> mybClass = myClassLoader2.loadClass("__10__JVM类加载器.__35命名空间.Test");
        System.out.println(myaClass.getClassLoader());
        System.out.println(mybClass.getClassLoader());
        System.out.println(myaClass.hashCode());
        System.out.println(mybClass.hashCode());
        System.out.println(mybClass.hashCode() == myaClass.hashCode());


        System.out.println("==================== 不同的类加载器加载同一个class ======================");
        final MyClassLoader myClassLoader3 = new MyClassLoader("E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)", null);
        final BrokeDelegateClassLoader brokeDelegateClassLoader = new BrokeDelegateClassLoader("E:\\IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)", null);
        final Class<?> myClass = myClassLoader3.loadClass("__10__JVM类加载器.__35命名空间.Test");
        final Class<?> brokeClass = brokeDelegateClassLoader.loadClass("__10__JVM类加载器.__35命名空间.Test");
        System.out.println(myClass.getClassLoader());
        System.out.println(brokeClass.getClassLoader());
        System.out.println(myaClass.hashCode());
        System.out.println(brokeClass.hashCode());
        System.out.println(brokeClass.hashCode() == myaClass.hashCode());
    }
}
