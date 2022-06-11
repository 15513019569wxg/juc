package __02__深入理解Thread构造函数.__04Thread构造函数.__03StackSize;

/**
 * The type Thread construction.
 *
 * @author wxg
 * @date 2021 /12/31-19:35
 * @quotes 小不忍则乱大谋
 */
public class ThreadConstruction {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please enter the stack size. ");
            System.exit(1);
        }
        final ThreadGroup group = new ThreadGroup("TestGroup");
        final Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurse(i + 1);
                }
            }
        };
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        final Thread thread = new Thread(group, runnable, "Test", Integer.parseInt(args[0]));
        thread.start();
    }
}
