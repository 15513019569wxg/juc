package __26__Two_Phase_Termination设计模式;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author wxg
 * @date 2022/2/2-10:24
 * @quotes 小不忍则乱大谋
 */
public class LRUCache<K, V> {
    /**
     * 用于记录key的顺序
     */
    private final LinkedList<K> keyList = new LinkedList<>();

    /**
     * 用于存放数据
     */
    private final Map<K,V> cache = new HashMap<>();

    /**
     * cache的最大容量
     */
    private final int capacity;

    /**
     * cacheLoader接口提供了一种加载数据的方式
     */
    private final CacheLoader<K, V> cacheLoader;

    public LRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value){
        // 当元素数量超过容量时, 将最老的数据清除
        if(keyList.size() >= capacity){
            //  eldest  data
            final K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }
        //  如果数据已经存在, 则从key的队列中删除
        if(keyList.contains(key)){
            keyList.remove(key);
        }
        //  将key存放至队尾
        keyList.addLast(key);
        cache.put(key,value);
    }

    public V get(K key){
        V value;
        //  先将key从list中删除
        final boolean success = keyList.remove(key);
        //  如果删除失败则表明数据不存在
        if(success){
            //  通过cacheLoader对数据进行加载
            value = cacheLoader.load(key);
            //  调用put方法cache数据
            put(key,value);
        }else{
            //  如果删除成功，则从cache中返回数据，并且将key再次放到队尾
            value = cache.get(key);
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return keyList.toString();
    }
}
