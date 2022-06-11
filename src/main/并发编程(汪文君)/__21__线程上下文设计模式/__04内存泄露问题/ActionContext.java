package __21__线程上下文设计模式.__04内存泄露问题;

/**
 * @author wxg
 * @date 2022/1/12-15:35
 * @quotes 小不忍则乱大谋
 */
public class ActionContext {

    /**
     * 定义ThreadLocal, 并且使用Supplier的方式重写initValue
     */
    private static final ThreadLocal<Context> CONTEXT = ThreadLocal.withInitial(Context::new);

    /**
     * 为Configuration创建ThreadLocal
     */
    private static final ThreadLocal<Context.Configuration> CONFIGURATION = ThreadLocal.withInitial(Context.Configuration::new);

    /**
     * 为OtherResource创建ThreadLocal
     */
    private static final ThreadLocal<Context.OtherResource> OTHER_RESOURCE = ThreadLocal.withInitial(Context.OtherResource::new);

    public static Context get(){
        return CONTEXT.get();
    }

    /**
     * 每一个线程都会有一个独立的Context实例
     */
    private static class Context {
        //在Context中的其他成员
        private Configuration configuration;
        private OtherResource otherResource;

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public OtherResource getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(OtherResource otherResource) {
            this.otherResource = otherResource;
        }

        private static class Configuration {}

        private static class OtherResource {}
    }
}
