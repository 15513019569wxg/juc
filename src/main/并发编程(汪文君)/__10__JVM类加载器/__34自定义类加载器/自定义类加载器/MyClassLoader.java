package __10__JVM类加载器.__34自定义类加载器.自定义类加载器;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *自定义类加载器必须是ClassLoader的直接或者间接子类
 * @author wxg
 * @date 2022/1/7-14:20
 * @quotes 小不忍则乱大谋
 */
public class MyClassLoader extends ClassLoader{
    /** 定义默认的class存放路径 */
    private final static Path DEFAULT_CLASS_DIR = Paths.get(
            "E:",
            "IDEAProject\\test\\juc\\src\\main\\并发编程(汪文君)");

    /** 默认的class路径 */
    private final Path classDir;

    /** 使用默认的Class路径*/
    public MyClassLoader(){
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    /** 允许传入指定路径的class路径*/
    public MyClassLoader(String classDir){
        super();
        this.classDir = Paths.get(classDir);
    }

    /**
     * 指定class路径的同时， 指定父类加载器
     * @param classDir class文件
     * @param parent  父类加载器
     */
    public MyClassLoader(String classDir, ClassLoader parent){
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    /**
     * 重写父类的findClass()
     * @param name  文件路径
     * @return  类对象
     * @throws ClassNotFoundException 类找不到异常
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 读取class的二进制数据
        final byte[] classBytes = readClassBytes(name);
        // 如果数据为null, 或者没有读到任何信息, 则抛出ClassNotFountException异常
        if(classBytes.length == 0){
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        // 调用defineClass 方法定义class
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * 将class文件读入内存
     * @return class文件路径二进制的字节数组
     * */
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //  将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        final Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        // 判断class文件是否存在
        if(!classFullPath.toFile().exists()){
           throw new ClassNotFoundException("The class " + name + " not found.");
        }
        //  如果字节码文件存在
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        }catch(IOException e){
            throw new ClassNotFoundException("load the class " + name + " occur error", e);
        }
    }
    @Override
    public String toString(){
        return "My ClassLoader";
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 声明一个MyClassLoader
        final MyClassLoader classLoader = new MyClassLoader();
        // 使用MyClassLoader加载HelloWorld， loadClass会调用findClass()
        final Class<?> aClass = classLoader.loadClass("__10__JVM类加载器.__34自定义类加载器.自定义类加载器.HelloWorld");
        System.out.println(aClass.getClassLoader());
        //  ①注释
        final Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        // 注意，在类的装载过程中static语句块中的代码不会打印。
        final Method welcomeMethod = aClass.getMethod("welcome");
        final String result = (String)welcomeMethod.invoke(helloWorld);
        System.out.println("Result: " + result);
    }
}
