package __05__线程通信.__12单线程间通信;

/**
 * The type Producer consumer demo 1.
 *
 * @author wxg
 * @date 2022 /1/2-19:36
 * @quotes 小不忍则乱大谋
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ProducerConsumerDemo1 {
    private int number = 1;
    private void produce(){
        synchronized(ProducerConsumerDemo1.class){
            System.out.println("P " + number++);
        }
    }

    private void consume(){
        synchronized(ProducerConsumerDemo1.class){
            System.out.println("C " + number);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final ProducerConsumerDemo1 producerConsumerDemo1 = new ProducerConsumerDemo1();
        new Thread("P"){
            @Override
            public void run() {
                while(true){
                    producerConsumerDemo1.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while(true){
                    producerConsumerDemo1.consume();
                }
            }
        }.start();
    }
}
