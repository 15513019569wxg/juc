package __03__ThreadApi的详细介绍.__06ThreadApi理解.__03join方法.join实战;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Fight query example.
 *
 * @author wxg
 * @date 2022 /1/1-15:28
 * @quotes 小不忍则乱大谋
 */
public class FightQueryExample {
    /**
     * ①合作的各大航空公司
     */
    private static final List<String> FIGHT_COMPANY = Arrays.asList("CSA", "CEA", "HNA");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("============== result ==============");
        results.forEach(System.out::println);
    }

    /**
     * 实现上海到北京的航班查询
     *
     * @param original    起始地
     * @param destination 目的地
     * @return 查询的航线数组
     */
    private static List<String> search(String original, String destination) {
        final ArrayList<String> result = new ArrayList<>();
        // ② 创建查询航班信息的线程列表
        final List<FightQueryTask> tasks = FIGHT_COMPANY.stream().map(fight -> createSearchTask(fight, original, destination)).collect(Collectors.toList());
        // ③ 分别启动这几个线程
        tasks.forEach(Thread::start);
        // ④ 分别调用每一个线程的join方法，阻塞当前main线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // ⑤ 在此之前，当前线程会被阻塞住，获取每一个查询线程的结果（结果以线程名字表示），并且加入到result中
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    /**
     * 调用线程获取数据
     *
     * @param fight       航空公司
     * @param original    起始地
     * @param destination 目的地
     * @return 查询数据
     */
    private static FightQueryTask createSearchTask(String fight, String original, String destination) {
        return new FightQueryTask(fight, original, destination);
    }
}
