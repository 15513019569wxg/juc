package __18__不可变对象设计模式.__03不可变的累加器对象设计;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 不可变对象不允许被继承
 * @author wxg
 * @date 2022/1/10-12:05
 * @quotes 小不忍则乱大谋
 */
public final class IntegerAccumulator{

    private final int init;

    /**
     * 构造时传入初始值
     * @param init 初始值
     */
    private IntegerAccumulator(int init) {
        this.init = init;
    }

    /**
     * 构造新的累加器, 需要用到另外一个accumulator和初始值
     * @param accumulator 累加器
     * @param init 初始值
     */
    public IntegerAccumulator(IntegerAccumulator accumulator, int init){
        this.init = accumulator.getValue() + init;
    }

    /**
     * 每次相加都会产生一个新的IntegerAccumulator
     * @param i 值
     * @return 累加器
     */
    public IntegerAccumulator add(int i){
        return new IntegerAccumulator(this, i);
    }

    private int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator integerAccumulator = new IntegerAccumulator(0);
        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while(true){
                // 这个oldValue的值永远都为0
                int oldValue = integerAccumulator.getValue();
                // 返回的是重新被赋值的init <----- inc
                int result = integerAccumulator.add(inc).getValue();
                System.out.println(oldValue + " + " + inc + " = " + result);
                if(inc + oldValue != result){
                    System.out.println("ERROR: " + oldValue + " + " + inc + " = " + result);
                }
                inc++;
                slowly();
            }
        }).start());
    }

    private static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
