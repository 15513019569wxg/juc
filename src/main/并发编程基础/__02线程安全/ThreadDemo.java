package __02线程安全;/*
    @author wxg
    @date 2021/12/28-11:51
    */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;


/**
 * @author capensis
 */
public class ThreadDemo {
    public static void main(String[] args) {
        /*
         List<String> list = new ArrayList<>();
           第一种解决方案
         List<String> list = new Vec*tor<>();
           第二种解决方案
        */
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        //写时复制技术：并发读， 独立写（类似于签到的例子）
        List<String> list = new CopyOnWriteArrayList<>();
        IntStream.range(0, 10).forEachOrdered(i -> new Thread(() -> {
            list.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(list);
        },String.valueOf(i)).start());

        //  HashSet的并发写也存在线程安全问题
        //  Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        IntStream.range(0, 10).forEachOrdered(i -> new Thread(() -> {
            set.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(set);
        },String.valueOf(i)).start());

        //  HashMap的并发写存在线程安全问题
        //Map<String,String> map= new HashMap<>(20);
        Map<String,String> map= new ConcurrentHashMap<>(20);
        IntStream.range(0, 10).forEachOrdered(i -> new Thread(() -> {
            map.put(String.valueOf(i), UUID.randomUUID().toString().substring(0,8));
            System.out.println(map);
        },String.valueOf(i)).start());

    }
}
