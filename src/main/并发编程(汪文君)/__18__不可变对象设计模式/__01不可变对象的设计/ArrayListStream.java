package __18__不可变对象设计模式.__01不可变对象的设计;

import java.util.Arrays;
import java.util.List;

/**
 * @author wxg
 * @date 2022/1/10-11:34
 * @quotes 小不忍则乱大谋
 */
public class ArrayListStream {
    public static void main(String[] args) {
        final List<String> list = Arrays.asList("Java", "Thread", "Concurrency");
        // 获取并行的stream, 然后通过map函数对list的中的数据进行加工,并行打印
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
        // 顺序打印
        list.forEach(System.out::println);
    }
}
