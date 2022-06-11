package __10__JVM类加载器.__36运行时包;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxg
 * @date 2022/1/8-9:27
 * @quotes 小不忍则乱大谋
 */
public class SimpleClass {
    /** 在SimpleClass中使用byte[]*/
    private static final byte[] BUFFER = new byte[8];

    /** 在SimpleClass中彼此使用String*/
    private static final String STR;

    /** 在SimpleClass中使用List*/
    private static final List<String> LIST = new ArrayList<>();

    static{
        BUFFER[0] = (byte) 1;
        STR = "Simple";
        LIST.add("element");
        System.out.println(BUFFER[0]);
        System.out.println(STR);
        System.out.println(LIST.get(0));
    }
}
