package __21__线程上下文设计模式.__03ThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxg
 * @date 2022/1/12-16:23
 * @quotes 小不忍则乱大谋
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t){
        synchronized(this){
            final Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get(){
        synchronized (this){
            final Thread key = Thread.currentThread();
            final T value = storage.get(key);
            if(value == null){
                return initialValue();
            }
            return value;
        }
    }

    private T initialValue() {
        return null;
    }
}
