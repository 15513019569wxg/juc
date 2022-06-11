package __18__不可变对象设计模式.__03不可变的累加器对象设计;

import java.util.Collections;
import java.util.List;

/**
 * @author wxg
 * @date 2022/1/10-12:34
 * @quotes 小不忍则乱大谋
 */
public class Immutable {

    private final List<String> list;

    public Immutable(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        //return list; // 可以被其他线程修改
        // 不可变的
        return Collections.unmodifiableList(list);
    }
}
