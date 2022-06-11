package __09__类的加载过程.__28装载过程;

/**
 * @author wxg
 * @date 2022/1/7-9:31
 * @quotes 小不忍则乱大谋
 */
public class Singleton {
    /*** ① */
    private static final Singleton INSTANCE = new Singleton();
    private static int x = 0;
    private static int y = 1;
    private final int z;

    private Singleton() {
        x++;
        y++;
        z = 10;
    }

    public static Singleton getInstance(){
        y = 5;
        return INSTANCE;
    }

    public static void main(String[] args){
        final Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.z);
        System.out.println(x);
        System.out.println(y);

    }
}
