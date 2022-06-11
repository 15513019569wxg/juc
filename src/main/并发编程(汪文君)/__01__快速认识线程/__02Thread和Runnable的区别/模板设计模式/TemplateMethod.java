package __01__快速认识线程.__02Thread和Runnable的区别.模板设计模式;

/**
 * The type Template method.
 *
 * @author wxg
 * @date 2021 /12/31-10:26
 * @quotes 小不忍则乱大谋
 */
public class TemplateMethod {

    /**
     * Wrap print.
     *
     * @param s the s
     */
    protected void wrapPrint(String s){}

    /**
     * print()类似于Thread的start(), 而wrapPrint则类似于run(), 这样做的好处是：
     * 程序结构由父类控制，并且是由final修饰的，不允许被重写，子类只需要实现想要的逻辑任务即可
     *
     * @param message 数据
     */
    public final void print(String message){
        System.out.println("=====================");
        wrapPrint(message);
        System.out.println("=====================");

    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final TemplateMethod templateMethod = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println(" == " + message + " ==");
            }
        };
        templateMethod.print("Hello World");

        final TemplateMethod templateMethod2= new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println(" **** " + message + " ****");
            }
        };

        templateMethod2.print("Hello World");
    }
}
