package __01__快速认识线程.__01线程的创建和启动;

/**
 * The type Try concurrency.
 *
 * @author capensis
 */
public class TryConcurrency {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread("Read-Thread"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        //noinspection AlibabaAvoidManuallyCreateThread
        new Thread("Write-Thread"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }

    private static void readFromDataBase() {
        //  Read data from database and handle it.
        try{
            println("Begin read data from db.");
            Thread.sleep(1000 * 30L);
            println("Read data done and start handle it.");
        }catch (InterruptedException e){
           e.printStackTrace();
        }
        println("The data read finish and successfully.");
    }

    private static void writeDataToFile() {
        try{
            println("Begin write data to file.");
            Thread.sleep(2000 * 20L);
            println("Write data done and start handle it.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        println("The data write finish and successfully.");
    }

    private static void println(String s) {
        System.out.println(s);
    }


}
