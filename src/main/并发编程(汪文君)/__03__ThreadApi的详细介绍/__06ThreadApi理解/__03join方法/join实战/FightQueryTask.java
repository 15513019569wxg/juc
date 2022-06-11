package __03__ThreadApi的详细介绍.__06ThreadApi理解.__03join方法.join实战;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 用于到各大航空公司获取数据
 *
 * @author wxg
 * @date 2022 /1/1-15:13
 * @quotes 小不忍则乱大谋
 */
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    /**
     * Instantiates a new Fight query task.
     *
     * @param airline     the airline
     * @param origin      the origin
     * @param destination the destination
     */
    public FightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * 核心方法
     */
    @Override
    public void run() {
        System.out.printf(" %s-query %s to %s \n", getName(), origin, destination);
        final int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            //  给每一个线程起名字
            flightList.add(getName() + "-" + randomVal);
            //  打印一句话
            System.out.printf("The Fight: %s List query successful \n", getName());
        } catch (InterruptedException ignored) {}
    }

    /**
     * 获取线程名字
     * @return 线程名字列表
     */
    @Override
    public List<String> get() {
        return flightList;
    }
}
