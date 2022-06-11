package __21__线程上下文设计模式.__01上下文;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wxg
 * @date 2022/1/12-12:13
 * @quotes 小不忍则乱大谋
 */
public class ApplicationContext {

    /**
     * 在Context中保存configuration实例
     */
    private ApplicationConfiguration configuration;

    /**
     * 在Context中保存runtimeInformation实例
     */
    private RuntimeInfo runtimeInfo;

    /**
     * 线程上下文
     */
    private final ConcurrentHashMap<Thread, ActionContext> contexts = new ConcurrentHashMap<>();

    /**
     * 私有化构造器
     */
    private ApplicationContext(){
        super();
    }

    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    public RuntimeInfo getRuntimeInfo() {
        return runtimeInfo;
    }

    public void setRuntimeInfo(RuntimeInfo runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
    }

    /**
     * 采用Holder的方式实现实例
     */
    private static class Holder{
        /**
         * 创建单例
         */
        private static final AtomicReference<ApplicationContext> INSTANCE = new AtomicReference<>(new ApplicationContext());
    }

    public static ApplicationContext getApplicationContext(){
        return Holder.INSTANCE.get();
    }

    /**
     * 获取线程的上下文
     * @return 线程的环境上下文
     */
    public ActionContext getActionContext(){
        ActionContext actionContext = contexts.get(Thread.currentThread());
        if(actionContext == null){
            actionContext = new ActionContext();
            // 线程级别的单例，永远只有一个线程访问ActionContext
            contexts.put(Thread.currentThread(), actionContext);
        }
        return actionContext;
    }
}
